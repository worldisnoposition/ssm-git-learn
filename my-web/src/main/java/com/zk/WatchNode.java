package com.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

class WatchNode implements Watcher {

    private static ZooKeeper zookeeper;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String path = "/haha";
        WatchNode watchNode = new WatchNode();
        zookeeper = new ZooKeeper("127.0.0.1:2181", 500000, new WatchNode());
        List<String> childs = watchNode.watchPath(path,CreateMode.PERSISTENT);
        System.out.println(childs);
        Thread.sleep(Integer.MAX_VALUE);
    }
    public List<String> watchPath(final String path, final CreateMode createMode) throws KeeperException, InterruptedException {
        if(zookeeper.exists(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    System.out.println("create watcher");
                    watchPath(path,createMode);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    doAction(event.getPath());
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }) == null) {
            zookeeper.create(path, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
        }
        return zookeeper.getChildren(path,false,new Stat());
    }
    public void doAction(String path) throws KeeperException, InterruptedException {
        byte[] bytes = zookeeper.getData(path,null,null);
        System.out.println(new String(bytes));
    }

    @Override
    public void process(WatchedEvent event) {
        //todo 获取子节点列表
    }
}
