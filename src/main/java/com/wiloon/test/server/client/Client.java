package com.wiloon.test.server.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Yupeg.LV on 17/6/6.
 */
public class Client {
    private static String m_host = "127.0.0.1";
    private static int m_prot = 23333;

    public static void main(String[] args) throws Exception {
        new Client().run();
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bt = new Bootstrap().group(group).channel(NioSocketChannel.class).handler(new Initializer());
            Channel channel = bt.connect(m_host, m_prot).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                channel.writeAndFlush(in.readLine() + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
