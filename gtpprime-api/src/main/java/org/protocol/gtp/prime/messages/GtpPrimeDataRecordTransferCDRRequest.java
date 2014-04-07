/**
 * 
 */
package org.protocol.gtp.prime.messages;

import org.protocol.gtp.prime.GtpPrimeHeader;
import org.protocol.gtp.prime.constants.GtpPrime3gppConstants;
import org.protocol.gtp.prime.iet.GtpPrimeInformationElementTV;

/**
 * @author krychu
 *
 */
public class GtpPrimeDataRecordTransferCDRRequest extends
		GtpPrimeDataRecordTransferCDRRequestBase {

	public GtpPrimeDataRecordTransferCDRRequest() {
		super();
		this.addInformationElement(new GtpPrimeInformationElementTV(
				GtpPrime3gppConstants.GTP_PRIME_IET_PACKET_TRANSFER_COMMAND,
				GtpPrime3gppConstants.GTP_PRIME_IET_PTC_SEND_DATA));
	}

	public GtpPrimeDataRecordTransferCDRRequest(GtpPrimeHeader header,
			byte[] message) {
		super(header, message);
	}

}
