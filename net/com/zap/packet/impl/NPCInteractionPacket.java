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

import com.dors.job.task.Task;
import com.zap.game.entity.npc.NPC;
import com.zap.game.entity.player.Player;
import com.zap.game.map.Location;
import com.zap.packet.Packet;
import com.zap.packet.PacketHandler;

/**
 * 
 * @author Faris
 */
public class NPCInteractionPacket implements PacketHandler {

	public static final int FIRST_CLICK = 155;
	public static final int SECOND_CLICK = 17;
	public static final int THIRD_CLICK = 21;
	public static final int FOURTH_CLICK = 230;
	public static final int ATTACK = 72;
	public static final int MAGIC_ON_NPC = 131;
	public static final int ITEM_ON_NPC = 57;

	@Override
	public void handlePacket(Player player, Packet packet) {
		switch (packet.getOpcode()) {
		case ATTACK:
			handleNpcAttack(player, packet);
			break;
		case FIRST_CLICK:
			handleFirstClick(player, packet);
			break;
		case SECOND_CLICK:
			handleSecondClick(player, packet);
			break;
		case THIRD_CLICK:
			handleThirdClick(player, packet);
			break;
		case FOURTH_CLICK:
			handleFourthClick(player, packet);
			break;
		case MAGIC_ON_NPC:
			handleMagicOnNpc(player, packet);
			break;
		case ITEM_ON_NPC:
			handleItemOnNpc(player, packet);
			break;
		}
	}

	private void handleItemOnNpc(Player player, Packet packet) {
		// TODO Auto-generated method stub

	}

	private void handleMagicOnNpc(Player player, Packet packet) {
		int npcIndex = packet.getLEShortA();
		final int spellId = packet.getUnsignedShortA();
		//NPC npc = World.get(npcIndex);
		
	}

	private void handleFourthClick(Player player, Packet packet) {
		// TODO Auto-generated method stub

	}

	private void handleThirdClick(Player player, Packet packet) {
		// TODO Auto-generated method stub

	}

	private void handleSecondClick(final Player player, Packet packet) {
		int npcIndex = packet.getLEShortA();
		//final NPC npc = Game.npcRepository.get(npcIndex);
		

	}

	private void handleFirstClick(final Player player, Packet packet) {
		int npcIndex = packet.getLEShort();
		//final NPC npc = Game.npcRepository.get(npcIndex);
		
		
	}

	private void handleNpcAttack(Player player, Packet packet) {
		int npcIndex = packet.getUnsignedShortA();
		//NPC npc = Game.npcRepository.get(npcIndex);
		
	}

}
