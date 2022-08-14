package com.janet.zookeeper.config;

import com.janet.zookeeper.utils.LockWatcherCallback;
import com.janet.zookeeper.utils.ZkUtils;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Date: 2022/8/13
 * Time: 23:37
 *
 * @author jimas
 */
public class TestLock {
    private ZooKeeper zk;
    @Before
    public void connect() throws Exception {
        zk = ZkUtils.initZk();
    }

    @After
    public void close() throws InterruptedException {
        zk.close();
    }

    @Test
    public void testlock() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LockWatcherCallback watcherCallback = new LockWatcherCallback(zk);
                    //tryLock
                    watcherCallback.tryLock("/test/lock");
                    //done
                    watcherCallback.done();
                    //unlock
                    watcherCallback.unlock();
                }
            }).start();
        }

        while (true) {
        }
    }


}
