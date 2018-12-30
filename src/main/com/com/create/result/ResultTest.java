package com.create.result;

import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.JsonNode;
import org.springframework.beans.BeanUtils;
import java.io.*;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class ResultTest {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        ResultTest resultTest = new ResultTest();
        resultTest.test();
    }
    public void test() throws IOException, IllegalAccessException {
        String path = "H:\\ssm-git-learn\\src\\main\\resource\\result.json";        //要遍历的路径
        File f = new File(path);
        InputStream in = new FileInputStream(f);
        byte[] bytes = new byte[((int) (f.length()))];
        in.read(bytes);
        String str = new String(bytes);
        List<PriceEntity> priceEntityList = JSONObject.parseArray(str,PriceEntity.class);
        //todo 把priceEntity转换成projectRespDTO
        ProjectRespDTO projectRespDTO = new ProjectRespDTO();
        this.buildProjectResp(projectRespDTO,priceEntityList.get(0));
        LinkedList<Level> queue = new LinkedList();
        System.out.println(JSONObject.toJSON(projectRespDTO));
//        System.out.println(priceEntityList);
    }
    private Class LINKEDLIST_CLASS = new LinkedList<>().getClass();
    private void buildProjectResp(Object o, PriceEntity priceEntity) throws IllegalAccessException {
        BeanUtils.copyProperties(o,priceEntity);
        for(Field field:this.getFields(o.getClass())){
            field.setAccessible(true);
            if(field.getType().equals(LinkedList.class)){
//                field.get(o)
                this.buildProjectRespList((LinkedList) field.get(o),priceEntity);
            }
        }
        System.out.println("field"+JSONObject.toJSON(o));
    }
    private void buildProjectRespList(LinkedList list, PriceEntity priceEntity) throws IllegalAccessException {
        BeanUtils.copyProperties(list.get(0),priceEntity);
        for(Field field:this.getFields(list.get(0).getClass())){
            field.setAccessible(true);
            if(field.getType().equals(LinkedList.class)){
                this.buildProjectRespList((LinkedList) field.get(list.get(0)),priceEntity);
            }
        }
        System.out.println("list"+JSONObject.toJSON(list));
    }

    private Field[] getFields(Class clazz){
        Field[] field = clazz.getDeclaredFields();
        return field;
    }
}
