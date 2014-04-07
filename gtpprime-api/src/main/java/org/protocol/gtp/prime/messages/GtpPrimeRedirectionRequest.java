/**
 * 
 */
package org.protocol.gtp.prime.messages;

import java.net.InetSocketAddress;

import org.protocol.gtp.prime.GtpPrimeHeader;
import org.protocol.gtp.prime.GtpPrimeMessage;
import org.protocol.gtp.prime.GtpPrimeMessageFactory;
import org.protocol.gtp.prime.constants.GtpPrime3gppConstants;
import org.protocol.gtp.prime.iet.GtpPrimeInformationElementTLV;
import org.protocol.gtp.prime.iet.GtpPrimeInformationElementTV;

/**
 * @author krychu
 *
 */
public class GtpPrimeRedirectionRequest extends GtpPrimeMessage {

	private void addCGateway(String ip) {
		this.addInformationElement(new GtpPrimeInformationElementTLV(
				GtpPrime3gppConstants.GTP_PRIME_IET_CHARGING_GATEWAY_ADDRESS,
				new InetSocketAddress(ip, 0).getAddress().getAddress()));
		
	}
	
	private void init(short cause, String ip) {
		this.getHeader().setMessageType(
				GtpPrime3gppConstants.GTP_PRIME_REDIRECTION_REQUEST);
		this.addInformationElement(new GtpPrimeInformationElementTV(
				GtpPrime3gppConstants.GTP_PRIME_IET_CAUSE,
				cause));
		addCGateway(ip);
	}

	public GtpPrimeRedirectionRequest(short cause, String ip) {
		super();
		init(cause, ip);
	}

	public GtpPrimeRedirectionRequest(short cause, String ip1, String ip2) {
		super();
		init(cause, ip1);
		addCGateway(ip2);
		
	}
	
	public GtpPrimeRedirectionRequest(GtpPrimeHeader header, byte[] message) {
		super(header,message);	
	}
	
	public GtpPrimeMessage getResponse() {
		GtpPrimeMessage msg = GtpPrimeMessageFactory.createRedirectionResponseMessage(GtpPrime3gppConstants.GTP_PRIME_IET_CAUSE_REQUEST_ACCEPTED);
		msg.getHeader().setSequenceNumber(this.getHeader().getSequenceNumber()+1);
		return msg;		
	}
	
}
