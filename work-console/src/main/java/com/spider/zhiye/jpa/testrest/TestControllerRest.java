package com.spider.zhiye.jpa.testrest;

import com.alibaba.fastjson.JSONObject;
import com.spider.zhiye.jpa.entity.SimpleWorkInfoEntity;
import com.spider.zhiye.jpa.repository.SimpleWorkInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/index")
@Slf4j
public class TestControllerRest {
    @Autowired
    private SimpleWorkInfoRepository simpleWorkInfoRepository;

    @RequestMapping("/index")
    public String addSimpleWork() {
        return "/index";
    }
}
