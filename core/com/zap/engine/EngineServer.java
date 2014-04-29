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
package com.zap.engine;

import com.zap.util.EngineList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Faris
 */
public class EngineServer {

    private List<Engine> preSubmitted = new EngineList();
    private List<Engine> activeEngines = new EngineList();

    public void submitEngine(Engine engine) {
        preSubmitted.add(engine);
    }

    public void serve() {
        if (preSubmitted.isEmpty() && activeEngines.isEmpty()) {
            return;
        }
        if (!preSubmitted.isEmpty()) {
            Iterator<Engine> it = preSubmitted.iterator();
            while (it.hasNext()) {
                Engine engine = it.next();
                if (!engine.isRunning()) {
                    engine.setRunning(true);
                    activeEngines.add(engine);
                    engine.fire();

                } else {
                    activeEngines.add(engine);
                }
                it.remove();
            }
        }
        Iterator<Engine> it2 = activeEngines.iterator();
        while (it2.hasNext()) {
            Engine engine = it2.next();
            if (activeEngines.isEmpty()) {
                return;
            }
            if (!engine.isRunning()) {
                it2.remove();
                engine = null;
            }
        }
    }
}
