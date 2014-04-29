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

import com.zap.Zap;
import com.zap.game.entity.player.Player;
import com.zap.game.update.UpdateExecutor;
import com.zap.game.update.UpdateTask;
import com.zap.util.NamedThreadFactory;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadFactory;

/**
 *
 * @author Faris
 */
public class ParallelUpdateTask extends UpdateTask {

    /**
	 * The executor service.
	 */
	private final ExecutorService executor;

	/**
	 * The phaser.
	 */
	private final Phaser phaser = new Phaser(1);

	/**
	 * Creates the parallel client synchronizer backed by a thread pool with a
	 * number of threads equal to the number of processing cores available
	 * (this is found by the {@link Runtime#availableProcessors()} method.
	 */
	public ParallelUpdateTask() {
		int processors = Runtime.getRuntime().availableProcessors();
		ThreadFactory factory = new NamedThreadFactory("ClientSynchronizer");
		executor = Executors.newFixedThreadPool(processors, factory);
	}

	@Override
	public void synchronize() {
		List<Player> players = Zap.getWorld().getPlayerList();
		int playerCount = players.size();

		phaser.bulkRegister(playerCount);
		for (Player player : players) {
			UpdateExecutor task = new PreUpdateExecutor(player);
			executor.submit(new PhasedUpdateTask(phaser, task));
		}
		phaser.arriveAndAwaitAdvance();

		phaser.bulkRegister(playerCount);
		for (Player player : players) {
			UpdateExecutor task = new RealUpdateExecutor(player);
			executor.submit(new PhasedUpdateTask(phaser, task));
		}
		phaser.arriveAndAwaitAdvance();

		phaser.bulkRegister(playerCount);
		for (Player player : players) {
			UpdateExecutor task = new PostUpdateExecutor(player);
			executor.submit(new PhasedUpdateTask(phaser, task));
		}
		phaser.arriveAndAwaitAdvance();
	}

}
