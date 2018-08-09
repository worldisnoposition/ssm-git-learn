package com.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil {
    // Spring应用上下文环境
//    private static ApplicationContext applicationContext;
    @Autowired
    private DefaultListableBeanFactory fty;
    @Autowired
    private AutowireCapableBeanFactory beanFactory;
    /*
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext
     */
//    public void setApplicationContext(ApplicationContext applicationContext) {
//        SpringContextUtil.applicationContext = applicationContext;
//        fty= (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
//
//    }
    /**
     * @return ApplicationContext
     */
//    public ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
    /**
     * 获取对象
     *
     * @param name
     * @return Object
     * @throws BeansException
     */
    public Object getBean(String name) throws BeansException {
        return beanFactory.getBean(name);
    }

    public void registBean(Object object){
        Class clazz = object.getClass();
        registBean(clazz.getName(),clazz);
    }
    public void registBean(String beanName, Object object){
//        BeanDefinition bean = new GenericBeanDefinition();
//        //类的全路径
//        bean.setBeanClassName(clazz.getName());
        //注册Bean
//        fty.registerBeanDefinition(beanName, bean);
        fty.registerSingleton(beanName,object);
        beanFactory.autowireBean(object);
    }
}
