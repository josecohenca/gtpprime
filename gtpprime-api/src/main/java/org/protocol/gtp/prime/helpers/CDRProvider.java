/**
 * 
 */
package org.protocol.gtp.prime.helpers;

import java.util.List;

/**
 * @author krychu
 *
 */
public interface CDRProvider {

	public int getDataRecordFormat();
	
	public int getDataRecordFormatVersion();
	
	public List<byte[]> getCDRs();
	
}
