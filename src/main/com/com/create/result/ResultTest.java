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

    private ConcurrentHashMap<String, Field> entityFieldsMap;

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        ResultTest resultTest = new ResultTest();
        resultTest.init();
        resultTest.test();
    }

    private void init() {
        Class clazz = PriceEntity.class;
        entityFieldsMap = new ConcurrentHashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            entityFieldsMap.put(field.getName(), field);
        }
    }

    public void test() throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        String path = "H:\\ssm-git-learn\\src\\main\\resource\\result.json";        //要遍历的路径
        File f = new File(path);
        InputStream in = new FileInputStream(f);
        byte[] bytes = new byte[((int) (f.length()))];
        in.read(bytes);
        String str = new String(bytes);
        List<PriceEntity> priceEntityList = JSONObject.parseArray(str, PriceEntity.class);
        //todo 把priceEntity转换成projectRespDTO
        Target target = this.buildTarget(priceEntityList);
        LinkedList<Level> queue = new LinkedList();
        System.out.println(JSONObject.toJSON(target));
    }

    private Target buildTarget(List<PriceEntity> priceEntities) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
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
    private Object buildParameterizeType(PriceEntity priceEntity, String typeName) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        LinkedList list = new LinkedList();
        Object target = buildParameterizeTypeObjectByTypeName(typeName);
        list.add(target);
        this.fieldsSetting(target, priceEntity);
        return list;
    }

    private Object buildParameterizeTypeObjectByTypeName(String typeName) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Class clazz = Class.forName(typeName);
        Object target = clazz.getConstructors()[0].newInstance(new ProjectRespDTO());
        return target;
    }

    private void fieldsSetting(Object target, PriceEntity priceEntity) throws IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException {
        for (Field field : this.getFields(target.getClass())) {
            field.setAccessible(true);
            this.setFieldOrAddToList(field, target, priceEntity);
        }
    }

    private void setFieldOrAddToList(Field field, Object target, PriceEntity priceEntity) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Type type = field.getAnnotatedType().getType();
        if (type instanceof ParameterizedType) {
            this.addParameterizedTypeObjectToList(field, target, priceEntity, (ParameterizedType) type);
        } else {
            this.setField(field, target, priceEntity);
        }
    }

    private void setField(Field field, Object target, PriceEntity priceEntity) throws IllegalAccessException {
        Field entityField = entityFieldsMap.get(field.getName());
        if (entityField != null) {
            field.set(target, entityField.get(priceEntity));
        }
    }

    private void addParameterizedTypeObjectToList(Field field, Object target, PriceEntity priceEntity, ParameterizedType parameterizedType) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //泛型是可以嵌套的，这版暂时不做嵌套泛型适配，但可以报个错
        Type[] type = parameterizedType.getActualTypeArguments();
        if (type.length != 1) {
            throw new RuntimeException("泛型推断错误，list不包含泛型或存在泛型嵌套");
        }
        if (parameterizedType.getRawType().equals(LinkedList.class)) {
            field.set(target, this.buildParameterizeType(priceEntity, type[0].getTypeName()));
        } else {
            //如果不是list类型则正常设置属性
            this.setField(field, target, priceEntity);
        }
    }


    private Field[] getFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return fields;
    }
}
