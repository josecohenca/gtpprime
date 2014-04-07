/**
 * 
 */
package org.krystianek.protocols.gtp;

import java.net.InetSocketAddress;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.socket.DatagramChannel;
import org.krystianek.protocols.gtp.gtpprime.configuration.GtpPrimeConfiguration;
import org.protocol.gtp.prime.GtpPrimeMessage;
import org.protocol.gtp.prime.constants.GtpPrimeCommunicationConstants;
import org.protocol.gtp.prime.messages.GtpPrimeDataRecordTransferRequest;
import org.protocol.gtp.prime.messages.GtpPrimeEchoRequest;
import org.protocol.gtp.prime.messages.GtpPrimeNodeAliveRequest;
import org.protocol.gtp.prime.messages.GtpPrimeRedirectionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author krychu
 *
 */
public class UdpGtpPrimeServer extends UdpGtpPrime {
	
	private static final GtpPrimeConfiguration config = new GtpPrimeConfiguration();
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	private InetSocketAddress addr = new InetSocketAddress(config.getIpAddress(), config.getPort());	
	
	public void initializeUDPTransport() {
		super.initializeUDPTransport();	    
	    c = (DatagramChannel) b.bind(addr);		
	}
	
	public void setInetAddress(InetSocketAddress addr) {
		this.addr = addr;
	}
		
	public void closeUDPTransport() {
		c.close();
	}

	public void handleGtpPrimeMessage(GtpPrimeMessage msg) {
		log.info("Recognized Base Message");
	}

	
	public GtpPrimeMessage handleGtpPrimeMessage(GtpPrimeEchoRequest msg) {
		log.info("Recognized Echo Message");
		return msg.getResponse();
	}
	
	public GtpPrimeMessage handleGtpPrimeMessage(GtpPrimeNodeAliveRequest msg) {
		log.info("Recognized NodeAlive Message");
		return msg.getResponse();
	}
	
	public GtpPrimeMessage handleGtpPrimeMessage(GtpPrimeRedirectionRequest msg) {
		log.info("Recognized GtpPrimeRedirectionRequest Message");
		return msg.getResponse();
	}

	public GtpPrimeMessage handleGtpPrimeMessage(GtpPrimeDataRecordTransferRequest msg) {
		log.info("Recognized GtpPrimeDataRecordTransferRequest Message");
		return msg.getResponse((short)0);
	}	
	
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent event)
	throws Exception
    {
    	Object msg = event.getMessage();
    	GtpPrimeMessage response = null;
    	if (msg instanceof GtpPrimeEchoRequest)
    	{
    		response = handleGtpPrimeMessage((GtpPrimeEchoRequest)msg);    		
    	}
    	if (msg instanceof GtpPrimeNodeAliveRequest)
    	{
    		response = handleGtpPrimeMessage((GtpPrimeNodeAliveRequest)msg);    		
    	}    	
    	if (msg instanceof GtpPrimeRedirectionRequest) 
    	{
    		response = handleGtpPrimeMessage((GtpPrimeRedirectionRequest)msg);
    	}
    	if (msg instanceof GtpPrimeDataRecordTransferRequest) 
    	{
    		response = handleGtpPrimeMessage((GtpPrimeDataRecordTransferRequest)msg);
    	}
    	    	
    	if (response != null)
    		event.getChannel().write(response,event.getRemoteAddress());
    	
    }
	
}
