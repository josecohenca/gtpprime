/**
 * 
 */
package org.krystianek.protocols.gtp;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.socket.DatagramChannel;
import org.protocol.gtp.prime.GtpPrimeMessageFactory;
import org.protocol.gtp.prime.constants.GtpPrimeConstants;
import org.protocol.gtp.prime.helpers.CDRProvider;
import org.protocol.gtp.prime.helpers.SequenceProvider;
import org.protocol.gtp.prime.messages.GtpPrimeDataRecordTransferCDRRequest;
import org.protocol.gtp.prime.messages.GtpPrimeDataRecordTransferRequest;
import org.protocol.gtp.prime.messages.GtpPrimeDataRecordTransferSequenceRelease;
import org.protocol.gtp.prime.messages.GtpPrimeEchoResponse;
import org.protocol.gtp.prime.messages.GtpPrimeVersionNotSupported;

/**
 * @author krychu
 *
 */
public class UdpGtpPrimeClient extends UdpGtpPrime {
	
	private static final Logger _log = Logger.getLogger(GtpPrimeTestMain.class.getName());
	
	private static final String TEST_GTP_IP = "192.168.122.168";
	//private static final String TEST_GTP_IP = "127.0.0.1";
	private static final int TEST_GTP_PORT = 3386;
	
	public UdpGtpPrimeClient() {
	    	    	    	    
	}
	
	public void initializeUDPTransport() {
		super.initializeUDPTransport();	    
	    c = (DatagramChannel) b.bind(new InetSocketAddress(0));		
	}
	
	public void sendSampleEchoRequest() {
	    c.write(GtpPrimeMessageFactory.createEchoRequestMessage(),new InetSocketAddress(TEST_GTP_IP, TEST_GTP_PORT));	    	   		
	}
	
	public void sendSampleNodeAliveRequest() {
	    c.write(GtpPrimeMessageFactory.createNodeAliveRequestMessage("192.168.122.1","fe80::224:d7ff:febd:559c"),new InetSocketAddress(TEST_GTP_IP, TEST_GTP_PORT));	    	   		
	}

	public void sendSampleRedirectionRequest() {
	    c.write(GtpPrimeMessageFactory.createRedirectionRequestMessage(GtpPrimeConstants.GTP_PRIME_IET_CAUSE_NODE_GOING_DOWN,"192.168.122.1","fe80::224:d7ff:febd:559c"),new InetSocketAddress(TEST_GTP_IP, TEST_GTP_PORT));	    	   		
	}

	class TestCDRProvider implements CDRProvider {

		ArrayList<byte[]> array;
		
		public TestCDRProvider() {
			byte[] arr1 = { 1, 2 , 3, 5, 8, 13, 21, 34 };
			byte[] arr2 = { 122, 44, 32, 5 };
			array = new ArrayList<byte[]>();
			array.add(arr1);
			array.add(arr2);
		}
		
		public int getDataRecordFormat() {
			return 1;
		}

		public int getDataRecordFormatVersion() {
			return 5;
		}

		public List<byte[]> getCDRs() {
			return array;
		}
	
	}
	
	public void sendSampleDataRecordTransferCDRRequest() {
		CDRProvider provider = new TestCDRProvider();
		GtpPrimeDataRecordTransferCDRRequest request = (GtpPrimeDataRecordTransferCDRRequest)GtpPrimeMessageFactory.createDataTransferCDRRequestMessage();		
		try {
			request.addDataRecords(provider);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    c.write(request,new InetSocketAddress(TEST_GTP_IP, TEST_GTP_PORT));	    	   		
	}

	class TestProvider implements SequenceProvider {

		ArrayList<Integer> array;
		
		public TestProvider() {
			int[] arr = { 1, 2 , 3, 5, 8, 13, 21, 34 };
			
			array = new ArrayList<Integer>();
			for (int i=0; i< arr.length; i++)
			   array.add(arr[i]);

		}
		
		public List<Integer> getSequenceList() {
			return array;
		}
		
	}
	
	public void sendSampleDataRecordReleaseTransferRequest() {
				
		TestProvider provider = new TestProvider();
		GtpPrimeDataRecordTransferSequenceRelease request = (GtpPrimeDataRecordTransferSequenceRelease)GtpPrimeMessageFactory.createDataTransferSequenceReleaseRequestMessage();		
		request.addCancelledSequences(provider);
	    c.write(request,new InetSocketAddress(TEST_GTP_IP, TEST_GTP_PORT));	    	   		
	}
	
	public void closeUDPTransport() {
		c.close();
	}
	
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent event)
	throws Exception
    {
    	Object msg = event.getMessage();
    	if (msg instanceof GtpPrimeEchoResponse)
    	{    		
    		_log.info("recognized Echo Response message...");
    		//GtpPrimeMessage response = handleGtpPrimeMessage((GtpPrimeEchoRequestMessage)msg);
    		//event.getChannel().write(response,event.getRemoteAddress());
    	}

    	if (msg instanceof GtpPrimeVersionNotSupported) {
    		int version = ((GtpPrimeVersionNotSupported) msg).getHeader().getVersion();
    		_log.info("recognized VersionNotSupported... Latest version supported is : " + version);
    		
    		
    		
    	}

    	
    }

}
