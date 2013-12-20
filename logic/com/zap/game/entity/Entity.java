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
package com.zap.game.entity;

import com.zap.game.map.Location;

/**
 *
 * @author Faris
 */
public class Entity {
    
    /**
     * The Current Location of the Entity
     */
    private Location location;
    
    /**
     * The Type of Entity this instance is 
     */
    private Type type;
    
    /**
     * The Unique user Identity
     */
    private int entityUID;

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return the entityUID
     */
    public int getEntityUID() {
        return entityUID;
    }

    /**
     * @param entityUID the entityUID to set
     */
    public void setEntityUID(int entityUID) {
        this.entityUID = entityUID;
    }
    
    public enum Type {
        NPC , PLAYER;
    }

}
