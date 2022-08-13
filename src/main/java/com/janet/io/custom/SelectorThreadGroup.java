package com.janet.io.custom;

import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Date: 2022/7/26
 * Time: 21:27
 *
 * @author jimas
 */
public class SelectorThreadGroup {
    SelectorThread[] threads = null;
    private AtomicInteger xid = new AtomicInteger();

    public SelectorThreadGroup(int num) {
        this.threads = new SelectorThread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new SelectorThread(this);
            new Thread(threads[i]).start();
        }
    }

    public void bind(int port) {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(port));
            server.configureBlocking(false);
            nextSelector(server);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextSelector(Channel server) {
        SelectorThread selectorThread = next();
        selectorThread.lbq.add(server);
        selectorThread.selector.wakeup();
    }

    private SelectorThread next() {
        return threads[xid.getAndIncrement() % threads.length];
    }
}
