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
package com.zap.game.update.impl;

import com.zap.game.entity.player.Player;
import com.zap.game.update.UpdateExecutor;


/**
 * A {@link SynchronizationTask} which does post-synchronization work for the
 * specified {@link Player}.
 * @author Graham
 */
public final class PostUpdateExecutor extends UpdateExecutor {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * Creates the {@link PostPlayerSynchronizationTask} for the specified
	 * player.
	 * @param player The player.
	 */
	public PostUpdateExecutor(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		
	}

}
