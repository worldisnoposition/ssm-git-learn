package com.create.result;

import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.JsonNode;
import org.springframework.beans.BeanUtils;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class ResultTest {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        ResultTest resultTest = new ResultTest();
        resultTest.test();
    }
    public void test() throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        String path = "H:\\ssm-git-learn\\src\\main\\resource\\result.json";        //要遍历的路径
        File f = new File(path);
        InputStream in = new FileInputStream(f);
        byte[] bytes = new byte[((int) (f.length()))];
        in.read(bytes);
        String str = new String(bytes);
        List<PriceEntity> priceEntityList = JSONObject.parseArray(str,PriceEntity.class);
        //todo 把priceEntity转换成projectRespDTO
        ProjectRespDTO projectRespDTO = this.buildProjectResp(priceEntityList.get(0));
        LinkedList<Level> queue = new LinkedList();
        System.out.println(JSONObject.toJSON(projectRespDTO));
//        System.out.println(priceEntityList);
    }
    private Class LINKEDLIST_CLASS = new LinkedList<>().getClass();
    private ProjectRespDTO buildProjectResp(PriceEntity priceEntity) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        ProjectRespDTO projectRespDTO = new ProjectRespDTO();
        BeanUtils.copyProperties(priceEntity,projectRespDTO);
        for(Field field:this.getFields(projectRespDTO.getClass())){
            field.setAccessible(true);
            Type type = field.getAnnotatedType().getType();
            if(type instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType)type;
                //泛型是可以嵌套的，这版暂时不做嵌套泛型适配，但可以报个错
                Type[] t = parameterizedType.getActualTypeArguments();
                if(parameterizedType.getRawType().equals(LinkedList.class)){
                    field.set(projectRespDTO,this.buildProjectRespList(priceEntity,t[0].getTypeName()));
                }
            }
        }
        return projectRespDTO;
    }
    private Object buildProjectRespList(PriceEntity priceEntity,String typeName) throws IllegalAccessException, ClassNotFoundException, InstantiationException, InvocationTargetException {
        Class clazz = Class.forName(typeName);
        Object[] a = clazz.getConstructors();
        Object o = clazz.getConstructors()[0].newInstance(new ProjectRespDTO());
        BeanUtils.copyProperties(priceEntity,o);
        LinkedList list = new LinkedList();
        list.add(o);
        for(Field field:this.getFields(o.getClass())){
            field.setAccessible(true);
            Type type = field.getAnnotatedType().getType();
            if(type instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType)type;
                //泛型是可以嵌套的，这版暂时不做嵌套泛型适配，但可以报个错
                Type[] t = parameterizedType.getActualTypeArguments();
                if(parameterizedType.getRawType().equals(LinkedList.class)){
                    field.set(o,this.buildProjectRespList(priceEntity,t[0].getTypeName()));
                }
            }
        }
        return list;
    }

    private Field[] getFields(Class clazz){
        Field[] field = clazz.getDeclaredFields();
        return field;
    }
}
