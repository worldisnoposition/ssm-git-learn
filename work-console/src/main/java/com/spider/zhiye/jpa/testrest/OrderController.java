package com.spider.zhiye.jpa.testrest;

import com.alibaba.fastjson.JSONObject;
import com.spider.zhiye.jpa.entity.OrderEntity;
import com.spider.zhiye.jpa.entity.ZhiyeEntity;
import com.spider.zhiye.jpa.repository.ZhiyeReposititoty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.AWTCharset;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/entry")
@Slf4j
public class OrderController {

    @Autowired
    private ZhiyeReposititoty zhiyeReposititoty;

    @RequestMapping("/index")
    @Transactional
    public Object doneTransactional(){
        return "/index.";
    }
    @RequestMapping("/chufa")
    public Object add(@RequestBody String param) throws UnsupportedEncodingException {
        try {
            param = URLDecoder.decode(param, "utf-8");
            param = param.substring(0, param.length() - 1);
            log.info(param);
            List<ZhiyeEntity> list = JSONObject.parseArray(param, ZhiyeEntity.class);
            zhiyeReposititoty.saveAll(list);
            return "success";
        }catch (Exception e){
            log.error("报错了，{}", e);
            return "error";
        }
    }

    @RequestMapping("query")
    private Object queryAll() {
        return zhiyeReposititoty.findAll();
    }
}