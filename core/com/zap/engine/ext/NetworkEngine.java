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

import com.zap.NetworkInitiator;
import com.zap.engine.Engine;
import com.zap.util.Constants;

/**
 *
 * @author Faris
 */
public class NetworkEngine extends Engine {
    
    private int port;
    
    public NetworkEngine(int port){
        super(Constants.NETWORK_EXECUTION_RATE);
        setPort(port);
    }

    @Override
    public void run() {
        execution();
    }

    @Override
    public void execution() {
        new NetworkInitiator(getPort()).constructLoginStreams();
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
    public void setPort(int port) {
        this.port = port;
    }

}
