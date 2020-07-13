package com.atguigu.bingo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * TODO desc
 *
 * @author bingo
 * @date 2020/07/10
 */
public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    /**
     * 通道读取客户端请求数据
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ServerChannelHandler.channelRead() ChannelHandlerContext:" + ctx);

        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端地址：" + ctx.channel().remoteAddress() +
                " | 接收到数据:" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        String msg = "服务端发送消息：" + System.currentTimeMillis();
        ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}