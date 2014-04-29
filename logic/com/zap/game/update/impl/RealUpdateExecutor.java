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
package com.zap.game.update.impl;

import com.zap.Zap;
import com.zap.game.entity.player.Player;
import com.zap.game.update.UpdateExecutor;
import com.zap.game.update.UpdateFlags.UpdateFlag;
import com.zap.packet.PacketBuilder;
import com.zap.util.Constants;
import com.zap.util.Stream;
import java.util.List;

/**
 * A {@link SynchronizationTask} which synchronizes the specified
 * {@link Player}.
 *
 * @author Graham
 */
public final class RealUpdateExecutor extends UpdateExecutor {

    private boolean teleporting = true;
    private boolean mapRegionChanging = true;
    private List<Player> localPlayers;
    public byte chatText[] = new byte[256];
    public int chatTextEffects = 0, chatTextColor = 0;
    private Stream updateBlock = new Stream(new byte[Constants.BUFFER_SIZE]);
    
    /**
     * The player.
     */
    private  Player player;

    /**
     * Creates the {@link PlayerUpdateTask} for the specified player.
     * @param player The player.
     */
    public RealUpdateExecutor(Player player) {
        this.player = player;
    }
    
    private Stream out = new Stream(new byte[Constants.BUFFER_SIZE]);

    @Override
    public void run() {
        if (mapRegionChanging){
            player.getDispatcher().sendMapRegion();
        }
		updateBlock.currentOffset = 0;
		if (Zap.updateServer) {
			getOut().createFrame(114);
			getOut().writeWordBigEndian(Zap.updateSeconds * 50 / 30);
		}
		updateThisPlayerMovement(getOut());
		//final boolean saveChatTextUpdate = isChatTextUpdateRequired();
		//setChatTextUpdateRequired(false);
		//appendPlayerUpdateBlock(updateBlock);
		//setChatTextUpdateRequired(saveChatTextUpdate);
		getOut().writeBits(8, localPlayers.size());
		final int size = localPlayers.size();
		populateRegion(getOut(), updateBlock);
		if (updateBlock.currentOffset > 0) {
			getOut().writeBits(11, 2047);
			getOut().finishBitAccess();

			getOut().writeBytes(updateBlock.buffer, updateBlock.currentOffset, 0);
		} else {
			getOut().finishBitAccess();
		}

		getOut().endFrameVarSizeWord();
    }

    private void populateRegion(Stream out, Stream block) {
        for (Player player : Zap.getWorld().getPlayerList()) {
            if (player.getUpdater().localPlayers.size() >= 255) {
                break;
            }
            if (player == null) {
                continue;
            }
            if (player == player) {
                continue;
            }
            if (!player.getUpdater().localPlayers.contains(player) && player.getLocation().withinDistance(player.getLocation())) {
                player.getUpdater().localPlayers.add(player);
                addPlayer(out, player);
                updateGivenPlayer(block, player, true);
            }
        }


    }

    public void addPlayer(Stream out, Player otherPlayer) {
        out.writeBits(11, otherPlayer.getIndex()); // Writing player index.
        out.writeBits(1, 1); // Update required.
        out.writeBits(1, 1); // Discard walking.
        int yPos = otherPlayer.getLocation().getY()
                - player.getLocation().getY();
        int xPos = otherPlayer.getLocation().getX()
                - player.getLocation().getX();
        out.writeBits(5, yPos); // The relative coordinates.
        out.writeBits(5, xPos); // The relative coordinates.
    }

    public void updateGivenPlayer(Stream out, Player player2,
            boolean force) {
        if (!player.getUpdateFlags().isUpdateRequired() && !force) {
            return;
        }
        int mask = 0x0;
        if (player.getUpdateFlags().get(UpdateFlag.FORCE_MOVEMENT)) {
            mask |= UpdateFlag.FORCE_MOVEMENT.getMask();
        }
        if (player.getUpdateFlags().get(UpdateFlag.GRAPHICS)) {
            mask |= UpdateFlag.GRAPHICS.getMask();
        }
        if (player.getUpdateFlags().get(UpdateFlag.ANIMATION)) {
            mask |= UpdateFlag.ANIMATION.getMask();
        }
        if (player.getUpdateFlags().get(UpdateFlag.FORCED_CHAT)) {
            mask |= UpdateFlag.FORCED_CHAT.getMask();
        }
        if (player.getUpdateFlags().get(UpdateFlag.CHAT) && player != player2) {
            mask |= UpdateFlag.CHAT.getMask();
        }
        if (player.getUpdateFlags().get(UpdateFlag.FACE_ENTITY)) {
            mask |= 0x1;
        }
        if (player.getUpdateFlags().get(UpdateFlag.APPEARANCE) || force) {
            mask |= UpdateFlag.APPEARANCE.getMask();
        }
        if (player.getUpdateFlags().get(UpdateFlag.FACE_COORDINATE)) {
            mask |= UpdateFlag.FACE_COORDINATE.getMask();
        }
        if (player.getUpdateFlags().get(UpdateFlag.HIT)) {
            mask |= UpdateFlag.HIT.getMask();
        }
        if (player.getUpdateFlags().get(UpdateFlag.HIT_2)) {
            mask |= UpdateFlag.HIT_2.getMask();
        }
        if (mask >= 0x100) {
            mask |= 0x40;
            out.writeByte(mask & 0xFF);
            out.writeByte(mask >> 8);
        } else {
            out.writeByte(mask);
        }
        checkRequiredUpdates(out, player2, force);
    }

