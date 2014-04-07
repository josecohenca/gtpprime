/**
 * 
 */
package org.protocol.gtp.prime.messages;

import org.protocol.gtp.prime.GtpPrimeHeader;
import org.protocol.gtp.prime.GtpPrimeMessage;
import org.protocol.gtp.prime.constants.GtpPrime3gppConstants;

/**
 * @author krychu
 *
 */
public class GtpPrimeVersionNotSupported extends GtpPrimeMessage {

	public GtpPrimeVersionNotSupported() {
		super();
		this.getHeader().setMessageType(GtpPrime3gppConstants.GTP_PRIME_VERSION_NOT_SUPPORTED);
	}
	
	public GtpPrimeVersionNotSupported(GtpPrimeHeader header, byte[] message) {
		super(header,message);	
	}
	
}
