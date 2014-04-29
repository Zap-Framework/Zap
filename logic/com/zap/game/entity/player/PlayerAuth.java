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

import com.zap.util.NetUtilities;

/**
 * 
 * @author Faris
 */
public class PlayerAuth {

	public PlayerAuth(String username, String password) {
		this.username = username;
		this.password = password;
		this.usernameAsLong = NetUtilities.playerNameToInt64(username);
		this.playerRights = PrivilegeRank.STANDARD;
	}

	private String username, password;
        private int clientRevision;
	private long usernameAsLong;
	private PrivilegeRank playerRights;
        
        public void setPlayerRights(int rank) {
		switch(rank){
                    case 0:
                        this.playerRights = PrivilegeRank.STANDARD;
                        break;
                    case 1:
                        this.playerRights = PrivilegeRank.MODERATOR;
                        break;
                    case 2:
                        this.playerRights = PrivilegeRank.ADMINISTRATOR;
                        break;
                    case 3:
                        this.playerRights = PrivilegeRank.OWNER;
                        break;
                }
	}

	public int getPlayerRightsAsInt() {
		switch (playerRights) {
		case MODERATOR:
			return 1;
		case ADMINISTRATOR:
			return 2;
		case OWNER:
			return 3;
		default:
			return 0;
		}
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the usernameAsLong
	 */
	public long getUsernameAsLong() {
		return usernameAsLong;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	public int[] appearanceIndex = new int[13];

	private boolean changeAppearance = false;

	public int playerGender() {
		return appearanceIndex[0];
	}

	public int playerHead() {
		return appearanceIndex[1];
	}

	public int playerTorso() {
		return appearanceIndex[2];
	}

	public int playerArms() {
		return appearanceIndex[3];
	}

	public int playerHands() {
		return appearanceIndex[4];
	}

	public int playerLegs() {
		return appearanceIndex[5];
	}

	public int playerFeet() {
		return appearanceIndex[6];
	}

	public int playerJaw() {
		return appearanceIndex[7];
	}

	public int playerHairColour() {
		return appearanceIndex[8];
	}

	public int playerTorsoColour() {
		return appearanceIndex[9];
	}

	public int playerLegColour() {
		return appearanceIndex[10];
	}

	public int playerFeetColour() {
		return appearanceIndex[11];
	}

	public int playerSkinColour() {
		return appearanceIndex[12];
	}

	public int getPlayerAppearanceIndex(int index) {
		return appearanceIndex[index];
	}

	public void setPlayerAppearanceIndex(int index, int appearance) {
		this.appearanceIndex[index] = appearance;
	}

	public boolean getChangeAppearance() {
		return changeAppearance;
	}

	public void setChangeAppearance(boolean status) {
		this.changeAppearance = status;
	}

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the clientRevision
     */
    public int getClientRevision() {
        return clientRevision;
    }

    /**
     * @param clientRevision the clientRevision to set
     */
    public void setClientRevision(int clientRevision) {
        this.clientRevision = clientRevision;
    }

	public enum PrivilegeRank {
		/**
		 * A standard (rights 0) account.
		 */
		STANDARD,

		/**
		 * A player moderator (rights 1) account.
		 */
		MODERATOR,

		/**
		 * An administrator (rights 2) account.
		 */
		ADMINISTRATOR,

		/**
		 * An owner (rights 3) account.
		 */
		OWNER;
	}

}
