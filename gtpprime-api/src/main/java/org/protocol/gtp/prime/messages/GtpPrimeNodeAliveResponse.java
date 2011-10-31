package org.protocol.gtp.prime.messages;

import org.protocol.gtp.prime.GtpPrimeHeader;
import org.protocol.gtp.prime.GtpPrimeMessage;
import org.protocol.gtp.prime.constants.GtpPrimeConstants;

public class GtpPrimeNodeAliveResponse extends GtpPrimeMessage {

	public GtpPrimeNodeAliveResponse() {
		super();
		this.getHeader().setMessageType(
				GtpPrimeConstants.GTP_PRIME_NODE_ALIVE_RESPONSE);
	}

	public GtpPrimeNodeAliveResponse(GtpPrimeHeader header, byte[] message) {
		super(header, message);
	}
	
}
