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
 * Item action packet handler.
 * 
 * @author Faris
 */
public class ItemActionPacket implements PacketHandler {

	@Override
	public void handlePacket(Player player, Packet packet) {
		switch (packet.getOpcode()) {
		case FIRST_ITEM_ACTION_OPCODE:
			handleFirstItemAction(player, packet);
			break;
		case SECOND_ITEM_ACTION_OPCODE:
			handleSecondItemAction(player, packet);
			break;
		case THIRD_ITEM_ACTION_OPCODE:
			handleThirdItemAction(player, packet);
			break;
		case FOURTH_ITEM_ACTION_OPCODE:
			handleFourthItemAction(player, packet);
			break;
		}
	}

	/**
	 * Handles first item action.
	 * 
	 * @param player
	 *            the player reference
	 * 
	 * @param packet
	 *            the packet
	 */
	public void handleFirstItemAction(Player player, Packet packet) {
		int interfaceIndex = packet.getUnsignedShortA();
		int itemSlot = packet.getUnsignedShortA();
		int itemIndex = packet.getUnsignedShortA();
		switch (interfaceIndex) {
		
		}
	}

	/**
	 * Handles second item action.
	 * 
	 * @param player
	 *            the player reference
	 * 
	 * @param packet
	 *            the packet
	 */
	public void handleSecondItemAction(Player player, Packet packet) {
		int interfaceIndex = packet.getLEShortA();
		int itemIndex = packet.getLEShortA();
		int itemSlot = packet.getLEShort();
		switch (interfaceIndex) {
		
		}
	}

	/**
	 * Handles third item action.
	 * 
	 * @param player
	 *            the player reference
	 * 
	 * @param packet
	 *            the packet
	 */
	public void handleThirdItemAction(Player player, Packet packet) {
		int interfaceIndex = packet.getLEShort();
		int itemIndex = packet.getUnsignedShortA();
		int itemSlot = packet.getUnsignedShortA();

		switch (interfaceIndex) {
		
		}
	}

	/**
	 * Handles fourth item action.
	 * 
	 * @param player
	 *            the player reference
	 * 
	 * @param packet
	 *            the packet
	 */
	public void handleFourthItemAction(Player player, Packet packet) {
		int itemSlot = packet.getUnsignedShortA();
		int interfaceIndex = packet.getUnsignedShort();
		int itemIndex = packet.getUnsignedShortA();

		switch (interfaceIndex) {
		
		}
	}

	public static final int FIRST_ITEM_ACTION_OPCODE = 145;
	public static final int SECOND_ITEM_ACTION_OPCODE = 117;
	public static final int THIRD_ITEM_ACTION_OPCODE = 43;
	public static final int FOURTH_ITEM_ACTION_OPCODE = 129;

}