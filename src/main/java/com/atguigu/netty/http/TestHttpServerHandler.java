package com.atguigu.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/*
说明
1. SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter
2. HttpObject 客户端和服务器端相互通讯的数据被封装成 HttpObject
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {


    //channelRead0 读取客户端数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println("对应的channel=" + ctx.channel() + " pipeline=" + ctx
                .pipeline() + " 通过pipeline获取channel" + ctx.pipeline().channel());

        System.out.println("当前ctx的handler=" + ctx.handler());

        //判断 msg 是不是 httprequest请求
        if (msg instanceof HttpRequest) {

            System.out.println("ctx 类型=" + ctx.getClass());

            System.out.println("pipeline hashcode" + ctx.pipeline().hashCode() + " TestHttpServerHandler hash=" + this.hashCode());

            System.out.println("msg 类型=" + msg.getClass());
            System.out.println("客户端地址" + ctx.channel().remoteAddress());

            //获取到
            HttpRequest httpRequest = (HttpRequest) msg;
            //获取uri, 过滤指定的资源
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了 favicon.ico, 不做响应");
                return;
            }
            //回复信息给浏览器 [http协议]

            ByteBuf content = Unpooled.copiedBuffer("{\"objId\": 1594123986, \"cameraID\": \"fcd519d0-df66-4d07-97fb-02e3d2e65fba\", \"taskID\": \"1775_bbm89lyr\", \"templateID\": \"tlp-ba93z8m4\", \"age\": 22, \"gender\": 1, \"glasses\": 0, \"mask\": 0, \"hat\": 0, \"hairLength\": 1, \"coordX\": 69, \"coordY\": 49, \"coordWidth\": 50, \"coordHeight\": 71, \"captureImg\": \"http://testcos-1256936300.cos.ap-guangzhou.myqcloud.com/2020070720/1594123962_22poQYs2.jpg\", \"captureBigImg\": \"http://testcos-1256936300.cos.ap-guangzhou.myqcloud.com/2020070720/1594123961_hQERlSZ3.jpg\", \"isNotify\": false, \"faceLibId\": \"\", \"faceImageId\": \"\", \"similarity\": 0, \"cTime\": \"2020-07-07 20:12:42\", \"information\": null, \"checktime\": \"2020-07-07 20:12:42\", \"jobID\": \"slr-6efv0co0\"}", CharsetUtil.UTF_8);

            //构造一个http的相应，即 httpresponse
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            //将构建好 response返回
            ctx.channel().writeAndFlush(response);
//            ctx.writeAndFlush(response);
        }
    }


}
