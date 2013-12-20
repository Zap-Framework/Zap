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

import com.zap.game.entity.player.Player;
import com.zap.packet.Packet;
import com.zap.packet.Packet.Type;
import net.burtlebutle.bob.rand.isaac.ISAAC;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 *
 * @author Faris
 */
public class RS2ProtocolDecoder extends FrameDecoder {

    private final ISAAC cipher;
    private int opcode = -1;
    private int size = -1;

    public RS2ProtocolDecoder(ISAAC cipher) {
        this.cipher = cipher;
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
        if (opcode == -1) {
            if (buffer.readableBytes() >= 1) {
                opcode = buffer.readByte() & 0xFF;
                opcode = (opcode - cipher.getNextKey()) & 0xFF;
                size = Player.PACKET_SIZES[opcode];
            } else {
                return null;
            }
        }
        if (size == -1) {
            if (buffer.readableBytes() >= 1) {
                size = buffer.readByte() & 0xFF;
            } else {
                return null;
            }
        }
        if (buffer.readableBytes() >= size) {
            final byte[] data = new byte[size];
            buffer.readBytes(data);
            final ChannelBuffer payload = ChannelBuffers.buffer(size);
            payload.writeBytes(data);
            try {
                return new Packet(opcode, Type.FIXED, payload);
            } finally {
                opcode = -1;
                size = -1;
            }
        }
        return null;
    }
}
