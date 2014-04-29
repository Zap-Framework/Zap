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
package com.zap.packet.impl;

import com.zap.game.entity.player.Player;
import com.zap.game.update.UpdateFlags.UpdateFlag;
import com.zap.packet.Packet;
import com.zap.packet.PacketHandler;

/**
 *
 * @author Faris
 */
public class AppearanceChangePacket implements PacketHandler {

    @Override
    public void handlePacket(Player player, Packet packet) {
            player.getAuth().setPlayerAppearanceIndex(0, packet.getByte());
            player.getAuth().setPlayerAppearanceIndex(1, packet.getByte());
            player.getAuth().setPlayerAppearanceIndex(7, packet.getByte());
            player.getAuth().setPlayerAppearanceIndex(2, packet.getByte());
            player.getAuth().setPlayerAppearanceIndex(3, packet.getByte());
            player.getAuth().setPlayerAppearanceIndex(4, packet.getByte());
            player.getAuth().setPlayerAppearanceIndex(5, packet.getByte());
            player.getAuth().setPlayerAppearanceIndex(6, packet.getByte());
            player.getAuth().setPlayerAppearanceIndex(8, packet.getByte());
            player.getAuth().setPlayerAppearanceIndex(9, packet.getByte());
            player.getAuth().setPlayerAppearanceIndex(10, packet.getByte());
            player.getAuth().setPlayerAppearanceIndex(11, packet.getByte());
            player.getAuth().setPlayerAppearanceIndex(12, packet.getByte());
            player.getUpdateFlags().flag(UpdateFlag.APPEARANCE);
    }

}
