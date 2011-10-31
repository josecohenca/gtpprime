/**
 * 
 */
package org.krystianek.protocols.gtp;

import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.DatagramChannel;
import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;
import org.krystianek.protocols.gtp.gtpprime.UdpChannelPipelineFactory;

/**
 * @author krychu
 *
 */
public class UdpGtpPrime extends SimpleChannelHandler {

	protected NioDatagramChannelFactory f;
	protected DatagramChannel c;
	protected ConnectionlessBootstrap b;
	protected ChannelPipeline pipe;
	
	protected void initializeBootstrap() {
	    f = new NioDatagramChannelFactory(Executors.newCachedThreadPool());	    

	    b = new ConnectionlessBootstrap(f);
	    b.setPipelineFactory(new UdpChannelPipelineFactory(this));
	    b.setOption("broadcast", "false");
	    b.setOption("sendBufferSize", 65536);
	    b.setOption("receiveBufferSize", 65536);
	}
	
	protected void initializeUDPTransport() {
		this.initializeBootstrap();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		e.getCause().printStackTrace();		
	}
	
}
