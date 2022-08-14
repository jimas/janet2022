package com.janet.zookeeper.config;

import com.janet.zookeeper.utils.MyWatcherCallback;
import com.janet.zookeeper.utils.ZkUtils;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Date: 2022/8/13
 * Time: 22:25
 *
 * @author jimas
 */
public class TestConfig {
    private static ZooKeeper zk;

    @Before
    public void start() {
        try {
            zk = ZkUtils.initZk();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConf() throws Exception {

        final MyWatcherCallback watcherCallback = new MyWatcherCallback(zk);
        final MyConfig config = new MyConfig();
        watcherCallback.setConfig(config);
        watcherCallback.await();
        while (true) {
            Thread.sleep(60);
            System.out.println(config.getConfig());
        }
    }
}
