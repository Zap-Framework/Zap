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
package com.zap;

import com.sun.xml.internal.ws.api.message.Packet;
import com.zap.game.entity.player.Player;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 *
 * @author Faris
 */
public class ChannelServer extends SimpleChannelHandler {

    private Session session = null;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        if (e.getMessage() instanceof Player) {
            session.setClient((Player) e.getMessage());
        } else if (e.getMessage() instanceof Packet) {
            if (session.getPlayer() != null) {
                session.getPlayer().queueMessage((Packet) e.getMessage());
            }
        }
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        if (session == null) {
            session = new Session(ctx.getChannel());
        }
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        if (session != null) {
            Player player = session.getPlayer();
            if (player != null) {
                player.disconnected = true;
            }
            session = null;
        }
    }
}
