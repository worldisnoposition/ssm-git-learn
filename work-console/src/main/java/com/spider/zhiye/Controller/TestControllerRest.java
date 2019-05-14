package com.spider.zhiye.Controller;

import com.spider.zhiye.jpa.repository.SimpleWorkInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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
