package net.thumbtack.school.async;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class Task4 {
    private static Gson gson = new Gson();
    private static final String HTTP_RESPONSE = "HTTP/1.1 200 OK\n" +
            "Content-Length: 100\n" +
            "Content-Type: text/html\n" +
            "Connection: Closed\n\n" +
            "<html>\n" +
            "<body>\n" +
            "<h1>Суммма равна %d!</h1>\n" +
            "</body>\n" +
            "</html>";

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 5000));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT, null);
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {
                    SocketChannel clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                    log("Connection Accepted: " + clientChannel.getLocalAddress() + "\n");

                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int n = client.read(buffer);
                    if (n == -1) {
                        client.close();
                    }
                    String result = new String(buffer.array()).trim();
                    put(key, result);
                    log("Message received: " + result);
                    key.interestOps(SelectionKey.OP_WRITE);

                } else if (key.isWritable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    Integer result = (Integer) key.attachment();
                    ByteBuffer writeBuffer = Charset.forName("UTF-8").encode(String.format(HTTP_RESPONSE, result));
                    client.write(writeBuffer);
                    if (!writeBuffer.hasRemaining()) {
                        writeBuffer.compact();
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    client.close();
                }
                iterator.remove();
            }
        }
    }

    private static void put(SelectionKey key, String result) {
        String request = result.split("\r\n\r\n")[1];
        XY xy = gson.fromJson(request, XY.class);
        key.attach(xy.getX() + xy.getY());
    }

    private static class XY {
        private int x;
        private int y;

        public XY (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static void log(String str) {
        System.out.println(str);
    }

}
