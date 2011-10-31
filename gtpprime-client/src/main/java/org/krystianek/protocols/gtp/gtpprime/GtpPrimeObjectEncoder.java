package org.krystianek.protocols.gtp.gtpprime;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import org.jboss.netty.buffer.ChannelBuffers;
import org.protocol.gtp.prime.GtpPrimeMessage;

public class GtpPrimeObjectEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext arg0, Channel arg1,
			Object arg2) throws Exception {
		
		if (arg2 instanceof GtpPrimeMessage) {
			return ChannelBuffers.copiedBuffer(((GtpPrimeMessage)arg2).toByteArray());
		}
		return arg2;
		
		
	}

}
