package com.auto.web;

import com.auto.spring.SpringBeanLoadHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/switch")
public class SwitchRestController {
    @Autowired
    private SpringBeanLoadHandler springBeanLoadHandler;

    @RequestMapping(path="/load",method = RequestMethod.POST)
    public String load(String param){
        springBeanLoadHandler.load(param);
        return "success";
    }
}
