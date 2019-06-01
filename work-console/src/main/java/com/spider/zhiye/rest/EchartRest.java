package com.spider.zhiye.rest;

import com.alibaba.fastjson.JSONObject;
import com.spider.zhiye.dto.StatisticByJobNumbers;
import com.spider.zhiye.jpa.entity.StatisticEntity;
import com.spider.zhiye.jpa.entity.ZhiyeEntity;
import com.spider.zhiye.jpa.repository.StatisticReposititoty;
import com.spider.zhiye.jpa.repository.ZhiyeReposititoty;
import com.spider.zhiye.service.WordSegmentationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/echart")
@Slf4j
public class EchartRest {

    @Autowired
    private StatisticReposititoty statisticReposititoty;
    @Autowired
    private ZhiyeReposititoty zhiyeReposititoty;
    @Resource
    private WordSegmentationService wordSegmentationService;

    @RequestMapping("/statistic")
    private StatisticByJobNumbers statistic() {
        List<StatisticEntity> statisticEntities = statisticReposititoty.selectByPkid();
        return this.buildStatisticByJoByNumbers(statisticEntities);
    }

    private StatisticByJobNumbers buildStatisticByJoByNumbers(List<StatisticEntity> statisticEntities) {
        StatisticByJobNumbers result = new StatisticByJobNumbers();
        List<String> companyNames = new ArrayList<>();
        List<Integer> jobNumbers = new ArrayList<>();
        statisticEntities.forEach(statisticEntity -> {
            companyNames.add(statisticEntity.getCompanyName());
            jobNumbers.add(statisticEntity.getCnt());
        });
        result.setCompanyNames(companyNames);
        result.setJobNumbers(jobNumbers);
        return result;
    }

    @RequestMapping("/segment")
    private Object wordSegment() {
        wordSegmentationService.startJob();
        return null;
    }

//    @RequestMapping("/chufa")
//    public String add(@RequestBody String param) throws UnsupportedEncodingException {
//        try {
//
//            param = URLDecoder.decode(param, "utf-8");
//            log.info(param);
//            List<ZhiyeEntity> list = null;
//            if (param.endsWith("=")) {
//                param = param.substring(0, param.length() - 1);
//            }
//            list = JSONObject.parseArray(param, ZhiyeEntity.class);
//            zhiyeReposititoty.saveAll(list);
//            return "500";
//        } catch (Exception e) {
//            log.error("报错了,长度param.length{}，{}", param.length(), e);
//            return "error";
//        }
//    }
}
