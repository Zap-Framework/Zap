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
package com.zap.game.entity.player;

import com.sun.xml.internal.ws.api.message.Packet;
import com.zap.Session;
import com.zap.game.entity.Entity;
import com.zap.game.entity.EntityType;
import com.zap.game.entity.movement.StepSynchronizer;
import com.zap.game.update.UpdateFlags;
import com.zap.game.update.impl.RealUpdateExecutor;
import com.zap.packet.PacketDispatcher;
import com.zap.util.Constants;
import com.zap.util.Stream;
import org.jboss.netty.channel.Channel;

/**
 *
 * @author Faris
 */
public class Player extends Entity {
    
    public boolean disconnected;
    
    public static final int PACKET_SIZES[] = { 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, // 0
		0, 0, 0, 0, 8, 0, 6, 2, 2, 0, // 10
		0, 2, 0, 6, 0, 12, 0, 0, 0, 0, // 20
		0, 0, 0, 0, 0, 8, 4, 0, 0, 2, // 30
		2, 6, 0, 6, 0, -1, 0, 0, 0, 0, // 40
		0, 0, 0, 12, 0, 0, 0, 8, 8, 12, // 50
		8, 8, 0, 0, 0, 0, 0, 0, 0, 0, // 60
		6, 0, 2, 2, 8, 6, 0, -1, 0, 6, // 70
		0, 0, 0, 0, 0, 1, 4, 6, 0, 0, // 80
		0, 0, 0, 0, 0, 3, 0, 0, -1, 0, // 90
		0, 13, 0, -1, 0, 0, 0, 0, 0, 0,// 100
		0, 0, 0, 0, 0, 0, 0, 6, 0, 0, // 110
		1, 0, 6, 0, 0, 0, -1, 0, 2, 6, // 120
		0, 4, 6, 8, 0, 6, 0, 0, 0, 2, // 130
		0, 0, 0, 0, 0, 6, 0, 0, 0, 0, // 140
		0, 0, 1, 2, 0, 2, 6, 0, 0, 0, // 150
		0, 0, 0, 0, -1, -1, 0, 0, 0, 0,// 160
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
		0, 8, 0, 3, 0, 2, 0, 0, 8, 1, // 180
		0, 0, 12, 0, 0, 0, 0, 0, 0, 0, // 190
		2, 0, 0, 0, 0, 0, 0, 0, 4, 0, // 200
		4, 0, 0, 0, 7, 8, 0, 0, 10, 0, // 210
		0, 0, 0, 0, 0, 0, -1, 0, 6, 0, // 220
		1, 0, 0, 0, 6, 0, 6, 8, 1, 0, // 230
		0, 4, 0, 0, 0, 0, -1, 0, -1, 4,// 240
		0, 0, 6, 6, 0, 0, 0 // 250
	};
    
    public Stream inStream = new Stream(new byte[Constants.BUFFER_SIZE]);
    public boolean saveCharacter;
    public boolean isActive;
    public int packetType;
    public int packetSize;
    public boolean initialized;
    private PlayerAuth auth;
    public String connectedFrom;
    private Session session;
    private RealUpdateExecutor updater = new RealUpdateExecutor(this);
    private PacketDispatcher dispatcher = new PacketDispatcher(this);
    private UpdateFlags updateFlags = new UpdateFlags();
    private Stream outStream = updater.getOut();
    public boolean updateRequired;

    public Player(Channel channel, int i, String user, String pass) {
        this.setType(EntityType.PLAYER);
        setAuth(new PlayerAuth(user, pass));
        setSession(channel);
        this.setStepSynchronizer(new StepSynchronizer(this));
    }

    public void queueMessage(Packet packet) {
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    public void initialize() {
       // throw new UnsupportedOperationException("Not yet implemented");
    }


    public Session getSession() {
        return session;
    }
    
    /**
     * Finds the Entity class' Index
     * @return the player Index
     */
    public int getIndex(){
        return this.getIndex();
    }

    /**
     * @return the auth
     */
    public PlayerAuth getAuth() {
        return auth;
    }

    /**
     * @param auth the auth to set
     */
    public final void setAuth(PlayerAuth auth) {
        this.auth = auth;
    }

    /**
     * @param session the session to set
     */
    public final void setSession(Channel channel) {
        this.session = new Session(channel);
    }

    /**
     * @return the updateFlags
     */
    public UpdateFlags getUpdateFlags() {
        return updateFlags;
    }

    /**
     * @return the packetDispatcher
     */
    public PacketDispatcher getDispatcher() {
        return dispatcher;
    }

    public void setAppearanceUpdateRequired(boolean b) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * @return the outStream
     */
    public Stream getOutStream() {
        return outStream;
    }

    /**
     * @return the updater
     */
    public RealUpdateExecutor getUpdater() {
        return updater;
    }

}
