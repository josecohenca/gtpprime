package org.protocol.gtp.prime;

import org.protocol.gtp.prime.constants.GtpPrimeConstants;
import org.protocol.gtp.prime.exception.UnrecognizedMessageException;

public class GtpPrimeHeaderFactory {

	public static GtpPrimeHeader createHeader(int type) {
		GtpPrimeHeader header = new GtpPrimeHeader();
		header.setMessageType(type);
		return header;		
	}
	
	public static GtpPrimeHeader createEchoRequestHeader() {
		return GtpPrimeHeaderFactory.createHeader(GtpPrimeConstants.GTP_PRIME_ECHO_REQUEST);
	}
	
	public static GtpPrimeHeader createEchoResponseHeader() {
		return GtpPrimeHeaderFactory.createHeader(GtpPrimeConstants.GTP_PRIME_ECHO_RESPONSE);
	}
	
	public static GtpPrimeHeader createDataTransferRequestHeader() {
		return GtpPrimeHeaderFactory.createHeader(GtpPrimeConstants.GTP_PRIME_DATA_RECORD_TRANSFER_REQUEST);
	}

	public static GtpPrimeHeader createDataTransferResponseHeader() {
		return GtpPrimeHeaderFactory.createHeader(GtpPrimeConstants.GTP_PRIME_DATA_RECORD_TRANSFER_RESPONSE);
	}

	public static GtpPrimeHeader createNodeAliveRequestHeader() {
		return GtpPrimeHeaderFactory.createHeader(GtpPrimeConstants.GTP_PRIME_NODE_ALIVE_REQUEST);
	}

	public static GtpPrimeHeader createNodeAliveResponseHeader() {
		return GtpPrimeHeaderFactory.createHeader(GtpPrimeConstants.GTP_PRIME_NODE_ALIVE_RESPONSE);
	}

	public static GtpPrimeHeader createRedirectionRequestHeader() {
		return GtpPrimeHeaderFactory.createHeader(GtpPrimeConstants.GTP_PRIME_REDIRECTION_REQUEST);
	}

	public static GtpPrimeHeader createRedirectionResponseHeader() {
		return GtpPrimeHeaderFactory.createHeader(GtpPrimeConstants.GTP_PRIME_REDIRECTION_RESPONSE);
	}	

	public static GtpPrimeHeader createVersionNotSupportedHeader() {
		return GtpPrimeHeaderFactory.createHeader(GtpPrimeConstants.GTP_PRIME_VERSION_NOT_SUPPORTED);
	}	
	
	public static GtpPrimeHeader decodeFromByteArray(byte[] msg) throws UnrecognizedMessageException {
		GtpPrimeHeader header = new GtpPrimeHeader();
		header.decodeFromMessage(msg);
		return header;
	}
	
}
