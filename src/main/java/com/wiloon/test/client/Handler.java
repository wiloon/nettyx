package com.wiloon.test.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Yupeg.LV on 17/6/6.
 */
public class Handler extends SimpleChannelInboundHandler<String>{

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("收到：" + s);
    }
}
