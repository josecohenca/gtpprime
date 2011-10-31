/**
 * 
 */
package org.protocol.gtp.prime.messages;

import org.protocol.gtp.prime.GtpPrimeHeader;
import org.protocol.gtp.prime.constants.GtpPrimeConstants;
import org.protocol.gtp.prime.iet.GtpPrimeInformationElementTV;

/**
 * @author krychu
 *
 */
public class GtpPrimeDataRecordTransferSequenceRelease extends
		GtpPrimeDataRecordTransferSequenceRequestBase {

	/**
	 * 
	 */
	public GtpPrimeDataRecordTransferSequenceRelease() {
		super();
		this.addInformationElement(new GtpPrimeInformationElementTV(
				GtpPrimeConstants.GTP_PRIME_IET_PACKET_TRANSFER_COMMAND,
				GtpPrimeConstants.GTP_PRIME_IET_PTC_RELEASE_DATA));
	}

	/**
	 * @param header
	 * @param message
	 */
	public GtpPrimeDataRecordTransferSequenceRelease(GtpPrimeHeader header,
			byte[] message) {
		super(header, message);
	}

}
