/**
 * This file is part of Zap Framework.
 *
 * Zap is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Zap is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Zap. If not, see <http://www.gnu.org/licenses/>.
 */
package com.zap.decoders;

import com.zap.LoginState;
import com.zap.Zap;
import com.zap.game.player.Player;
import com.zap.game.player.PlayerHandler;
import com.zap.game.player.PlayerSave;
import com.zap.packet.PacketBuilder;
import com.zap.util.Constants;
import com.zap.util.NetUtilities;
import java.security.SecureRandom;
import net.burtlebutle.bob.rand.isaac.ISAAC;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 *
 * @author Faris
 */
public class RS2LoginProtocolDecoder extends FrameDecoder {

    private LoginState state = LoginState.CONNECTED;

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
        if (!channel.isConnected()) {
            return null;
        }
        switch (state) {
            case CONNECTED:
                if (buffer.readableBytes() < 2) {
                    return null;
                }
                int request = buffer.readUnsignedByte();
                if (request != 14) {
                    System.out.println("Invalid login request: " + request);
                    channel.close();
                    return null;
                }
                buffer.readUnsignedByte();
                channel.write(new PacketBuilder().putLong(0).put((byte) 0).putLong(new SecureRandom().nextLong()).toPacket());
                state = LoginState.LOGGING_IN;
                return null;
            case LOGGING_IN:
                if (buffer.readableBytes() < 2) {
                    return null;
                }
                int loginType = buffer.readByte();
                if (loginType != 16 && loginType != 18) {
                    System.out.println("Invalid login type: " + loginType);
                    channel.close();
                    return null;
                }
                int blockLength = buffer.readByte() & 0xff;
                if (buffer.readableBytes() < blockLength) {
                    return null;
                }
                buffer.readByte();
                int clientRevision = buffer.readShort();
                buffer.readByte();
                for (int i = 0; i < 9; i++) {
                    buffer.readInt();
                }
                buffer.readByte();
                int rsaOpcode = buffer.readByte();
                if (rsaOpcode != 10) {
                    System.out.println("Unable to decode RSA block properly!");
                    channel.close();
                    return null;
                }
                final long clientHalf = buffer.readLong();
                final long serverHalf = buffer.readLong();
                final int[] isaacSeed = {(int) (clientHalf >> 32), (int) clientHalf, (int) (serverHalf >> 32), (int) serverHalf};
                final ISAAC inCipher = new ISAAC(isaacSeed);
                for (int i = 0; i < isaacSeed.length; i++) {
                    isaacSeed[i] += 50;
                }
                final ISAAC outCipher = new ISAAC(isaacSeed);
                final int version = buffer.readInt();
                final String name = NetUtilities.formatPlayerName(NetUtilities.getRS2String(buffer));
                final String pass = NetUtilities.getRS2String(buffer);
                channel.getPipeline().replace("decoder", "decoder", new RS2ProtocolDecoder(inCipher));
                return login(channel, inCipher, outCipher, version, name, pass, clientRevision);
        }
        return null;
    }

    private static Player login(Channel channel, ISAAC inCipher, ISAAC outCipher, int version, String name, String pass, int clientRevision) {
        int returnCode = 2;
        if (!name.matches("[A-Za-z0-9 ]+")) {
            returnCode = 4;
        }
        if (name.length() > 12) {
            returnCode = 8;
        }
        Player player = new Player(channel, -1);
        player.playerName = name;
        player.playerName2 = player.playerName;
        player.playerPass = pass;
        player.outStream.packetEncryption = outCipher;
        player.saveCharacter = false;
        player.isActive = true;
        player.setClientRevision(clientRevision);
        // if (Connection.isNamedBanned(cl.playerName)) {
        //         returnCode = 4;
        // }
        if (PlayerHandler.isPlayerOn(name)) {
            returnCode = 5;
        }
        if (PlayerHandler.getPlayerCount() >= Constants.MAX_PLAYERS) {
            returnCode = 7;
        }
        if (Zap.updateServer) {
            returnCode = 14;
        }
        if (returnCode == 2) {
            int load = PlayerSave.loadGame(player, player.playerName, player.playerPass);
            if (load == 0) {
                //  cl.addStarter = true;
            }
            if (load == 3) {
                returnCode = 3;
                // cl.saveFile = false;
            } else {
                /* for (int i = 0; i < cl.playerEquipment.length; i++) {
                 if (cl.playerEquipment[i] == 0) {
                 cl.playerEquipment[i] = -1;
                 cl.playerEquipmentN[i] = 0;
                 }
                 }*/
                if (!PlayerHandler.newPlayerClient(player)) {
                    returnCode = 7;
                    //cl.saveFile = false;
                } else {
                    // cl.saveFile = true;
                }
            }
        }
        if (returnCode == 2) {
            player.saveCharacter = true;
            player.packetType = -1;
            player.packetSize = 0;
            final PacketBuilder bldr = new PacketBuilder();
            bldr.put((byte) 2);
            if (player.playerRights == 3) {
                bldr.put((byte) 2);
            } else {
                bldr.put((byte) player.playerRights);
            }
            bldr.put((byte) 0);
            channel.write(bldr.toPacket());
        } else {
            System.out.println("returncode:" + returnCode);
            sendReturnCode(channel, returnCode);
            return null;
        }
        synchronized (PlayerHandler.lock) {
            player.initialize();
            player.initialized = true;
        }
        return player;
    }

    public static void sendReturnCode(final Channel channel, final int code) {
        channel.write(new PacketBuilder().put((byte) code).toPacket()).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(final ChannelFuture arg0) throws Exception {
                arg0.getChannel().close();
            }
        });
    }
}
