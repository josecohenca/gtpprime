/**
 * 
 */
package org.protocol.gtp.prime.messages;

import org.protocol.gtp.prime.GtpPrimeHeader;
import org.protocol.gtp.prime.GtpPrimeInformationElement;
import org.protocol.gtp.prime.GtpPrimeMessage;
import org.protocol.gtp.prime.constants.GtpPrime3gppConstants;
import org.protocol.gtp.prime.iet.GtpPrimeInformationElementTV;

/**
 * @author krychu
 *
 */
public class GtpPrimeEchoResponse extends GtpPrimeMessage {
	
	public GtpPrimeEchoResponse() {
		this((short)0);
	}
	
	public GtpPrimeEchoResponse(short restartCounter) {
		super();
		this.getHeader().setMessageType(GtpPrime3gppConstants.GTP_PRIME_ECHO_RESPONSE);				
		this.addInformationElement(new GtpPrimeInformationElementTV(GtpPrime3gppConstants.GTP_PRIME_IET_RECOVERY,restartCounter));		
	}
	
	public GtpPrimeEchoResponse(GtpPrimeHeader header, byte[] message) {
		super(header,message);	
	}
	
	public void setRestartCounter(short value) {
		GtpPrimeInformationElement el = getInformationElement(GtpPrime3gppConstants.GTP_PRIME_IET_RECOVERY);
		((GtpPrimeInformationElementTV)el).setValue(value);
	}
	

}
