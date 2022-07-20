package com.janet.io.nio;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * Date: 2022/7/16
 * Time: 21:45
 *
 * @author jimas
 */
public class SocketNIO {

    public static void main(String[] args) throws Exception {
        LinkedList<SocketChannel> clients = new LinkedList<>();
        //服务端开启监听：接受客户端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9090));
        //只让接受客户端 不阻塞
        serverSocketChannel.configureBlocking(false);

        while (true) {
            Thread.sleep(100);
            SocketChannel client = serverSocketChannel.accept();
            if (client != null) {
                //不阻塞
                client.configureBlocking(false);
                System.out.println("client..port: " + client.socket().getPort());
                clients.add(client);
            }
            ByteBuffer dft = ByteBuffer.allocate(4096);
            //遍历已经链接进来的客户端能不能读写数据
            for (SocketChannel socketChannel : clients) {
                //不会阻塞
                int read = socketChannel.read(dft);
                if (read > 0) {
                    dft.flip();
                    byte[] bytes = new byte[dft.limit()];
                    System.out.println(client.socket().getPort() + ":" + dft.get(bytes));
                    dft.clear();
                }
            }
        }
    }
}
