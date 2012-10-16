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

	// Definition of IEs - according to 3GPP 32.295 
	public static final short DATA_RECORD_PACKET_IE = 252;
	
	public GtpPrimeInformationElement(short type) {
		this.type = type;
	}
	
	public short getIEType() {
		return type;
	}
	
	public abstract byte[] toByteArray();
        public abstract String informationElementSpecificToString();
	public abstract int getTotalSize();

        public String toString() {
           StringBuilder b = new StringBuilder("InformationElement {\n");
           b.append("    type = ");
           b.append(type);
           b.append('\n');
     
           b.append(informationElementSpecificToString());
          
           b.append("\n}\n");
           return b.toString();
 
        }
	
}
