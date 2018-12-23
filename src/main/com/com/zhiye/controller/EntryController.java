package com.zhiye.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhiye.service.ZhiyeService;
import com.zhiye.vo.ZhiyeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {
    @Autowired
    private ZhiyeService zhiyeService;

    @RequestMapping(name="/a",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String entry(@RequestBody String param) throws UnsupportedEncodingException {
//        param = URLDecoder.decode(param);
//        param = new String(param,)
        String xmlString = null;
        try {
            //todo 读取文件夹下的文件，依次转为对象存表
            String path = "H:\\ssm-git-learn\\src\\main\\resource\\spider";        //要遍历的路径
            File file = new File(path);        //获取其file对象
            File[] fs = file.listFiles();    //遍历path下的文件和目录，放在File数组中
            for (File f : fs) {                    //遍历File[]数组
                if (!f.isDirectory()) {
                    byte[] strBuffer = null;
                    int flen = 0;
//                File xmlfile = new File("/data/local/getHomePage.xml");
                    try {
                        InputStream in = new FileInputStream(f);
                        flen = (int) f.length();
                        strBuffer = new byte[flen];
                        in.read(strBuffer, 0, flen);
                    } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    xmlString = new String(strBuffer, "utf-8"); //构建String时，可用byte[]类型，

                    List<ZhiyeVo> list = JSONObject.parseArray(xmlString, ZhiyeVo.class);
                    if(list != null && list.size()!=0){
                        zhiyeService.saveZhiye(list);
                    }

                }
            }
        }catch(Exception e){
            System.out.println(xmlString);
            e.printStackTrace();
        }
        return "{}";
    }
}
