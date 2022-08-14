package com.janet.zookeeper.utils;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * Date: 2022/8/13
 * Time: 22:04
 *
 * @author jimas
 */
public class ZkUtils {
    public static final String zkString = "192.168.64.129:2181,192.168.64.131:2181,192.168.64.132:2181,192.168.64.133:2181/testLock";
    private static CountDownLatch cc = new CountDownLatch(1);

    public static ZooKeeper initZk() throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(zkString, 3000, new DefaultWatcher(cc));
        cc.await();
        return zooKeeper;
    }

}
