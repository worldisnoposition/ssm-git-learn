package com.create.result;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ResultTest {
    private ThreadLocal threadLocal;
    private ConcurrentHashMap<String, Field> entityFieldsMap;

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException, NoSuchFieldException {
        ResultTest resultTest = new ResultTest();
        resultTest.init();
        //todo 对于从头到尾一直传递的重要参数可以放到threadLocal里
        resultTest.test();
    }

    private void init() {
        threadLocal = new ThreadLocal();
        Class clazz = PriceEntity.class;
        entityFieldsMap = new ConcurrentHashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            entityFieldsMap.put(field.getName(), field);
        }
    }

    public void test() throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException, NoSuchFieldException {
        String path = "H:\\ssm-git-com.learn\\src\\main\\resource\\result.json";        //要遍历的路径
        File f = new File(path);
        InputStream in = new FileInputStream(f);
        byte[] bytes = new byte[((int) (f.length()))];
        in.read(bytes);
        String str = new String(bytes);
        List<PriceEntity> priceEntityList = JSONObject.parseArray(str, PriceEntity.class);
        //todo 在这里可以补充一个排序工具，根据注解上的字段将数据进行排序
        //todo 把priceEntity转换成projectRespDTO
        Target target = this.buildTarget(priceEntityList);
        LinkedList<Level> queue = new LinkedList();
        System.out.println(JSONObject.toJSON(target));
    }

    private Target buildTarget(List<PriceEntity> priceEntities) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException, NoSuchFieldException {
        Target target = new ProjectRespDTO();
        for (PriceEntity priceEntity : priceEntities) {
            this.fieldsSetting(target, priceEntity);
        }
        return target;
    }

    /**
     * 这个主要是处理带泛型数据的
     *
     * @param priceEntity
     * @param typeName
     * @return
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    private Object buildParameterizeType(PriceEntity priceEntity, String typeName) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException, NoSuchFieldException {
        Object target = buildParameterizeTypeObjectByTypeName(typeName);
        if(this.fieldsSetting(target, priceEntity)) {
            return target;
        }else{
            return null;
        }
    }

    private Object buildParameterizeTypeObjectByTypeName(String typeName) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Class clazz = Class.forName(typeName);
        //constructors也可以缓存到threadLocal中
        Object target = clazz.getConstructors()[0].newInstance(new ProjectRespDTO());
        return target;
    }

    private boolean fieldsSetting(Object target, PriceEntity priceEntity) throws IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException, NoSuchFieldException {
        boolean result = false;
        for (Field field : this.getFields(target.getClass())) {
            field.setAccessible(true);//todo 如果上一步fields是缓存的结果，那么这一步可以省掉
            result = this.setFieldOrAddToList(field, target, priceEntity) || result;
        }
        return result;
    }

    private boolean setFieldOrAddToList(Field field, Object target, PriceEntity priceEntity) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Type type = field.getAnnotatedType().getType();
        if (type instanceof ParameterizedType) {
            //没添加成功，就意味着要从上一级继续构建对象
            return this.addParameterizedTypeObjectToList(field, target, priceEntity, (ParameterizedType) type);
        } else {
            //todo 如果本身存在属性，需要进行判断，若不同则抛出错误
            return this.setField(field, target, priceEntity);
        }
    }

    private boolean setField(Field field, Object target, PriceEntity priceEntity) throws IllegalAccessException {
        Field entityField = entityFieldsMap.get(field.getName());
        if (entityField != null) {
            Object doneFieldValue = field.get(target);
            Object todoFieldValue = entityField.get(priceEntity);
            if (doneFieldValue != null && (!doneFieldValue.equals(todoFieldValue))) {
                System.out.println("doneFieldValue"+doneFieldValue+",todoFieldValue"+todoFieldValue);
                throw new RuntimeException("属性值冲突");
            }
            Object fieldValue = entityField.get(priceEntity);
            if(fieldValue == null){
                return false;
            }else {
                field.set(target, fieldValue);
                return true;
            }
        }
        return false;
    }

    private boolean addParameterizedTypeObjectToList(Field field, Object target, PriceEntity priceEntity, ParameterizedType parameterizedType) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //泛型是可以嵌套的，这版暂时不做嵌套泛型适配，但可以报个错
        Type[] type = parameterizedType.getActualTypeArguments();
        if (type.length != 1) {
            throw new RuntimeException("泛型推断错误，list不包含泛型或存在泛型嵌套");
        }
        if (parameterizedType.getRawType().equals(LinkedList.class)) {
            //todo这里set或者add到当前field的list中
            return this.setInToList(target, priceEntity, field, type[0]);
        } else {
            //如果不是list类型则正常设置属性
            return this.setField(field, target, priceEntity);
        }
    }

    private boolean setInToList(Object target, PriceEntity priceEntity, Field field, Type type) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException, NoSuchFieldException {
        //todo 获取需要对比的主键，从target的list中取得，
        LinkedList list = (LinkedList) field.get(target);
        if (list != null && list.size() > 0) {
            if (!this.isTheSameKey(list.getLast(), field, priceEntity, type)) {
                //不同放到当前list中
                Object listElement = this.buildParameterizeType(priceEntity, type.getTypeName());
                if(listElement != null){
                    list.add(listElement);
                    return true;
                }
                return false;
            } else {
                //相同设置到list最后一个对象中
                return this.fieldsSetting(list.getLast(), priceEntity);
            }
        } else {
            list = new LinkedList();
            field.set(target, list);
            Object listElement = this.buildParameterizeType(priceEntity, type.getTypeName());
            if(listElement != null){
                list.add(listElement);
                return true;
            }
            return false;
        }
    }

    private boolean isTheSameKey(Object target, Field field, PriceEntity priceEntity, Type type) throws InvocationTargetException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //todo 从target获取主键，先设置一个，后续可设置包括联合主键，甚至带有表达式的主键
        //todo 从注解中获取主键字段名
        Object listTarget = buildParameterizeTypeObjectByTypeName(type.getTypeName());
        String keyFieldName = listTarget.getClass().getAnnotation(Level.class).key();
        if (keyFieldName == null) {
            return false;
        } else {
            Field keyField = listTarget.getClass().getDeclaredField(keyFieldName);
            keyField.setAccessible(true);
            Field entityField = entityFieldsMap.get(keyFieldName);
            if (entityField == null) {
                return false;
            }
            Object entityId = entityField.get(priceEntity);
            if (entityId == null) {
                return false;
            }
            Object targetId = keyField.get(target);
            return entityId != null && entityId.equals(targetId);
        }
    }


    private Field[] getFields(Class clazz) {
        //不应该这样获取，其实完全可以将Field缓存到map里，同时，setAccess(true)
        //todo 这是接下来的工作，利用spring的扫描注解的机制初始化
        Field[] fields = clazz.getDeclaredFields();
        return fields;
    }
}
