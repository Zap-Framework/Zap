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

public class PlayerInteractionPacket implements PacketHandler {

	public static final int FOLLOW = 39;
	public static final int ATTACK = 73;
	public static final int MAGIC_ON_PLAYER = 249;
	public static final int USE_ITEM_ON_PLAYER = 14;

	@Override
	public void handlePacket(Player player, Packet packet) {
		switch (packet.getOpcode()) {
		case ATTACK:
			handleAttackOption(player, packet);
			break;
		case FOLLOW:
			handleFollowOption(player, packet);
			break;
		case MAGIC_ON_PLAYER:
			handleMagicOnPlayer(player, packet);
			break;
		}

	}

	private void handleMagicOnPlayer(Player player, Packet packet) {
		int playerIndex = packet.getUnsignedShortA();
		int castingSpell = packet.getLEShort();
		//Player otherPlayer = Game.playerRepository.get(playerIndex);
		
	}

	private void handleAttackOption(Player player, Packet packet) {
		int playerIndex = packet.getLEShort();
		//Player otherPlayer = Game.playerRepository.get(playerIndex);
		
		
	}

	private void handleFollowOption(Player player, Packet packet) {
		final int followPlayerIndex = packet.getLEShort();
		//Player otherPlayer = Game.playerRepository.get(followPlayerIndex);
		
	}

}
