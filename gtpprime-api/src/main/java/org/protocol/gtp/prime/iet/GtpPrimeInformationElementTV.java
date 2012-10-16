/**
 * 
 */
package org.protocol.gtp.prime.iet;

import org.protocol.gtp.prime.GtpPrimeInformationElement;

/**
 * @author Krystian Brachmanski
 *
 */
public class GtpPrimeInformationElementTV extends GtpPrimeInformationElement {

	protected short value = 0;
	
	public GtpPrimeInformationElementTV(short type, short value) {
		super(type);
		this.value = value;
	}
	
	public byte[] toByteArray() {
		byte[] tmp = new byte[2];
		tmp[0] = (byte)(type & 0xFF);
		tmp[1] = (byte)(value & 0xFF);
		return tmp;
	}
	
	public void setValue(short value) {
		this.value = value;
	}

        public String informationElementSpecificToString() {
        	StringBuilder b = new StringBuilder("    value = ");
                b.append(value);
		return b.toString();
        }

	public int getTotalSize() {
		return 2;
	}

}
