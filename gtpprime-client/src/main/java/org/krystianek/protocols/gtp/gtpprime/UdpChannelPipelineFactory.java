package org.krystianek.protocols.gtp.gtpprime;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.SimpleChannelHandler;

public class UdpChannelPipelineFactory implements ChannelPipelineFactory {
	
	private SimpleChannelHandler handler;
	
	public UdpChannelPipelineFactory(SimpleChannelHandler handler) {
		this.handler = handler;
	}
	
	public ChannelPipeline getPipeline() throws Exception {
	    ChannelPipeline p = Channels.pipeline();
	    
	    //p.addLast("logger", new LoggingHandler());
	    p.addLast("encoder", new GtpPrimeObjectEncoder());
	    p.addLast("decoder", new GtpPrimeObjectDecoder());
		
	    p.addLast("handler", handler);
	    
	    return p;
	}

}
