package com.spider.zhiye.rest;

import com.alibaba.fastjson.JSONObject;
import com.spider.zhiye.jpa.entity.StatisticEntity;
import com.spider.zhiye.jpa.repository.StatisticReposititoty;
import com.spider.zhiye.jpa.repository.ZhiyeReposititoty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.xml.ws.Action;
import java.util.List;

@RestController
@RequestMapping("/echart")
@Slf4j
public class EchartRest {

    @Autowired
    private StatisticReposititoty statisticReposititoty;

    @RequestMapping("statistic")
    private Object statistic() {
        List<StatisticEntity> result = statisticReposititoty.selectByPkid();
        System.out.println(JSONObject.toJSON(result));
        return null;
    }
}
