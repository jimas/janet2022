package com.janet.zookeeper.utils;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * Date: 2022/8/13
 * Time: 22:29
 *
 * @author jimas
 */
public class LockWatcherCallback implements Watcher, AsyncCallback.Children2Callback, AsyncCallback.StatCallback, AsyncCallback.DataCallback, AsyncCallback.StringCallback {
    private ZooKeeper zk;
    private CountDownLatch cc = new CountDownLatch(1);

    private String pathName;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public LockWatcherCallback(ZooKeeper zk) {
        this.zk = zk;
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
                zk.getChildren("/test", false, this, "xxxxx");
                break;
            case NodeDataChanged:
                //zk.getData("/AppConfig", this, this, "xx");
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
            //zk.getData("/AppConfig", this, this, "sss");
        } else {
            //防止速度太快，比如上个接口执行完，且释放了锁（节点删除）当前节点才开始监控，其实这就监控了一个空节点
            //因此节点不存在时，也要countDown
            cc.countDown();
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

    }

    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        if (name != null) {
            pathName = name;
            zk.getChildren("/test", false, this, "xx");
        }
    }

    /**
     * 获取子节点列表回调
     *
     * @param rc
     * @param path
     * @param ctx
     * @param children
     * @param stat
     */
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        // System.out.println(path + "==" + children);
        if (children != null) {
            final List<String> list = children.stream().map(t -> path + "/" + t).sorted().collect(Collectors.toList());
            //排序
            final int i = list.indexOf(pathName);
            if (i == 0) {
                System.out.println(Thread.currentThread().getName() + " i am first....");
                cc.countDown();
            } else {
                zk.exists(list.get(i - 1), this, this, "xxx");
            }

        }
    }

    public void unlock() {
        System.out.println(Thread.currentThread().getName() + " unlock.....");
        try {
            zk.delete(pathName, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public void done() {
        System.out.println(Thread.currentThread().getName() + " working.....");
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void tryLock(String path) {
        System.out.println(Thread.currentThread().getName() + " tryLock.....");
        zk.create(path, Thread.currentThread().getName().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, "xxx");
        try {
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
