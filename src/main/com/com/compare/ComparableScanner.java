package com.compare;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ComparableScanner implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Map<String, Object> comparableMap = configurableListableBeanFactory.getBeansWithAnnotation(ComparableBean.class);
        AbstractComparable.initCaches();
        for(Object o:comparableMap.values()){
            AbstractComparable.initKeysFields(o.getClass());
        }

    }
}
