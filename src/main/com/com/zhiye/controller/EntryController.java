package com.zhiye.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entry")
public class EntryController {

    @RequestMapping("/")
    public String entry(){
        return "";
    }
}
