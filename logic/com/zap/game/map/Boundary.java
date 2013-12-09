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
package com.zap.game.map;

import com.zap.game.player.Player;

/**
 *
 * @author Faris
 */
public class Boundary {
    
    /**
     * The given parameters for the boundary
     * these vary per instance
     */
    private int lowestX, 
                lowestY, 
                highestX, 
                highestY;
    
    /**
     * Constructor set to assign the variables in a new instance of this class
     * @param lowest
     * @param highest 
     */
    public Boundary(Location lowest, Location highest){
        this.lowestX = lowest.getX();
        this.lowestY = lowest.getY();
        this.highestX = highest.getX();
        this.highestY = highest.getY();
    }
    
    /**
     * Standard method for usage of this class
     * @param player
     * @return 
     */
    public boolean withinBoundry(Player player){
        return (player.getLocation().getX() >= lowestX) & (player.getLocation().getX() <= highestX) & (player.getLocation().getY() >= lowestY) & (player.getLocation().getY() <= highestY);
    }

}
