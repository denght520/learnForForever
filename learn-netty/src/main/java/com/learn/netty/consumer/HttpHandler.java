package com.learn.netty.consumer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;
import java.nio.ByteBuffer;

/**
 * @projectName: learnForForever
 * @className: HttpHandler
 * @author: denghaitao
 * @date: 2020/1/7
 * @version: 1.0
 * @E-mail: denghaitao@oxyzgroup.com
 * @Copyright: 版权所有 (C) 2019 蓝鲸淘.
 * @return
 */
public class HttpHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        Channel channel = channelHandlerContext.channel();
        System.out.println(channel.remoteAddress()+"发来消息"+o);
//        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//        byteBuffer.clear();
//        byteBuffer.put("已收到请求");
//        byteBuffer.flip();
        channel.writeAndFlush("已收到请求");
    }
}
