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
import com.zap.game.entity.player.Player;
import com.zap.game.map.Location;
import com.zap.packet.Packet;
import com.zap.packet.PacketHandler;

/**
 *
 * @author Faris
 */
public class ObjectInteractionPacket implements PacketHandler {
	
	public static final int FIRST_CLICK_OBJECT_OPCODE = 132,
			SECOND_CLICK_OBJECT_OPCODE = 252, THIRD_CLICK_OBJECT_OPCODE = 70;

    @Override
    public void handlePacket(Player player, Packet packet) {
        switch (packet.getOpcode()) {
        case FIRST_CLICK_OBJECT_OPCODE:
        	handleObjectFirstClick(player, packet);
        	break;
        case SECOND_CLICK_OBJECT_OPCODE:
        	handleObjectSecondClick(player, packet);
        	break;
        	
        case THIRD_CLICK_OBJECT_OPCODE:
        	handleObjectThirdClick(player, packet);
        	break;
        }
    }

	private void handleObjectFirstClick(final Player player, Packet packet) {
		final int objectX = packet.getLEShortA();
		final int objectId = packet.getUnsignedShort();
		final int objectY = packet.getUnsignedShortA();
		
		final Location location = new Location(objectX, objectY, player
				.getLocation().getH());
		
	}

	private void handleObjectSecondClick(final Player player, Packet packet) {
		final int objectId = packet.getLEShortA();
		final int objectY = packet.getLEShort();
		final int objectX = packet.getUnsignedShortA();
		final Location location = new Location(objectX, objectY, player
				.getLocation().getH());
		
	}

	private void handleObjectThirdClick(final Player player, Packet packet) {
		final int objectX = packet.getLEShort();
		final int objectY = packet.getUnsignedShort();
		final int objectId = packet.getLEShortA();
		final Location location = new Location(objectX, objectY, player
				.getLocation().getH());
		
	}

}
