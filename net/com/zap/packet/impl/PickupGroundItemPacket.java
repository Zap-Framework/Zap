package com.zap.packet.impl;

import com.zap.game.entity.player.Player;
import com.zap.game.map.Location;
import com.zap.packet.Packet;
import com.zap.packet.PacketHandler;

public class PickupGroundItemPacket implements PacketHandler {

	@Override
	public void handlePacket(final Player player, Packet packet) {
		final int itemY = packet.getLEShort();
		final int itemIndex = packet.getUnsignedShort();
		final int itemX = packet.getLEShort();

		final Location location = new Location(itemX, itemY, player.getLocation().getH());
		
		
	}

}
