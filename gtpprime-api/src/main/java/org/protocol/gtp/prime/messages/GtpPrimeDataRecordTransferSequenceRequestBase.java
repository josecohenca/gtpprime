/**
 * 
 */
package org.protocol.gtp.prime.messages;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.protocol.gtp.prime.GtpPrimeHeader;
import org.protocol.gtp.prime.constants.GtpPrime3gppConstants;
import org.protocol.gtp.prime.helpers.SequenceProvider;
import org.protocol.gtp.prime.iet.GtpPrimeInformationElementTLV;

/**
 * @author krychu
 *
 */
public class GtpPrimeDataRecordTransferSequenceRequestBase extends
		GtpPrimeDataRecordTransferRequest {

	public GtpPrimeDataRecordTransferSequenceRequestBase() {
		super();
	}

	public GtpPrimeDataRecordTransferSequenceRequestBase(GtpPrimeHeader header,
			byte[] message) {
		super(header, message);
	}

	protected void addSequences(short code, SequenceProvider provider) {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		List<Integer> list = provider.getSequenceList();
		for (Integer i : list) {
			out.write((i>> 8) & 0xFF);
			out.write(i & 0xFF);
		}
		
		this.addInformationElement(new GtpPrimeInformationElementTLV(code, out.toByteArray()));
	}
	
	public void addCancelledSequences(SequenceProvider provider) {
		this.addSequences(GtpPrime3gppConstants.GTP_PRIME_IET_SEQUENCE_NUMBERS_CANCELLED, provider);		
	}
	

	public void addReleasedSequences(SequenceProvider provider) {
		this.addSequences(GtpPrime3gppConstants.GTP_PRIME_IET_SEQUENCE_NUMBERS_RELEASED, provider);
	}
		
}
