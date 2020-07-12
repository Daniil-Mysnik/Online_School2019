package net.thumbtack.school.async.Task6;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.util.CharsetUtil;

public class WebServerApplication {
    private static final String host = "localhost";
    private static final int PORT = 9999;

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new Init())
                    .bind(host, PORT);
            ChannelFuture f = b.connect(host, 8080).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    private static class Init extends ChannelInitializer {
        @Override
        protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast(new HttpResponseDecoder());
            ch.pipeline().addLast(new HttpObjectAggregator(100 * 1024));
            ch.pipeline().addLast(new ClientHandler());
        }
    }

    private static class ClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            String REQUEST = "POST /api/sessions HTTP/1.1\r\n" +
                    "User-Agent: Opera/9.80 (X11; Linux x86_64) Presto/2.12.388 Version/12.16\r\n" +
                    "Host: jsonplaceholder.typicode.com\r\n" +
                    "Accept: */*\r\n" +
                    "Connection: close\r\n" +
                    "Content-Type: application/json\r\n" +
                    "Content-Length: 200\r\n" +
                    "\r\n" +
                    "{\"login\":\"admin12345\", \"password\":\"admin12345\"}\r\n\r\n";

            String GETREQUEST = "GET /api/patients/1 HTTP/1.1\r\n" +
                    "User-Agent: Opera/9.80 (X11; Linux x86_64) Presto/2.12.388 Version/12.16\r\n" +
                    "Host: jsonplaceholder.typicode.com\r\n" +
                    "Accept: */*\r\n" +
                    "Connection: close\r\n\r\n";

            ctx.writeAndFlush(Unpooled.copiedBuffer(REQUEST, CharsetUtil.UTF_8));
            ctx.writeAndFlush(Unpooled.copiedBuffer(GETREQUEST, CharsetUtil.UTF_8));
        }

        @Override
        public void channelRead0(ChannelHandlerContext ctx, FullHttpResponse in) {
            System.out.println(in.content().toString(CharsetUtil.UTF_8));
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }

}
