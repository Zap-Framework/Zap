/**
 *  This file is part of Zap Framework.
 * 
 *  Zap is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Zap is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Zap.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.zap.packet;

import com.zap.Session;
import com.zap.packet.impl.ActionButtonPacket;
import com.zap.packet.impl.AppearanceChangePacket;
import com.zap.packet.impl.ClickingIngamePacket;
import com.zap.packet.impl.CommandPacket;
import com.zap.packet.impl.DialoguePacket;
import com.zap.packet.impl.DropItemPacket;
import com.zap.packet.impl.EquipPacketHandler;
import com.zap.packet.impl.IncomingChatPacket;
import com.zap.packet.impl.ItemActionPacket;
import com.zap.packet.impl.NPCInteractionPacket;
import com.zap.packet.impl.ObjectInteractionPacket;
import com.zap.packet.impl.PlayerInteractionPacket;
import com.zap.packet.impl.PrivateMessagingPacket;
import com.zap.packet.impl.RegionChangePacket;
import com.zap.packet.impl.UseItemPacketHandler;
import com.zap.packet.impl.WalkingUpdatePacket;
import java.util.HashMap;
import java.util.Map;

/**
 * Packet handlers manager.
 *
 * @author Faris
 */
public class PacketType {

    private static Map<Integer, PacketHandler> incomingPacket = new HashMap<Integer, PacketHandler>();

    /**
     * Handles an incoming packet
     * @param channelContext the channel socket context
     * @param packet the packet
     */
    public static void handlePacket(Session session, Packet packet) {
        PacketHandler packets = incomingPacket.get(packet.getOpcode());
        if (packets == null) {
            // System.out.println("Unhandled Packet Type: " + packet.opcode());
            return;
        }
        if (packet.getOpcode() < 0 || packet.getOpcode() > 256) {
            return;
        }
        try {
            packets.handlePacket(session.getPlayer(), packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Static constructor for packet handlers.
     */
    static {
        incomingPacket.put(DialoguePacket.DIALOGUE_OPCODE, new DialoguePacket());
        PrivateMessagingPacket privateMessage = new PrivateMessagingPacket();
        incomingPacket.put(PrivateMessagingPacket.ADD_FRIEND_OPCODE, privateMessage);
        incomingPacket.put(PrivateMessagingPacket.ADD_IGNORE_OPCODE, privateMessage);
        incomingPacket.put(PrivateMessagingPacket.REMOVE_FRIEND_OPCODE, privateMessage);
        incomingPacket.put(PrivateMessagingPacket.REMOVE_IGNORE_OPCODE, privateMessage);
        incomingPacket.put(PrivateMessagingPacket.SEND_PM_OPCODE, privateMessage);
        incomingPacket.put(41, new EquipPacketHandler());
        incomingPacket.put(DropItemPacket.DROP_ITEM_OPCODE, new DropItemPacket());
        ItemActionPacket itemActions = new ItemActionPacket();
        incomingPacket.put(ItemActionPacket.FIRST_ITEM_ACTION_OPCODE, itemActions);
        NPCInteractionPacket npcInteraction = new NPCInteractionPacket();
        incomingPacket.put(NPCInteractionPacket.FIRST_CLICK, npcInteraction);
        incomingPacket.put(NPCInteractionPacket.SECOND_CLICK, npcInteraction);
        incomingPacket.put(NPCInteractionPacket.THIRD_CLICK, npcInteraction);
        incomingPacket.put(NPCInteractionPacket.FOURTH_CLICK, npcInteraction);
        incomingPacket.put(NPCInteractionPacket.ATTACK, npcInteraction);
        incomingPacket.put(NPCInteractionPacket.MAGIC_ON_NPC, npcInteraction);
        incomingPacket.put(NPCInteractionPacket.ITEM_ON_NPC, npcInteraction);
        ObjectInteractionPacket objectPacket = new ObjectInteractionPacket();
        incomingPacket.put(132, objectPacket);
        incomingPacket.put(252, objectPacket);
        incomingPacket.put(70, objectPacket);
        incomingPacket.put(241, new ClickingIngamePacket());
        PlayerInteractionPacket playerInteraction = new PlayerInteractionPacket();
        incomingPacket.put(PlayerInteractionPacket.ATTACK, playerInteraction);
        incomingPacket.put(PlayerInteractionPacket.FOLLOW, playerInteraction);
        incomingPacket.put(185, new ActionButtonPacket());
        incomingPacket.put(103, new CommandPacket());
        incomingPacket.put(101, new AppearanceChangePacket());
        incomingPacket.put(4, new IncomingChatPacket());
        incomingPacket.put(121, new RegionChangePacket());
        incomingPacket.put(WalkingUpdatePacket.COMMAND_MOVEMENT_OPCODE, new WalkingUpdatePacket());
        incomingPacket.put(WalkingUpdatePacket.GAME_MOVEMENT_OPCODE, new WalkingUpdatePacket());
        incomingPacket.put(WalkingUpdatePacket.MINIMAP_MOVEMENT_OPCODE, new WalkingUpdatePacket());
        UseItemPacketHandler useItem = new UseItemPacketHandler();
        incomingPacket.put(UseItemPacketHandler.USE_ITEM, useItem);
    }
}