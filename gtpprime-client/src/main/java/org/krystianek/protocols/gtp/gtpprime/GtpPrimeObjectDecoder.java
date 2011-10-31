package org.krystianek.protocols.gtp.gtpprime;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;
import org.protocol.gtp.prime.GtpPrimeMessageFactory;

public class GtpPrimeObjectDecoder extends OneToOneDecoder {

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object obj) throws Exception {
				
		if (obj instanceof ChannelBuffer) {
		
			System.out.println("Received message");
			ChannelBuffer buf = (ChannelBuffer)obj;
			byte[] msg = new byte[buf.readableBytes()];
			buf.readBytes(msg);
			return GtpPrimeMessageFactory.decodeFromByteArray(msg); 			
		}
		return obj;
	}

}
