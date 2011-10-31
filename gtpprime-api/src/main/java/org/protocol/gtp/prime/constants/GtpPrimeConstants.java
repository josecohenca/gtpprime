package org.protocol.gtp.prime.constants;

public class GtpPrimeConstants {

	public static final int GTP_PRIME_PROTOCOL_TYPE = 0;
	
	// Message types
	public static final int GTP_PRIME_ECHO_REQUEST = 1;
	public static final int GTP_PRIME_ECHO_RESPONSE = 2;
	public static final int GTP_PRIME_VERSION_NOT_SUPPORTED = 3;
	public static final int GTP_PRIME_NODE_ALIVE_REQUEST = 4;
	public static final int GTP_PRIME_NODE_ALIVE_RESPONSE = 5;
	public static final int GTP_PRIME_REDIRECTION_REQUEST = 6;
	public static final int GTP_PRIME_REDIRECTION_RESPONSE = 7;	
	public static final int GTP_PRIME_DATA_RECORD_TRANSFER_REQUEST = 240;
	public static final int GTP_PRIME_DATA_RECORD_TRANSFER_RESPONSE = 241;
	
	// IE types
	public static final short GTP_PRIME_IET_CAUSE = 1;
	public static final short GTP_PRIME_IET_RECOVERY = 14;
	public static final short GTP_PRIME_IET_PACKET_TRANSFER_COMMAND = 126;
	public static final short GTP_PRIME_IET_CHARGING_GATEWAY_ADDRESS = 251;
	
	//
	public static final short GTP_PRIME_IET_CAUSE_NODE_GOING_DOWN = 63;
	public static final short GTP_PRIME_IET_CAUSE_REQUEST_ACCEPTED = 128;
	public static final short GTP_PRIME_IET_CAUSE_SYSTEM_FAILURE = 204;
	public static final short GTP_PRIME_IET_SEQUENCE_NUMBERS_RELEASED = 249;
	public static final short GTP_PRIME_IET_SEQUENCE_NUMBERS_CANCELLED = 250;
	public static final short GTP_PRIME_IET_DATA_RECORD_PACKET = 252;	
	
	// Packet commands
	/*
	 * 1 = 'Send Data Record Packet';
	 * 2 = 'Send possibly duplicated Data Record Packet';
	 * 3 = 'Cancel Data Record Packet';
	 * 4 = 'Release Data Record Packet'.
	 */
	public static final short GTP_PRIME_IET_PTC_SEND_DATA = 1;
	public static final short GTP_PRIME_IET_PTC_SEND_DUPLDATA = 2;
	public static final short GTP_PRIME_IET_PTC_CANCEL_DATA = 3;
	public static final short GTP_PRIME_IET_PTC_RELEASE_DATA = 4;

	
}
