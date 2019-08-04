package com.auto;

import com.auto.classloader.DynamicClassLoader;
import com.auto.proxy.DefaultCommonHandler;
import com.auto.client.TestService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Proxy;

@Component
public class MainClass {
    @PostConstruct
    public void init() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println(ClassLoader.getSystemClassLoader().getClass());
        System.out.println(MainClass.class.getClassLoader());
//        MavenHandler.downloadJarFile();
        //todo classLoader 放到map中动态加载，带版本号，带类名
        //todo 面向接口
        //todo spring 在中注册bean，加锁，动态卸载更新成新的
        DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(classPath);
        dynamicClassLoader.loadClass(testClassName);
        DefaultCommonHandler defaultCommonHandler = new DefaultCommonHandler(Class.forName(testClassName, true, dynamicClassLoader).newInstance());
        TestService testProxy = (TestService) Proxy.newProxyInstance(dynamicClassLoader, new Class[]{TestService.class}, defaultCommonHandler);
        testProxy.service();
    }
    //    private final static String testClassName = "com.word.segmentation.TestClass";
    private final static String testClassName = "com.auto.proxy.test.TestServiceImpl";
    private final static String classPath = "D:\\coding\\java\\ssm-git-learn\\word-segmentation\\target\\classes";


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        this.init();
    }
}
