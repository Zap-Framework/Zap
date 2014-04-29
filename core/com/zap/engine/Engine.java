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
package com.zap.engine;

import com.zap.engine.impl.MinigameEngine;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faris
 */
public abstract class Engine implements Runnable {
    
    public Engine(int freq){
        setFrequency(freq);
    }
    
    /**
     * Holds current life status of engine instantiated
     */
    private boolean running;
    
    /**
     * The execution refresh rate of the engine
     */
    private int frequency;
  
    
    /**
     * Begins the life cycle of the engine
     */
    public  void fire(){
        new Thread(this).start();
    }
    
    /**
     * stops the life cycle of the running engine
     */
    public  void interrupt(){
        this.setRunning(false);
    };
    
    /**
     * Handles the main responsibilities of the instantiated engine
     */
    public abstract void execution();
    
    /**
     * The basic run function for each instantiated engine
     */
    @Override
    public void run(){
        while(this.isRunning()){
            execution();
            try {
                Thread.sleep(this.getFrequency());
            } catch (InterruptedException ex) {
                Logger.getLogger(MinigameEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the isRunning
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @param isRunning the isRunning to set
     */
    public void setRunning(boolean isRunning) {
        this.running = isRunning;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public final void setFrequency(int frequency) {
        this.frequency = frequency;
    }

}
