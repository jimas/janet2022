package com.janet.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Date: 2022/7/15
 * Time: 23:44
 *
 * @author jimas
 */
public class SocketIO {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9090, 20);
        System.out.println("step1: new ServerSocket(9090) ");

        while (true) {
            //阻塞，等待客户端连接
            Socket client = serverSocket.accept();
            System.out.println("step2:client\t" + client.getPort());

            new Thread(() -> {
                try {
                    InputStream inputStream = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    while (true) {
                        //阻塞2
                        String line = reader.readLine();
                        if (line != null) {
                            System.out.println(line);
                        } else {
                            client.close();
                            break;
                        }
                        System.out.println("客户端断开连接");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
