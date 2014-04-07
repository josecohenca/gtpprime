/**
 * 
 */
package org.protocol.gtp.prime.messages;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.protocol.gtp.prime.GtpPrimeHeader;
import org.protocol.gtp.prime.constants.GtpPrime3gppConstants;
import org.protocol.gtp.prime.helpers.CDRProvider;
import org.protocol.gtp.prime.iet.GtpPrimeInformationElementTLV;

/**
 * @author krychu
 *
 */
public class GtpPrimeDataRecordTransferCDRRequestBase extends
		GtpPrimeDataRecordTransferRequest {

	public GtpPrimeDataRecordTransferCDRRequestBase() {
		super();
	}

	public GtpPrimeDataRecordTransferCDRRequestBase(GtpPrimeHeader header,
			byte[] message) {
		super(header, message);
	}
	
	public void addDataRecords(CDRProvider provider) throws Exception {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		List<byte[]> list = provider.getCDRs();
		
		out.write(list.size() & 0xFF);
		out.write(provider.getDataRecordFormat() & 0xFF);
		int version = provider.getDataRecordFormatVersion();
		out.write((version >> 8) & 0xFF);
		out.write(version & 0xFF);
		
		for (byte[] cdr : list) {
			out.write((cdr.length >> 8) & 0xFF);
			out.write(cdr.length & 0xFF);
			out.write(cdr);
		}
		
		this.addInformationElement(new GtpPrimeInformationElementTLV(GtpPrime3gppConstants.GTP_PRIME_IET_DATA_RECORD_PACKET, out.toByteArray()));
		
	}


}
