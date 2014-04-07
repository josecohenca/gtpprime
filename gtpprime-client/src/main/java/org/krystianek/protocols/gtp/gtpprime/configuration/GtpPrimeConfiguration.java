/**
 * 
 */
package org.krystianek.protocols.gtp.gtpprime.configuration;

import org.protocol.gtp.prime.constants.GtpPrimeCommunicationConstants;

/**
 * @author krystian
 *
 */
public class GtpPrimeConfiguration {
	
	String ipAddress = "127.0.0.1";
	int port = GtpPrimeCommunicationConstants.GTP_PORT;
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
}
