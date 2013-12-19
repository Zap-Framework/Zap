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
import com.zap.engine.ext.NetworkEngine;
import com.zap.util.Constants;
import java.util.logging.Logger;

/**
 * 
 * @author Faris
 */
public class Zap {
    
        /**
         * A logger used to report error messages.
         */
        public static final Logger logger = Logger.getLogger(Zap.class.getName());
        
        /**
         * Whether the server requires an update
         */
        public static boolean updateServer = false;
        
        /**
         * The Engine Server
         */
        public static EngineServer engineServer;
        
	public static void main(String[] args) {
		new Zap().start();
	}

	public void start() {
            logger.info("Starting up Zap on Port:" +Constants.SERVER_PORT);
            engineServer =  new EngineServer();
            engineServer.initialize();
            logger.info("Fired up Engine Server...");
            EngineServer.submitEngine(new NetworkEngine());
            logger.info("Fired up Netty Login Server...");
	}

}
