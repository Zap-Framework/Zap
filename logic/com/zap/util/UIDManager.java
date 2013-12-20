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
package com.zap.util;

/**
 *
 * @author Faris
 */
public class UIDManager {
    
    /**
     * Stores index keys, anything nulled is an available index
     */
    public static Integer indexStore [] = new Integer[Constants.MAX_PLAYERS];
    
    private static Integer[] npcIndexStore = new Integer[Constants.MAX_NPCS];
    
    /**
     * Sets the parsed index to null for recycling
     * @param index players old index
     */
    public static void freeIndex(int index){
        indexStore[index] = null;
    }
    
    /**
     * Sets the parsed index to null for recycling
     * @param index NPCs old index
     */
    public static void freeNpcIndex(int index) {
    	npcIndexStore[index] = null;
    }
    
    /**
     * Loops through available index
     * @returns first unassigned index
     */
    public static Integer getIndex(){
        for(int i=1; i<indexStore.length; i++){
            if(indexStore[i] == null){
                indexStore[i] = i;
                return indexStore[i];
            }
        }
        return null;
    }
    
    /**
     * Loops through available index
     * @returns first unassigned index
     */
    public static Integer getNpcIndex() {
    	for (int i = 1; i < npcIndexStore.length; i++) {
    		if (npcIndexStore[i] == null) {
    			npcIndexStore[i] = i;
    			return npcIndexStore[i];
    		}
    	}
    	return null;
    }

}
