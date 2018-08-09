package com.compare;

import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractComparable<T> {
    private static boolean HASINIT = false;
    protected static ConcurrentHashMap<Class, Collection<Field>> keysCache;
    protected static ConcurrentHashMap<Class, Collection<Field>> fieldsCache;

    public abstract void update(Collection toUpdate);

    public abstract void save(Collection toSave);

    public AbstractComparable() {
        initCaches();
    }

    public static void initKeysFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Collection<Field> comparables = new LinkedList<>();
        Collection<Field> keys = new LinkedList<>();
        Assert.notEmpty(fields);
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(Comparable.class == annotation.annotationType());
                if (Comparable.class.equals(annotation.annotationType())) {
                    comparables.add(field);
                }
                if (Key.class.equals(annotation.annotationType())) {
                    comparables.add(field);
                    keys.add(field);
                }
            }
        }
        fieldsCache.put(clazz, comparables);
        keysCache.put(clazz, keys);
    }

    public CompareResult<T> compare(Collection<T> oldDatas, Collection<T> newDatas) {
        Assert.notEmpty(oldDatas);
        Assert.notEmpty(newDatas);
        Collection toUpdate = new LinkedList();
        Collection toSave = new LinkedList();
        Collection toRemove = new LinkedList();
        toSave.addAll(newDatas);
        out:
        for (Object oldData : oldDatas) {
            for (Object newData : newDatas) {
                if (compareFields(newData, oldData, keysCache)) {
                    if (!compareFields(newData, oldData, fieldsCache)) {
                        toUpdate.add(newData);
                    }
                    toSave.remove(newData);
                    continue out;
                }
            }
            toRemove.add(oldData);
        }
        return new CompareResult<T>(toUpdate, toSave, toRemove);
    }

    public CompareResult<T> compare2(Collection<T> oldDatas, Collection<T> newDatas) {
        final Map oldDataMap = new HashMap();
        final Map newDataMap = new HashMap();
        oldDatas.forEach(d -> oldDataMap.put(getKey(d), d));
        newDatas.forEach(d -> newDataMap.put(getKey(d), d));
        return compare(oldDataMap, newDataMap);
    }

    protected String getKey(T oldData) {
        Collection<Field> keyFields = keysCache.get(oldData.getClass());
        if (keyFields == null || keyFields.size() == 0) {
            initKeysFields(oldData.getClass());
            keyFields = keysCache.get(oldData.getClass());
        }
        System.out.println(keyFields);
        return keyFields.toString();
    }

    public CompareResult<T> compare(Map oldDatas, Map newDatas) {
        Assert.notEmpty(oldDatas);
        Assert.notEmpty(newDatas);
        Collection toUpdate = new LinkedList();
        Collection toSave = new LinkedList();
        Collection toRemove = new LinkedList();
        toSave.addAll(newDatas.values());
        for (Object entry : oldDatas.entrySet()) {
            Object oldData = ((Map.Entry) entry).getValue();
            Object newData = newDatas.get(((Map.Entry) entry).getKey());
            if (!compareFields(newData, oldData, fieldsCache)) {
                toUpdate.add(newData);
            } else {
                toSave.remove(newData);
            }
            toRemove.add(oldData);
        }
        return new CompareResult<T>(toUpdate, toSave, toRemove);
    }

    public boolean compareFields(Object o1, Object o2, ConcurrentHashMap<Class, Collection<Field>> cache) {
        System.out.println(o1.getClass() + "--" + o2.getClass());
        if (cache == null || cache.size() == 0) {
            initCaches();
        }
        Collection<Field> fields = cache.get(o1.getClass());
        Assert.notEmpty(fields);
        for (Field field : fields) {
            try {
                if (!compareField(field.get(o1), field.get(o2))) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException();
            }
        }
        return true;
    }

    public static void initCaches() {
        if (!HASINIT) {
            keysCache = new ConcurrentHashMap<>();
            fieldsCache = new ConcurrentHashMap<>();
            HASINIT = true;
        }
    }

    public boolean compareField(Object o1, Object o2) {
        if (o1 == null) {
            if (o2 == null) {
                return true;
            }
        } else {
            if (o1.equals(o2)) {
                return true;
            }
        }
        return false;
    }
}
