package com.spider.zhiye.rest;

import com.alibaba.fastjson.JSONObject;
import com.spider.zhiye.jpa.entity.ZhiyeEntity;
import com.spider.zhiye.jpa.repository.ZhiyeReposititoty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/entry")
@Slf4j
public class OrderController {

    @Autowired
    private ZhiyeReposititoty zhiyeReposititoty;

    @RequestMapping("/index")
    @Transactional
    public Object doneTransactional() {
        return "/index.";
    }

    @RequestMapping("/chufa")
    public Object add(@RequestBody String param) throws UnsupportedEncodingException {
        try {

            param = URLDecoder.decode(param, "utf-8");
            log.info(param);
            List<ZhiyeEntity> list = null;
            if (param.endsWith("=")) {
                param = param.substring(0, param.length() - 1);
            }
            list = JSONObject.parseArray(param, ZhiyeEntity.class);
//            zhiyeReposititoty.saveAll(list);
            return "success";
        } catch (Exception e) {
            log.error("报错了,长度param.length{}，{}", param.length(), e);
            return "error";
        }
    }

    @RequestMapping("query")
    private Object queryAll() {
        return zhiyeReposititoty.findAll();
    }
}