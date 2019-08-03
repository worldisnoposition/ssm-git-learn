package com.auto.download;

import com.auto.deal.classloader.DynamicClassLoader;
import com.auto.deal.proxy.DefaultCommonHandler;
import com.auto.deal.proxy.test.TestService;

import java.lang.reflect.Proxy;

public class MainClass {
    //    private final static String testClassName = "com.word.segmentation.TestClass";
    private final static String testClassName = "com.auto.deal.proxy.test.TestServiceImpl";
    private final static String classPath = "D:\\coding\\java\\ssm-git-learn\\word-segmentation\\target\\classes";
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println(ClassLoader.getSystemClassLoader().getClass());
        System.out.println(MainClass.class.getClassLoader());
//        MavenHandler.download();
        DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(classPath);
        dynamicClassLoader.loadClass(testClassName);
        DefaultCommonHandler defaultCommonHandler = new DefaultCommonHandler(Class.forName(testClassName, true, dynamicClassLoader).newInstance());
        TestService testProxy = (TestService) Proxy.newProxyInstance(dynamicClassLoader, new Class[]{TestService.class}, defaultCommonHandler);
        testProxy.service();
    }
}
