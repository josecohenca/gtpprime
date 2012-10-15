/**
 * 
 */
package org.protocol.gtp.prime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.protocol.gtp.prime.iet.GtpPrimeInformationElementTLV;
import org.protocol.gtp.prime.iet.GtpPrimeInformationElementTV;

/**
 * @author krychu
 *
 */
public class GtpPrimeMessage {

	protected GtpPrimeHeader header;
	protected ArrayList<GtpPrimeInformationElement> iet;
	
	public GtpPrimeMessage() {
		header = new GtpPrimeHeader();
		iet = new ArrayList<GtpPrimeInformationElement>();
	}
	
	public GtpPrimeMessage(GtpPrimeHeader header, byte[] message) {
		this.header = header;
		decodeIets(message, 6);
	}
	
	private void decodeIets(byte[] message, int start) {
		if (start >= message.length) return;
		byte type = message[start];
		
		// TLV
		if (((type >> 7)&0x01) == 1) {
			GtpPrimeInformationElementTLV tlv = new GtpPrimeInformationElementTLV(message,start);
			addInformationElement(tlv);			
			start += tlv.getTotalSize();								
		} else {
			// TV
			addInformationElement(new GtpPrimeInformationElementTV((short)(type & 0x00FF),(short)(message[start+1] & 0x00FF)));
			start += 2;
		}
		if (start < message.length) decodeIets(message, start);
	}
	
	public byte[] toByteArray() {		
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayOutputStream out_iets = new ByteArrayOutputStream();
		try {
			for (GtpPrimeInformationElement g: iet) {
				out_iets.write(g.toByteArray());				
			}
			header.setLength((short)out_iets.size());
			out.write(header.toByteArray());
			out.write(out_iets.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return out.toByteArray();
	}

	public GtpPrimeHeader getHeader() {
		return header;
	}

	public void setHeader(GtpPrimeHeader header) {
		this.header = header;
	}
	
	public void addInformationElement(GtpPrimeInformationElement el) {
		if (this.iet == null) this.iet = new ArrayList<GtpPrimeInformationElement>();
		this.iet.add(el);
	}
	
	public GtpPrimeInformationElement getInformationElement(short type) {
		
		GtpPrimeInformationElement tmp = null;
		for (GtpPrimeInformationElement g : iet) {
			if (g.getIEType() == type ) {
				tmp = g;
				break;
			}
		}
		return tmp;
	}

        public String toString() {
            StringBuilder b = new StringBuilder();
            b.append(this.header.toString());
            for (GtpPrimeInformationElement gtpie : iet)
               b.append(gtpie.toString());
            return b.toString();
        }	
}
