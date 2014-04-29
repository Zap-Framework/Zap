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
import com.zap.game.map.Location;
import com.zap.packet.Packet;
import com.zap.packet.PacketHandler;

/**
 * 
 * @author Faris
 */
public class WalkingUpdatePacket implements PacketHandler {

	public static final int COMMAND_MOVEMENT_OPCODE = 98;
	public static final int GAME_MOVEMENT_OPCODE = 164;
	public static final int MINIMAP_MOVEMENT_OPCODE = 248;

	@Override
	public void handlePacket(Player player, Packet packet) {
		if (packet.getOpcode() == 248) {
			//packet.length(packet.length() - 14);
		}
		

	}

}
