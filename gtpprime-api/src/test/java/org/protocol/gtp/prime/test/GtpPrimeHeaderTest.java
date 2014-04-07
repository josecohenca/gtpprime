package org.protocol.gtp.prime.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.protocol.gtp.prime.GtpPrimeHeader;
import org.protocol.gtp.prime.constants.GtpPrime3gppConstants;
import org.protocol.gtp.prime.exception.UnrecognizedMessageException;

public class GtpPrimeHeaderTest {

	/*
	 * Flags: 0x2e
	 *    001. .... = Version: 1
	 *    ...0 .... = Protocol type: GTP' (0)
	 *    .... 111. = Reserved: 7
	 *    .... ...0 = Is SNDCP N-PDU included?: no
	 * Message Type: Data record transfer request (0xf0) 
	 * Length: 743 Sequence
	 * number: 0x0006
	 */
	public static final byte[] properHeader = { 0x2e, (byte) 0xf0, 0x02,
			(byte) 0xe7, 0x00, 0x06 };

	/*
	 * Flags: 0x2a
	 *    001. .... = Version: 1
	 *    ...0 .... = Protocol type: GTP' (0)
	 *    .... 101. = Reserved: 7
	 *    .... ...0 = Is SNDCP N-PDU included?: no
	 * Message Type: Data record transfer request (0xf0) 
	 * Length: 743 Sequence
	 * number: 0x0006
	 */
	public static final byte[] improperReservedHeader = { 0x2a, (byte) 0xf0, 0x02,
			(byte) 0xe7, 0x00, 0x06 };

	/*
	 * Flags: 0x3e
	 *    001. .... = Version: 1
	 *    ...1 .... = Protocol type: GTP' (0)
	 *    .... 101. = Reserved: 7
	 *    .... ...0 = Is SNDCP N-PDU included?: no
	 * Message Type: Data record transfer request (0xf0) 
	 * Length: 743 Sequence
	 * number: 0x0006
	 */
	public static final byte[] improperProtocolTypeHeader = { 0x3e, (byte) 0xf0, 0x02,
			(byte) 0xe7, 0x00, 0x06 };
	
	
	
	public void setUp() {
		System.out.println("Per Test Case Setup");
	}

	private void validateHeader(GtpPrimeHeader header) {
		assertEquals(1, header.getVersion());
		assertEquals(0, header.getPt());
		assertEquals(GtpPrime3gppConstants.GTP_PRIME_DATA_RECORD_TRANSFER_REQUEST,
				header.getMessageType());
		assertEquals(743, header.getLength());
		assertEquals(0x0006, header.getSequenceNumber());
	}

	@Test
	public void testDecodeFromMessageConstructor() throws Exception {
		GtpPrimeHeader t1 = new GtpPrimeHeader(properHeader);
		validateHeader(t1);
	}

	@Test
	public void testEncodeFromMessageConstructor() throws Exception {
		GtpPrimeHeader t1 = new GtpPrimeHeader(properHeader);
		validateHeader(t1);
		byte[] tmp = t1.toByteArray();
		assertArrayEquals(properHeader, t1.toByteArray());
	}

	
	@Test
	public void testDecodeFromMessage() throws Exception {
		GtpPrimeHeader t1 = new GtpPrimeHeader();
		t1.decodeFromMessage(properHeader);
		validateHeader(t1);

	}

	@Test(expected = UnrecognizedMessageException.class)
	public void testDecodeFromMessageWrongSpareConstructor() throws Exception {
		GtpPrimeHeader t1 = new GtpPrimeHeader(improperReservedHeader);
	}

	@Test(expected = UnrecognizedMessageException.class)
	public void testDecodeFromMessageWrongSpare() throws Exception {
		GtpPrimeHeader t1 = new GtpPrimeHeader();
		t1.decodeFromMessage(improperReservedHeader);
	}

	@Test(expected = UnrecognizedMessageException.class)
	public void testDecodeFromMessageWrongProtocolTypeConstructor() throws Exception {
		GtpPrimeHeader t1 = new GtpPrimeHeader(improperReservedHeader);
	}
	
	@Test(expected = UnrecognizedMessageException.class)
	public void testDecodeFromMessageWrongProtocolType() throws Exception {
		GtpPrimeHeader t1 = new GtpPrimeHeader();
		t1.decodeFromMessage(improperProtocolTypeHeader);
	}

}
