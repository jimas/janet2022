package com.janet.io.custom;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Date: 2022/7/26
 * Time: 21:27
 *
 * @author jimas
 */
public class SelectorThread implements Runnable {
    Selector selector = null;
    LinkedBlockingQueue<Channel> lbq = new LinkedBlockingQueue<>();
    SelectorThreadGroup stg = null;

    public SelectorThread(SelectorThreadGroup stg) {
        try {
            this.stg = stg;
            this.selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        while (true) {
            try {
                System.out.println(Thread.currentThread().getName() + "selector before select:" + selector.keys().size());
                int num = selector.select();
                System.out.println(Thread.currentThread().getName() + "selector after select:" + selector.keys().size());
                if (num > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        final SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);
                        } else if (key.isWritable()) {
                            writeHandler(key);
                        }
                    }
                }
                if (!lbq.isEmpty()) {
                    try {
                        final Channel channel = lbq.take();
                        if (channel instanceof ServerSocketChannel) {
                            final ServerSocketChannel server = (ServerSocketChannel) channel;
                            server.register(selector, SelectionKey.OP_ACCEPT);
                        }
                        if (channel instanceof SocketChannel) {
                            final SocketChannel client = (SocketChannel) channel;
                            client.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(4096));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeHandler(SelectionKey key) {

    }

    private void readHandler(SelectionKey key) {
        final SocketChannel client = (SocketChannel) key.channel();
        final ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        while (true) {
            try {
                int read = client.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (read == 0) {
                    break;
                } else {
                    //客户端断开了
                    System.out.println("client: " + client.getRemoteAddress() + " closed......");
                    key.cancel();
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private void acceptHandler(SelectionKey key) {
        try {
            System.out.println("accent:");
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            final SocketChannel client = server.accept();
            client.configureBlocking(false);
            this.stg.nextSelector(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
