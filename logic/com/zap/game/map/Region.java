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

/**
 *
 * @author Faris
 */
public class Region {
    
    public Region(Location location){
        this.location = location;
    }
    
    private Location location;
    
    
    private Location getLocation(){
        return location;
    }
    
    /**
     * Gets the region coordinate of this location.
     * @return the region x coordinate
     */
    public int regionX() {
        return (getLocation().x >> 3) - 6;
    }

    /**
     * Gets the region coordinate of this location.
     * @return the region y coordinate
     */
    public int regionY() {
        return (getLocation().y >> 3) - 6;
    }

    /**
     * Gets the local coordinate of this location.
     * @return the local x coordinate
     */
    public int localX() {
        return localX(getLocation());
    }

    /**
     * Gets the local coordinate of this location.
     * @return the local y coordinate
     */
    public int localY() {
        return localY(getLocation());
    }

    /**
     * Gets the relative local coordinate to another region.
     * @return the local x coordinate
     */
    public int localX(Location location) {
        return location.x - 8 * location.getRegion().regionX();
    }

    /**
     * Gets the relative local coordinate to another region.
     * @return the local y coordinate
     */
    public int localY(Location location) {
        return location.y - 8 * location.getRegion().regionY();
    }

}
