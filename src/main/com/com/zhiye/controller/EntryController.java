package com.zhiye.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhiye.service.ZhiyeService;
import com.zhiye.vo.ZhiyeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/entry")
public class EntryController {
    @Autowired
    private ZhiyeService zhiyeService;

    @RequestMapping(name="/a",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public String entry(@RequestBody String param){
//        param = URLDecoder.decode(param);
//        param = new String(param,)
        List<ZhiyeVo> list = JSONObject.parseArray(param,ZhiyeVo.class);
        zhiyeService.saveZhiye(list);
        return "{}";
    }
}
