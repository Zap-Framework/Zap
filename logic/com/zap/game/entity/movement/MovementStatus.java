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
package com.zap.game.entity.movement;

/**
 *
 * @author Faris
 */
public enum MovementStatus {
    
    /**
     * This entity is currently moving
     */
    WALKING,
    
    /**
     * This Entity is still and able to move
     */
    STILL,
    
    /**
     * This Entity may be confused or busy E.G Agility task
     */
    BUSY;

}
