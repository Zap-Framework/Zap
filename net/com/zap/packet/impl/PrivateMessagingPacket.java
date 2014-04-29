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
import com.zap.packet.Packet;
import com.zap.packet.PacketHandler;

/**
 * Private messaging packet handler.
 * 
 * @author Arithium
 */
public class PrivateMessagingPacket implements PacketHandler {

	@Override
	public void handlePacket(Player player, Packet packet) {
		long username;

		switch (packet.getOpcode()) {
		/*
		 * Add friend packet.
		 */
		case ADD_FRIEND_OPCODE:
			
			break;

		/*
		 * Add ignore packet.
		 */
		case ADD_IGNORE_OPCODE:
			
			break;

		/*
		 * Remove friend packet.
		 */
		case REMOVE_FRIEND_OPCODE:
			break;

		/*
		 * Remove ignore packet.
		 */
		case REMOVE_IGNORE_OPCODE:
			break;
		case SEND_PM_OPCODE:
			break;
		}
	}

	public static final int ADD_FRIEND_OPCODE = 188;
	public static final int REMOVE_FRIEND_OPCODE = 215;
	public static final int ADD_IGNORE_OPCODE = 133;
	public static final int REMOVE_IGNORE_OPCODE = 74;
	public static final int SEND_PM_OPCODE = 126;

}