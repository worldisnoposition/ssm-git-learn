package com.learn.first;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/address")
public class FirstController {
    @RequestMapping(value = "/hello" , method = RequestMethod.GET)
    public String html() {
        System.out.println("只有这种尝试成功了");
        return "index";
    }
}

