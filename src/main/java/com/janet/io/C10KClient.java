package com.janet.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2022/7/15
 * Time: 23:25
 *
 * @author jimas
 */
public class C10KClient {
    public static void main(String[] args) {
        SocketAddress serverAddr = new InetSocketAddress("192.168.64.127", 9090);
        List<SocketChannel> clients = new ArrayList<SocketChannel>();
        for (int i = 10000; i < 65000; i++) {
            try {
                SocketChannel client1 = SocketChannel.open();
                SocketChannel client2 = SocketChannel.open();

                client1.bind(new InetSocketAddress("192.168.64.1", i));

                client1.connect(serverAddr);
                client1.isOpen();
                clients.add(client1);
                //涉及到 路由下一跳 没有配置好
//                client2.bind(new InetSocketAddress("192.168.1.8", i));
//                client2.connect(serverAddr);
//                client2.isOpen();
//                clients.add(client2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("client num" + clients.size());
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
