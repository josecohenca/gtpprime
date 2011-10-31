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
public class GtpPrimeDataRecordTransferRequest extends GtpPrimeMessage {

	public GtpPrimeDataRecordTransferRequest() {
		super();
		this.getHeader().setMessageType(GtpPrimeConstants.GTP_PRIME_DATA_RECORD_TRANSFER_REQUEST);
		
/*		this.addInformationElement(new GtpPrimeInformationElementTV(
				GtpPrimeConstants.GTP_PRIME_IET_PACKET_TRANSFER_COMMAND,
				GtpPrimeConstants.GTP_PRIME_IET_PTC_CANCEL_DATA));*/
	}
	
	public GtpPrimeDataRecordTransferRequest(GtpPrimeHeader header, byte[] message) {
		super(header,message);	
	}	
	
	public GtpPrimeDataRecordTransferResponse getResponse(short cause) {
		GtpPrimeDataRecordTransferResponse response = new GtpPrimeDataRecordTransferResponse(cause);
		response.getHeader().setSequenceNumber(response.getHeader().getSequenceNumber() + 1);
		return response;
	}
			
}
