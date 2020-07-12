package net.thumbtack.school.async.Task5;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private RestService restService;

    public HttpServerHandler(RestService restService) {
        this.restService = restService;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) {
        String requestJson = request.content().toString(CharsetUtil.UTF_8);
        String responseString = restService.process(requestJson) + "\r\n";
        FullHttpResponse response = new DefaultFullHttpResponse(
                HTTP_1_1,
                OK,
                Unpooled.copiedBuffer(responseString, CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "application/json; charset=UTF-8");
        response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

        ctx.write(response);
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}