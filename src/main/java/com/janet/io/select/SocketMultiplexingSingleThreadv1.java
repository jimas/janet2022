package com.janet.io.select;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Date: 2022/7/23
 * Time: 21:58
 *
 * @author jimas
 */
public class SocketMultiplexingSingleThreadv1 {
    private ServerSocketChannel server = null;
    private Selector selector = null;

    public static void main(String[] args) throws Exception {
        final SocketMultiplexingSingleThreadv1 singleThreadv1 = new SocketMultiplexingSingleThreadv1();
        singleThreadv1.start();
    }

    private void start() throws Exception {
        initServer();

        while (true) {
            final Set<SelectionKey> keys = selector.keys();
            System.out.println("keys size" + keys.size());

            while (selector.select() > 0) {
                final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    final SelectionKey key = iterator.next();
                    iterator.remove();
                    //连接上
                    if (key.isAcceptable()) {
                        acceptHandler(key);
                    }
                    //可读
                    else if (key.isReadable()) {
                        readHandler(key);

                    }
                }
            }
        }
    }

    private void readHandler(SelectionKey key) {
        final SocketChannel client = (SocketChannel) key.channel();
        final ByteBuffer buffer = (ByteBuffer) key.attachment();

        buffer.clear();
        while (true) {
            int read = 0;
            try {
                read = client.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (read == 0) {
                    break;
                } else {
                    client.close();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        try {
            final ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            final SocketChannel client = ssc.accept();
            client.configureBlocking(false);
            final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            client.register(selector, SelectionKey.OP_READ, byteBuffer);
            System.out.println("================================");
            System.out.println("new client:" + client.getRemoteAddress());
            System.out.println("================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initServer() throws Exception {
        server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(9090));
        selector = Selector.open();

        server.register(selector, SelectionKey.OP_ACCEPT);
    }
}
