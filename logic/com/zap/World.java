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
package com.zap;

import com.zap.engine.EngineServer;
import com.zap.engine.ext.CleanUpEngine;
import com.zap.engine.ext.GameEngine;
import com.zap.engine.ext.MinigameEngine;
import com.zap.engine.ext.NetworkEngine;
import com.zap.game.entity.Entity;
import com.zap.game.entity.npc.NPC;
import com.zap.game.entity.player.Player;
import com.zap.util.UIDManager;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Faris
 */
public class World {
    
    public World(WorldType type, int port){
        entityRegistry = Collections.synchronizedList(new LinkedList());
        playerRegistry = Collections.synchronizedList(new LinkedList());
        npcRegistry = Collections.synchronizedList(new LinkedList());
        setWorldType(type);
        setPort(port);
        prepareWorld();
    }
    
    /**
     * The Engine Server
     */
    public static EngineServer engineServer;
    
    /**
     * A logger used to report error messages.
     */
    public static final Logger logger = Logger.getLogger(Zap.class.getName());
    
    /**
     * The World Type Initialized
     */
    private WorldType worldType;
    
    /**
     * The port this world is registered to
     */
    private int port;
    
    /**
     * List containing all online Mobile Entities
     */
    private List<Entity> entityRegistry, playerRegistry, npcRegistry;
    
    /**
     * Returns a new list with all types Casted to Players
     * @return the newly populated list
     */
    public List<Player> getPlayerList(){
        List<Player> list = new LinkedList();
        for(Entity entity : playerRegistry){
            if (entity == null){
                continue;
            }
            list.add((Player)entity);
        }
        return list;
    }
    
    /**
     * Returns a new list with all types Casted to NPCs
     * @return the newly populated list
     */
    public List<NPC> getNPCList(){
        List<NPC> list = new LinkedList();
        for(Entity entity : npcRegistry){
            if (entity == null){
                continue;
            }
            list.add((NPC)entity);
        }
        return list;
    }
    
    /**
     * Adds a player to the online list
     * @param player 
     */
    public synchronized boolean registerEntity(Entity entity){
        entityRegistry.add(entity);
        if(entity instanceof Player){
            Player player = (Player) entity;
            player.setEntityUID(UIDManager.getIndex());
            player.isActive = true;
            player.connectedFrom = ((InetSocketAddress) player.getSession().getChannel().getRemoteAddress()).getAddress().getHostAddress();
            playerRegistry.add(entity);
        } else if ( entity instanceof NPC) {
            npcRegistry.add(entity);
        }
        return true;
    }
    
    /**
     * Removes a Player from the game safely
     * @param player 
     */
    public synchronized void deregisterPlayer(Entity entity){
        entityRegistry.remove(entity);
        if(entity instanceof Player){
            playerRegistry.remove(entity);
        } else if ( entity instanceof NPC) {
            npcRegistry.remove(entity);
        }
        DestroyExistance(entity);
    }
    
    /**
     * Finds the player by name matching string specified
     * @param name is the string to search for amidst the online
     * @return player if found or null
     */
    public Player getPlayerByName(String name){
        for(Player player : getPlayerList()){
            if(name.equals(player.getPlayerName())){
                return player;
            }
        }
        return null;
    }
    
    /**
     * Finds the entity by UID matching integer specified
     * @param uid is the integer to search for amidst the online
     * @return entity if found or null
     */
    public Entity getEntityByUID(int uid){
        for(Entity entity : entityRegistry){
            if(uid == (entity.getEntityUID())){
                return entity;
            }
        }
        return null;
    }
    
    /**
     * Finds the total amount of online players
     * @return the count
     */
    public int getOnlinePlayers(){
        return this.getPlayerList().size();
    }
    
    /**
     * Finds the amount of total online NPCs
     * @return the count
     */
    public int getOnlineNPCs(){
        return this.getNPCList().size();
    }
    
    /**
     * Handles appropriate protocol in sagely destroying an online object
     * @param entity parameter
     */
     private static void DestroyExistance(Entity entity) {
        switch(entity.getType()){
            case PLAYER:
                Player player = (Player)entity;
                break;
            case NPC:
                NPC npc = (NPC)entity;
                break;
        }
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    private void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the worldType
     */
    public WorldType getWorldType() {
        return worldType;
    }

    /**
     * @param worldType the worldType to set
     */
    private void setWorldType(WorldType worldType) {
        this.worldType = worldType;
    }

    /**
     * Starts off all necessary processes for the world to function correctly
     */
    private void prepareWorld() {
        logger.info("Starting up Zap Main World on Port:" + getPort());
        startEngineServer();
        logger.info("Fired up Zap Engine Server...");
    }
     
    /**
     * Starts all required Engines with Engine Server
     */
    private void startEngineServer(){
        engineServer = new EngineServer();
        engineServer.initialize();
        EngineServer.submitEngine(new NetworkEngine(getPort()));
        logger.info("Fired up Netty Login Server...");
        EngineServer.submitEngine(new MinigameEngine());
        EngineServer.submitEngine(new GameEngine());
        EngineServer.submitEngine(new CleanUpEngine());
        logger.info("CleanUpEngine Scheduled...");
    }
    
    public enum WorldType {
     NORMAL, PVP , SPAWN;
    }

}
