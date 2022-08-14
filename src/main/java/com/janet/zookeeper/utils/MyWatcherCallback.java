package com.janet.zookeeper.utils;

import com.janet.zookeeper.config.MyConfig;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Date: 2022/8/13
 * Time: 22:29
 *
 * @author jimas
 */
public class MyWatcherCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback, AsyncCallback.Create2Callback {
    private ZooKeeper zk;
    private MyConfig config;
    private CountDownLatch cc = new CountDownLatch(1);

    public MyWatcherCallback(ZooKeeper zk) {
        this.zk = zk;
    }

    public void setConfig(MyConfig config) {
        this.config = config;
    }

    /**
     * 节点事件回调
     *
     * @param event
     */
    @Override
    public void process(WatchedEvent event) {
        System.out.println("mywatcher:" + event.toString());
        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                break;
            case NodeDataChanged:
                zk.getData("/AppConfig", this, this, "xx");
                break;
            case NodeChildrenChanged:
                break;
            case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
        }
    }

    /**
     * 节点存在回调
     *
     * @param rc
     * @param path
     * @param ctx
     * @param stat
     */
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat != null) {
            zk.getData("/AppConfig", this, this, "sss");
        }
    }

    /**
     * getData 获取节点值回调
     *
     * @param rc
     * @param path
     * @param ctx
     * @param data
     * @param stat
     */
    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        if (data != null) {
            config.setConfig(new String(data));
            cc.countDown();
        }
    }

    /**
     * 创建回调
     *
     * @param rc
     * @param path
     * @param ctx
     * @param name
     * @param stat
     */
    @Override
    public void processResult(int rc, String path, Object ctx, String name, Stat stat) {
        if (path != null) {

        }
    }

    public void await() {
        try {
            zk.exists("/AppConfig", this, this, "sss");
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
