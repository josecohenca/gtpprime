/**
 * 
 */
package org.protocol.gtp.prime.messages;

import org.protocol.gtp.prime.GtpPrimeHeader;
import org.protocol.gtp.prime.GtpPrimeMessage;
import org.protocol.gtp.prime.constants.GtpPrimeConstants;

/**
 * @author krychu
 *
 */
public class GtpPrimeVersionNotSupported extends GtpPrimeMessage {

	public GtpPrimeVersionNotSupported() {
		super();
		this.getHeader().setMessageType(GtpPrimeConstants.GTP_PRIME_VERSION_NOT_SUPPORTED);
	}
	
	public GtpPrimeVersionNotSupported(GtpPrimeHeader header, byte[] message) {
		super(header,message);	
	}
	
}
