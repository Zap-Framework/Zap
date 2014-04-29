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

import com.zap.game.entity.movement.MovementStatus;
import com.zap.game.entity.movement.StepSynchronizer;
import com.zap.game.map.Location;
import com.zap.game.map.Region;

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
    private EntityType type;
    
    /**
     * The Unique user Identity
     */
    private int entityUID;
    
    /**
     * The Welfare status of the Entity
     */
    private WelfareStatus welfareStatus;
    
    /**
     * The Current MovementStatus of the Entity
     */
    private MovementStatus movementStatus;
    
    /**
     * The Class which contains the movement queue
     */
    private StepSynchronizer stepSynchronizer;
    
    /**
     * The location to be moved to in the next movement phase
     */
    private Location targetLocation;
    
    /**
     * The Player / NPC this entity is talking to / in combat with
     */
    private Entity interatingEntity;
    
    /**
     * The furthest amount of steps able to be taken
     */
    private int maxMovementDistance;
    
    /**
     * Last known region found containing this entity
     */
    private Region cachedRegion;

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
    public EntityType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(EntityType type) {
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

    /**
     * @return the welfareStatus
     */
    public WelfareStatus getWelfareStatus() {
        return welfareStatus;
    }

    /**
     * @param welfareStatus the welfareStatus to set
     */
    public void setWelfareStatus(WelfareStatus welfareStatus) {
        this.welfareStatus = welfareStatus;
    }

    /**
     * @return the movementStatus
     */
    public MovementStatus getMovementStatus() {
        return movementStatus;
    }

    /**
     * @param movementStatus the movementStatus to set
     */
    public void setMovementStatus(MovementStatus movementStatus) {
        this.movementStatus = movementStatus;
    }

    /**
     * @return the targetLocation
     */
    public Location getTargetLocation() {
        return targetLocation;
    }

    /**
     * @param targetLocation the targetLocation to set
     */
    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    /**
     * @return the cachedRegion
     */
    public Region getCachedRegion() {
        return cachedRegion;
    }

    /**
     * @param cachedRegion the cachedRegion to set
     */
    public void setCachedRegion(Region cachedRegion) {
        this.cachedRegion = cachedRegion;
    }

    /**
     * @return the interatingEntity
     */
    public Entity getInteratingEntity() {
        return interatingEntity;
    }

    /**
     * @param interatingEntity the interatingEntity to set
     */
    public void setInteratingEntity(Entity interatingEntity) {
        this.interatingEntity = interatingEntity;
    }

    /**
     * @return the maxMovementDistance
     */
    public int getMaxMovementDistance() {
        return maxMovementDistance;
    }

    /**
     * @param maxMovementDistance the maxMovementDistance to set
     */
    public void setMaxMovementDistance(int maxMovementDistance) {
        this.maxMovementDistance = maxMovementDistance;
    }

    /**
     * @return the stepSynchronizer
     */
    public StepSynchronizer getStepSynchronizer() {
        return stepSynchronizer;
    }

    /**
     * @param stepSynchronizer the stepSynchronizer to set
     */
    public void setStepSynchronizer(StepSynchronizer stepSynchronizer) {
        this.stepSynchronizer = stepSynchronizer;
    }

}
