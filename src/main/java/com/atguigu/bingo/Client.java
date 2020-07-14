package com.atguigu.bingo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * TODO desc
 *
 * @author bingo
 * @date 2020/07/13
 */
public class Client {

    private volatile Channel channel;

    private NioEventLoopGroup loopGroup;


    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();

        client.connection();

        while (true) {
            Thread.sleep(1000);
            client.sendMsg();
        }
    }

    public void connection() throws InterruptedException {
        loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ClientChannelHandler());
                        }
                    })
            ;

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8888).sync();

            System.out.println("客户端 ok..");

            channel = channelFuture.channel();

//            channelFuture.channel().closeFuture().sync();
        } finally {
//            loopGroup.shutdownGracefully();
        }
    }

    public void shutdown() {
        if (channel != null) {
            channel.close();
        }
        loopGroup.shutdownGracefully();
    }

    public void sendMsg() {
        if (channel == null) {
            return;
        }
        channel.writeAndFlush(Unpooled.copiedBuffer("hello" + System.currentTimeMillis(), CharsetUtil.UTF_8));
    }

}