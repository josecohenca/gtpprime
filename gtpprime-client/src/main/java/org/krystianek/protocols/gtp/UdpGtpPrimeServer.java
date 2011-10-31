/**
 * 
 */
package org.krystianek.protocols.gtp;

import java.net.InetSocketAddress;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.socket.DatagramChannel;
import org.krystianek.protocols.gtp.gtpprime.constants.GtpPrimeCommunicationConstants;
import org.protocol.gtp.prime.GtpPrimeMessage;
import org.protocol.gtp.prime.messages.GtpPrimeDataRecordTransferRequest;
import org.protocol.gtp.prime.messages.GtpPrimeEchoRequest;
import org.protocol.gtp.prime.messages.GtpPrimeNodeAliveRequest;
import org.protocol.gtp.prime.messages.GtpPrimeRedirectionRequest;

/**
 * @author krychu
 *
 */
public class UdpGtpPrimeServer extends UdpGtpPrime {

	//private static final String IP_HOST="192.168.122.1";
	private static final String IP_HOST="127.0.0.1";
	
	private static final Logger _log = Logger.getLogger(UdpGtpPrimeServer.class.getName());
	private InetSocketAddress addr = new InetSocketAddress(IP_HOST, GtpPrimeCommunicationConstants.GTP_PORT);	
	
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
		_log.info("Recognized Base Message");
	}

	
	public GtpPrimeMessage handleGtpPrimeMessage(GtpPrimeEchoRequest msg) {
		_log.info("Recognized Echo Message");
		return msg.getResponse();
	}
	
	public GtpPrimeMessage handleGtpPrimeMessage(GtpPrimeNodeAliveRequest msg) {
		_log.info("Recognized NodeAlive Message");
		return msg.getResponse();
	}
	
	public GtpPrimeMessage handleGtpPrimeMessage(GtpPrimeRedirectionRequest msg) {
		_log.info("Recognized GtpPrimeRedirectionRequest Message");
		return msg.getResponse();
	}

	public GtpPrimeMessage handleGtpPrimeMessage(GtpPrimeDataRecordTransferRequest msg) {
		_log.info("Recognized GtpPrimeDataRecordTransferRequest Message");
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
