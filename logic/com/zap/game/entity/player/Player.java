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
    private String playerName;
    public String playerName2;
    public String playerPass;
    public Stream inStream = null, outStream = null;
    public boolean saveCharacter;
    public boolean isActive;
    public int packetType;
    public int packetSize;
    public int playerRights;
    public boolean initialized;
    private int clientRevision;
    public String connectedFrom;
    private Session session;

    public Player(Channel channel, int i) {
        this.setType(Type.PLAYER);
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void queueMessage(Packet packet) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void initialize() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * @return the clientVersion
     */
    public int getClientRevision() {
        return clientRevision;
    }

    /**
     * @param clientVersion the clientVersion to set
     */
    public void setClientRevision(int clientRevision) {
        this.clientRevision = clientRevision;
    }

    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @param playerName the playerName to set
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Session getSession() {
        return session;
    }

}
