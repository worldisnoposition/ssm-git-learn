//package common.com.curator;
//
//import com.google.common.annotations.VisibleForTesting;
//import com.netflix.com.curator.RetryPolicy;
//import com.netflix.com.curator.framework.CuratorFramework;
//import com.netflix.com.curator.framework.CuratorFrameworkFactory;
//import com.netflix.com.curator.retry.ExponentialBackoffRetry;
//import org.apache.zookeeper.CreateMode;
//import org.apache.zookeeper.ZooDefs;
//
//public class TestCurator {
//
//    @Test
//    public void testName() throws Exception {
//        // 1000：表示curator链接zk的时候超时时间是多少 3：表示链接zk的最大重试次数
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//        String connectString = "master:2181,slave1:2181,slave2:2181";
//        int sessionTimeoutMs = 5000;// 这个值只能在4000-40000ms之间 表示链接断掉之后多长时间临时节点会消失
//        int connectionTimeoutMs = 3000;// 获取链接的超时时间
//        CuratorFramework client = CuratorFrameworkFactory.newClient(
//                connectString, sessionTimeoutMs, connectionTimeoutMs,
//                retryPolicy);
//        client.start();// 开启客户端
//
//
//        client.create().creatingParentsIfNeeded()// 如果父节点不存在则创建
//                .withMode(CreateMode.EPHEMERAL)//指定节点类型,临时节点
//                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)// 设置节点权限信息
//                .forPath("/monitor/test123");//指定节点名称
//
//
//    }
//
//}