    public void updateThisPlayerMovement(Stream out) {
        if (isTeleporting() || isMapRegionChanging()) {
            out.writeBits(1, 1); // Update Required
            out.writeBits(2, 3); // Player Teleported
            out.writeBits(2, player.getLocation().getH()); // current height
            out.writeBits(1, isTeleporting()); // teleporting);
            out.writeBits(1, player.getUpdateFlags().isUpdateRequired()); // update														// required
            out.writeBits(7, player.getLocation().localY());
            out.writeBits(7, player.getLocation().localX());
        } else {
            if (player.getStepSynchronizer().walkingDirection() == -1) {
                if (player.getUpdateFlags().isUpdateRequired()) {
                    out.writeBits(1, 1); // update required
                    out.writeBits(2, 0); // we didn't move
                } else {
                    out.writeBits(1, 0); // Nothing changed
                }
            } else {
                if (player.getStepSynchronizer().runningDirection() == -1) {
                    out.writeBits(1, 1); // this is update required...
                    out.writeBits(2, 1); // walking
                    out.writeBits(3, player.getStepSynchronizer()
                            .walkingDirection()); // Direction
                    out.writeBits(1, player.getUpdateFlags().isUpdateRequired()); // Update
                    // block
                } else {
                    out.writeBits(1, 1); // updating required
                    out.writeBits(2, 2); // running - 2 seconds
                    out.writeBits(3, player.getStepSynchronizer()
                            .walkingDirection()); // Walking
                    out.writeBits(3, player.getStepSynchronizer()
                            .runningDirection()); // Running
                    out.writeBits(1, player.getUpdateFlags().isUpdateRequired()); // Update
                    // block
                }
            }
        }
    }

    private void checkRequiredUpdates(Stream out, Player player2, boolean force) {
        if (player.getUpdateFlags().get(UpdateFlag.GRAPHICS)) {
            //appendGraphicMask(player, out);
        }
        if (player.getUpdateFlags().get(UpdateFlag.ANIMATION)) {
            //appendAnimationMask(player, out);
        }
        if (player.getUpdateFlags().get(UpdateFlag.FORCED_CHAT)) {
            //out.putRS2String(player.getUpdateFlags().getForceChatMessage());
        }
        if (player.getUpdateFlags().get(UpdateFlag.CHAT) && player != player2) {
            // updatePlayerChat(out, player);
        }
        if (player.getUpdateFlags().get(UpdateFlag.APPEARANCE) || force) {
            //updatePlayerAppearance(out, player);
        }
        if (player.getUpdateFlags().get(UpdateFlag.FACE_ENTITY)) {
            //out.putLEShort(player.getInteractingEntityIndex());
        }
        if (player.getUpdateFlags().get(UpdateFlag.FACE_COORDINATE)) {
           // out.putLEShort(player.getUpdateFlags().getFaceLocation().getX() * 2 + 1);
           // out.putLEShort(player.getUpdateFlags().getFaceLocation().getY() * 2 + 1);
        }
        if (player.getUpdateFlags().get(UpdateFlag.HIT)) {
            //updateHit(out, player);
        }
        if (player.getUpdateFlags().get(UpdateFlag.HIT_2)) {
            //updatingHit2(out, player);
        }
    }

    private void updatePlayerMovement(Stream out, Player player) {
        if (player.getStepSynchronizer().walkingDirection() == -1) {
            if (player.getUpdateFlags().isUpdateRequired()) {
                out.writeBits(1, 1); // Update required
                out.writeBits(2, 0); // No movement
            } else {
                out.writeBits(1, 0); // Nothing changed
            }
        } else if (player.getStepSynchronizer().runningDirection() == -1) {
            out.writeBits(1, 1); // Update required
            out.writeBits(2, 1); // Player walking one tile
            out.writeBits(3, player.getStepSynchronizer().walkingDirection()); // Walking
            out.writeBits(1, player.getUpdateFlags().isUpdateRequired()); // Update
        } else {
            out.writeBits(1, 1); // Update Required
            out.writeBits(2, 2); // Moved two tiles
            out.writeBits(3, player.getStepSynchronizer().walkingDirection()); // Walking
            out.writeBits(3, player.getStepSynchronizer().runningDirection()); // Running
            out.writeBits(1, player.getUpdateFlags().isUpdateRequired()); // Update
        }
    }

    /*public void updatePlayerChat(Stream out, Player player) {
        int effects = ((player.getUpdater().chatTextColor & 0xff) << 8)
                + (player.getUpdater().chatTextEffects & 0xff);
        out.putLEShort(effects);
        out.putByte(player.getAuth().getPlayerRightsAsInt());
        out.putByteC(player.getUpdater().chatText.length);
        out.put(player.getUpdater().chatText);
    }*/

    /**
     * @return the mapRegionChanging
     */
    public boolean isMapRegionChanging() {
        return mapRegionChanging;
    }

    /**
     * @param mapRegionChanging the mapRegionChanging to set
     */
    public void setMapRegionChanging(boolean mapRegionChanging) {
        this.mapRegionChanging = mapRegionChanging;
    }

    public void resetUpdateVars() {
        chatTextEffects = chatTextColor = 0;
        chatText = new byte[256];
        setTeleporting(false);
        setMapRegionChanging(false);
        player.getUpdateFlags().reset();
        player.getStepSynchronizer().walkingDirection(-1).runningDirection(-1);
    }

    /**
     * @return the teleporting
     */
    public boolean isTeleporting() {
        return teleporting;
    }

    /**
     * @param teleporting the teleporting to set
     */
    public void setTeleporting(boolean teleporting) {
        this.teleporting = teleporting;
    }

    /**
     * @return the outStream
     */
    public Stream getOut() {
        return out;
    }
}
