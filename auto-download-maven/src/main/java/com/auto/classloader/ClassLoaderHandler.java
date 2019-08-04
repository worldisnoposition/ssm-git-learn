package com.auto.classloader;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class ClassLoaderHandler {
    private Map<String, DynamicClassLoader> dynamicClassLoaderMap = new ConcurrentHashMap();

    public void remove(String loaderName) {
        dynamicClassLoaderMap.remove(loaderName);
    }

    /**
     *
     */
    public void clear() {
        dynamicClassLoaderMap.clear();
    }

    /**
     * @param dynamicClassLoader
     */
    public void put(DynamicClassLoader dynamicClassLoader) {
        Preconditions.checkArgument(dynamicClassLoader == null, "dynamicClassLoader is null");
        Preconditions.checkArgument(StringUtils.isEmpty(dynamicClassLoader.getClasspath()), "dynamicClassLoader's classPath is null");
        dynamicClassLoaderMap.put(dynamicClassLoader.getClasspath(), dynamicClassLoader);
    }

    /**
     * @param dynamicClassLoader
     */
    public DynamicClassLoader putIfAbsent(DynamicClassLoader dynamicClassLoader) {
        Preconditions.checkArgument(dynamicClassLoader == null, "dynamicClassLoader is null");
        Preconditions.checkArgument(StringUtils.isEmpty(dynamicClassLoader.getClasspath()), "dynamicClassLoader's classPath is null");
        return dynamicClassLoaderMap.putIfAbsent(dynamicClassLoader.getClasspath(), dynamicClassLoader);
    }


}
