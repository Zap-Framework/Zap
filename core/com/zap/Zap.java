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
package com.zap;

import com.zap.World.WorldType;

/**
 *
 * @author Faris
 */
public class Zap {

    /**
     * The World Instance this will take place in, for single world purposes
     * only one should exist
     */
    private static World[] world = new World[10];
    /**
     * Whether the server requires an update
     */
    public static boolean updateServer = false;

    /**
     * The main method
     * @param args parsed into the server
     */
    public static void main(String[] args) {
        new Zap().start();
    }

    /**
     * @return the world collection
     */
    public static World[] getWorlds() {
        return world;
    }

    /**
     * @return the main world
     */
    public static World getWorld() {
        return world[1];
    }

    /**
     * @return the world specified
     */
    public static World getWorld(int index) {
        return world[index];
    }

    /**
     * @param aWorld the world to set
     */
    public void setWorld(World aWorld, int index) {
        world[index] = aWorld;
    }

    /**
     * Begin Zap Framework
     */
    public void start() {
        setWorld(new World(WorldType.NORMAL, 43594), 1);
        for(int i = 2; i < 10; i++){
            world[i] = null;
        }
    }
}
