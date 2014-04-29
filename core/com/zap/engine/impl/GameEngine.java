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
package com.zap.engine.impl;

import com.zap.engine.Engine;
import com.zap.game.update.UpdateTask;
import com.zap.game.update.impl.ParallelUpdateTask;
import com.zap.util.Constants;

/**
 *
 * @author Faris
 */
public class GameEngine extends Engine {
    
    public GameEngine(){
        super(Constants.GAME_EXECUTION_RATE);
    }
    
    UpdateTask task = new ParallelUpdateTask();

    @Override
    public void execution() {
        
        /**
         * Handles the Player/NPC Updating In-Game
         */
        task.synchronize();
        
    }


}
