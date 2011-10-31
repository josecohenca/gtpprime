/**
 * 
 */
package org.protocol.gtp.prime;

/**
 * @author krychu
 *
 */
public abstract class GtpPrimeInformationElement {

	protected short type = 0;
	
	public GtpPrimeInformationElement(short type) {
		this.type = type;
	}
	
	public short getIEType() {
		return type;
	}
	
	public abstract byte[] toByteArray();
	
}
