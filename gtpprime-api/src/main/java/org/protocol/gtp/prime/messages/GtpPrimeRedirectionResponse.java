/**
 * 
 */
package org.protocol.gtp.prime.messages;

import org.protocol.gtp.prime.GtpPrimeHeader;
import org.protocol.gtp.prime.GtpPrimeMessage;
import org.protocol.gtp.prime.constants.GtpPrimeConstants;
import org.protocol.gtp.prime.iet.GtpPrimeInformationElementTV;

/**
 * @author krychu
 *
 */
public class GtpPrimeRedirectionResponse extends GtpPrimeMessage {

	public GtpPrimeRedirectionResponse(short cause) {
		super();
		this.getHeader().setMessageType(
				GtpPrimeConstants.GTP_PRIME_REDIRECTION_RESPONSE);
		this.addInformationElement(new GtpPrimeInformationElementTV(
				GtpPrimeConstants.GTP_PRIME_IET_CAUSE,
				cause));				
	}

	public GtpPrimeRedirectionResponse(GtpPrimeHeader header, byte[] message) {
		super(header, message);
	}
	
}
