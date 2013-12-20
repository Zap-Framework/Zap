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
package com.zap.engine.ext;

import com.zap.engine.Engine;
import com.zap.game.content.minigame.Minigame;
import com.zap.util.Constants;
import com.zap.util.MinigameList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Faris
 */
public class MinigameEngine extends Engine {
    
    public MinigameEngine(){
        super(Constants.GAME_EXECUTION_RATE);
    }
    
    /**
     * List containing all of the active games waiting to start
     */
    public static List<Minigame> gamesToBegin = new MinigameList();
    
    /**
     * List containing all of the currently active games
     */
    public static List<Minigame> activeGames = new MinigameList();

    @Override
    public void execution() {
        if(activeGames.isEmpty()){
            return;
        }
        Iterator<Minigame> it = activeGames.iterator();
        while(it.hasNext()){
            Minigame game = it.next();
            if(game.gameShouldEnd()){
                activeGames.remove(game);
            }
            game.process();
        }
        if(gamesToBegin.isEmpty()){
            return;
        }
        for(Minigame game : gamesToBegin){
            activeGames.add(game);
        }
        gamesToBegin.clear();
    }

}
