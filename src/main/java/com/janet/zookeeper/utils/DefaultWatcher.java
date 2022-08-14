package com.janet.zookeeper.utils;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * Date: 2022/8/13
 * Time: 22:22
 *
 * @author jimas
 */
public class DefaultWatcher implements Watcher {

    private CountDownLatch cc;

    public DefaultWatcher(CountDownLatch cc) {
        this.cc = cc;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("defaultwatcher：" + event.toString());
        switch (event.getState()) {
            case Unknown:
                break;
            case Disconnected:
                break;
            case NoSyncConnected:
                break;
            case SyncConnected:
                cc.countDown();
                break;
            case AuthFailed:
                break;
            case ConnectedReadOnly:
                break;
            case SaslAuthenticated:
                break;
            case Expired:
                break;
            case Closed:
                break;
        }

    }
}
