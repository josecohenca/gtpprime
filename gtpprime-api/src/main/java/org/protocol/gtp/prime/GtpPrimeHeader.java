/**
 * The GTP' message header representation as described in 3GPP TS 32.295 V10.0.0 (2011-03) Technical Specification Charging Data Record (CDR) transfer (Release 10).
 *  
 */
package org.protocol.gtp.prime;

import org.protocol.gtp.prime.constants.GtpPrimeConstants;
import org.protocol.gtp.prime.exception.UnrecognizedMessageException;

/**
 * @author Krystian Brachmanski
 * 
 */
public class GtpPrimeHeader {

	/**
	 * Bit 5 of octet 1 of the GTP header is the Protocol Type (PT) flag: it is
	 * '0' if the message is GTP'.
	 * 
	 * The Version bits indicate the GTP' protocol version when the Protocol
	 * Type flag is '0'.
	 * 
	 * Bit 1 of octet 1 is not used in GTP' (except in v0), and it is marked '0'
	 * in the GTP' header. It is in use in GTP' v0 and distinguishes the used
	 * header-length. In the case of GTP' v0, this bit being marked one (1)
	 * indicates the usage of the 6 octets header. If the bit is set to '0'
	 * (usually the case) the 20-octet header is used. For all other versions of
	 * GTP', this bit is not used and is set to '0'. However, this does not
	 * suggest the use of the 20-octet header, rather a shorter 6-octet header.
	 * The Length indicates the length of payload (number of octets after the
	 * GTP' header). The Sequence Number of the packet is part of the GTP'
	 * header
	 */
	
	private byte version = 0, pt = -1;
	private int messageType = -1;
	private int length = 0, sequenceNumber = 0; 
	
	/**
	 * Constructor for decoding the GTP' header from an existing message
	 * @param message - the message in the form of byte array
	 */	
	public GtpPrimeHeader(byte[] message) throws UnrecognizedMessageException {
		decodeFromMessage(message);		
	}
	
	/**
	 * Constructor to be used for creating new GTP' header object for a new message
	 */
	public GtpPrimeHeader() {
		initDefaults();		
	}

	private void initDefaults() {
		version = 2;
		pt = GtpPrimeConstants.GTP_PRIME_PROTOCOL_TYPE;
		length = 0;
		messageType = GtpPrimeConstants.GTP_PRIME_ECHO_REQUEST;
		sequenceNumber = 0;
	}
	
	private int convertByteToInt(byte a) {
		return a & 0x00FF;
	}
	
	public void decodeFromMessage(byte[] message) throws UnrecognizedMessageException {
		byte first = message[0];
		
		// Check if spare field consists of ones
		if (((first >> 1) & 0x07) != 0x07) throw new UnrecognizedMessageException("Message is not of type GTP' : Spare part is not '111'");
		pt = (byte)((first >> 4) & 0x01);
		
		if (pt != GtpPrimeConstants.GTP_PRIME_PROTOCOL_TYPE) throw new UnrecognizedMessageException("Message is not of type GTP' : ProtocolType field is not GTP'");
		
		version = (byte)((first >> 5) & 0x07);
		messageType = (short)convertByteToInt(message[1]);
		
		this.length = (convertByteToInt(message[2]) << 8) + convertByteToInt(message[3]);
		this.sequenceNumber = (convertByteToInt(message[4]) << 8) + convertByteToInt(message[5]);		
	}
	
	public byte[] toByteArray() {
		byte[] tmp = new byte[6];
		tmp[0] = 0x0e;		
		tmp[0] |= (byte)(this.version << 5);
		tmp[0] |= (byte)(this.pt << 4);
		tmp[1] = (byte)this.messageType;
		tmp[3] = (byte)(this.length & 0xFF);
		tmp[2] = (byte)((this.length >> 8) & 0xFF);		
		tmp[5] = (byte)(this.sequenceNumber & 0xFF);
		tmp[4] = (byte)((this.sequenceNumber >> 8) & 0xFF);
		return tmp;
	}
	
	public byte getVersion() {
		return version;
	}

	public void setVersion(byte version) {
		this.version = version;
	}

	public byte getPt() {
		return pt;
	}

	public void setPt(byte pt) {
		this.pt = pt;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(short length) {
		this.length = length;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	

}
