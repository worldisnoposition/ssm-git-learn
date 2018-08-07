package compare;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractComparable<T> {
    private static boolean HASINIT = false;
    protected static ConcurrentHashMap<Class, Collection<Field>> keysCache;
    protected static ConcurrentHashMap<Class, Collection<Field>> fieldsCache;

    public abstract void update(Collection toUpdate);

    public abstract void save(Collection toSave);

    public AbstractComparable(Class clazz) {
        initCaches(clazz);
        if (keysCache.get(clazz) == null || fieldsCache.get(clazz) == null) {
            getKeysFields(clazz);
        }
    }

    private void getKeysFields(Class<? extends AbstractComparable> clazz) {
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
                    System.out.println("同一个id");
                    if (!compareFields(newData, oldData, fieldsCache)) {
                        toUpdate.add(newData);
                    }
                    toSave.remove(newData);
                    continue out;
                }
            }
            toRemove.add(oldData);
        }
        return new CompareResult<T>(toUpdate,toSave,toRemove);
    }

    public boolean compareFields(Object o1, Object o2, ConcurrentHashMap<Class, Collection<Field>> cache) {
        System.out.println(o1.getClass() + "--" + o2.getClass());
        if (cache == null || cache.size() == 0) {
            initCaches(o1.getClass());
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

    protected void initCaches(Class<?> aClass) {
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
