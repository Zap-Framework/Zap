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

import com.zap.game.update.UpdateExecutor;
import java.util.concurrent.Phaser;

/**
 * A {@link SynchronizationTask} which wraps around another
 * {@link SynchronizationTask} and notifies the specified {@link Phaser} when
 * the task has completed by calling {@link Phaser#arriveAndDeregister()}.
 * <p>
 * The phaser must have already registered this task. This can be done using
 * the {@link Phaser#register()} or {@link Phaser#bulkRegister(int)} methods.
 * @author Graham
 */
public final class PhasedUpdateTask extends UpdateExecutor {

	/**
	 * The phaser.
	 */
	private final Phaser phaser;

	/**
	 * The task.
	 */
	private final UpdateExecutor task;

	/**
	 * Creates the phased synchronization task.
	 * @param phaser The phaser.
	 * @param task The task.
	 */
	public PhasedUpdateTask(Phaser phaser, UpdateExecutor task) {
		this.phaser = phaser;
		this.task = task;
	}

	@Override
	public void run() {
		try {
			task.run();
		} finally {
			phaser.arriveAndDeregister();
		}
	}

}
