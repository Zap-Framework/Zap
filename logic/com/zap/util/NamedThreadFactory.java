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
package com.zap.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A {@link ThreadFactory} which gives each thread a unique name made up of the
 * name supplied in the constructor and postfixed with an id.
 * <p>
 * For example, if the name {@code MyThread} was given and a third thread was
 * created by the factory, the resulting name would be {@code MyThread [id=2]}.
 * @author Graham
 */
public final class NamedThreadFactory implements ThreadFactory {

	/**
	 * The unique name.
	 */
	private final String name;

	/**
	 * The next id.
	 */
	private AtomicInteger id = new AtomicInteger(0);

	/**
	 * Creates the named thread factory.
	 * @param name The unique name.
	 */
	public NamedThreadFactory(String name) {
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable runnable) {
		int currentId = id.getAndIncrement();
		return new Thread(runnable, name + " [id=" + currentId + "]");
	}

}
