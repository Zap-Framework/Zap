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

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.DefaultChannelPipeline;
import org.jboss.netty.util.HashedWheelTimer;

/**
 *
 * @author Faris
 */
public class PipelineFactory implements ChannelPipelineFactory {

	private final HashedWheelTimer timer;

	public PipelineFactory(HashedWheelTimer timer) {
		this.timer = timer;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		final ChannelPipeline pipeline = new DefaultChannelPipeline();
		//pipeline.addLast("timeout", new ReadTimeoutHandler(timer, 10));
		//pipeline.addLast("encoder", new RS2ProtocolEncoder());
		//pipeline.addLast("decoder", new RS2LoginProtocolDecoder());
		//pipeline.addLast("handler", new ChannelHandler());
		return pipeline;
	}

}
