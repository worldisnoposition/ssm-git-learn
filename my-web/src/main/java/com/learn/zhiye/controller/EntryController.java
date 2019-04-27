package com.learn.zhiye.controller;
//import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import com.learn.websocket.WebSocketServiceImpl;
import com.learn.zhiye.service.ZhiyeService;
import com.learn.zhiye.vo.ZhiyeVo;
import com.test.log.LoggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {
    private static Logger logger = LoggerFactory.getLogger(EntryController.class);
    public static void main(String[] args) {
        int a=5;
        System.out.println(a++);
        int[] nums = {1,1,1,2,2,2,2,3,3,3};
        int result = 2;
        if(nums.length<2){
            System.out.println("result:"+nums.length);
            return ;
        }
        for(int i=2;i<nums.length;i++){
            System.out.println("=======================");
            System.out.println(JSONObject.toJSONString(nums));
            System.out.println("i:"+i+",result:"+result);
            if(nums[i] != nums[result-2]){
                nums[result++] = nums[i];
                System.out.println(JSONObject.toJSONString(nums));
            }

        }
        System.out.println("result:"+nums.length);
        return ;
    }
    @Autowired
    private ZhiyeService zhiyeService;

    @Resource
    private WebSocketServiceImpl webSocketService;
    @Resource
    private LoggerBuilder loggerBuilder;
    @PostConstruct
    public void init(){
        System.out.println("init EntryController成功");

        System.out.println(AopUtils.isAopProxy(this));
        System.out.println(AopUtils.isCglibProxy(this));
        System.out.println(AopUtils.isJdkDynamicProxy(this));
    }
    @RequestMapping(value="/chufa",name="/chufa",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String chufa(@RequestBody String param, HttpServletResponse response) throws IOException, InterruptedException {
//        Object o = AopContext.currentProxy();
        Logger log1 = loggerBuilder.getLogger("zhiye");
        log1.info("报文：{}",param);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
//        Logger log2 = loggerBuilder.b("hahalog");
//        log1.info("hahaha");
        String a = URLDecoder.decode(param,"utf-8");
        System.out.println(a+"");
        System.out.println(AopUtils.isAopProxy(this));
        System.out.println(AopUtils.isCglibProxy(this));
        System.out.println(AopUtils.isJdkDynamicProxy(this));
//        AopUtils.isCglibProxy() //cglib
//        AopUtils.isJdkDynamicProxy()
//        for(int i=0;i<10;i++){
//            webSocketService.chufa(i);
//        }
        this.entry(a);
        return "";
    }

//    @RequestMapping(value="/a",name="/a",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String entry( String param) throws UnsupportedEncodingException {
//        param = URLDecoder.decode(param);
//        param = new String(param,)
        String xmlString = null;
        List<ZhiyeVo> list = JSONObject.parseArray(param.substring(12,param.length()-1), ZhiyeVo.class);
        System.out.println(JSONObject.toJSONString(list));
        if(list != null && list.size()!=0){
            zhiyeService.saveZhiye(list);
        }
        return null;
//        try {
            //todo 读取文件夹下的文件，依次转为对象存表
//            String path = "F:\\chromedownload\\spyder";        //要遍历的路径
////            String path = "H:\\ssm-git-com.learn\\src\\main\\resource\\spider";        //要遍历的路径
//            File file = new File(path);        //获取其file对象
//            File[] fs = file.listFiles();    //遍历path下的文件和目录，放在File数组中
//            for (File f : fs) {                    //遍历File[]数组
//                if (!f.isDirectory()) {
//                    byte[] strBuffer = null;
//                    int flen = 0;
//                    try {
//                        InputStream in = new FileInputStream(f);
//                        flen = (int) f.length();
//                        strBuffer = new byte[flen];
//                        in.read(strBuffer, 0, flen);
//                        in.close();
//
//                    } catch (FileNotFoundException e) {
//// TODO Auto-generated catch block
//                        e.printStackTrace();
//                    } catch (IOException e) {
//// TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
////                    xmlString = new String(strBuffer, "utf-8"); //构建String时，可用byte[]类型，
//
//                    List<ZhiyeVo> list = JSONObject.parseArray(param, ZhiyeVo.class);
//                    System.out.println(JSONObject.toJSONString(list));
//                    if(list != null && list.size()!=0){
//                        zhiyeService.saveZhiye(list);
//                    }
//
//                }
//            }

//        }catch(Exception e){
//            System.out.println(xmlString);
//            e.printStackTrace();
//        }
//        return "{}";
    }
}
