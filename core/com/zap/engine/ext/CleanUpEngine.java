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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faris
 */
public class CleanUpEngine extends Engine {
    
    public CleanUpEngine(){
        super(5000);
    }

    @Override
    public void execution() {
        System.gc();
        System.runFinalization();
    }

    @Override
    public void run() {
        while(this.isRunning()){
            execution();
            try {
                Thread.sleep(this.getFrequency());
            } catch (InterruptedException ex) {
                Logger.getLogger(MinigameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
