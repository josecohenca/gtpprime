/**
 * 
 */
package org.krystianek.protocols.gtp;

import java.util.logging.Logger;

/**
 * @author krychu
 * 
 */
public class GtpPrimeTestMain {

	private static final Logger _log = Logger.getLogger(GtpPrimeTestMain.class
			.getName());

	public GtpPrimeTestMain() {

	}

	public static UdpGtpPrimeServer initializeGtpServer() {
		_log.info("Starting the GTP' server...");
		UdpGtpPrimeServer server = new UdpGtpPrimeServer();
		server.initializeUDPTransport();
		return server;
	}

	public static UdpGtpPrimeClient initializeGtpClient() {
		_log.info("Starting the GTP' client...");
		UdpGtpPrimeClient client = new UdpGtpPrimeClient();
		client.initializeUDPTransport();
		return client;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		UdpGtpPrimeServer server = initializeGtpServer();
		UdpGtpPrimeClient client = initializeGtpClient();

		//client.sendSampleNodeAliveRequest();
		//client.sendSampleEchoRequest();
		client.sendSampleRedirectionRequest();
		
		
		//client.sendSampleDataRecordTransferRequest();
		client.sendSampleDataRecordTransferCDRRequest();
		client.sendSampleDataRecordReleaseTransferRequest();		
		
		/*
		 * try { Thread.sleep(10000); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 * 
		 * //client.sendSampleNodeAliveRequest();
		 */
//		while (true) {
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//		}
		
		_log.info("Closing the GTP' client & server");

		server.closeUDPTransport();
		client.closeUDPTransport();

	}

}
