package com.spider.zhiye.rest;

import com.spider.zhiye.jpa.entity.WordCloudEntity;
import com.spider.zhiye.jpa.entity.WordSegmentEntity;
import com.spider.zhiye.jpa.repository.WordCloudReposititory;
import com.spider.zhiye.vo.StatisticByJobNumbersVO;
import com.spider.zhiye.jpa.entity.StatisticEntity;
import com.spider.zhiye.jpa.repository.StatisticReposititoty;
import com.spider.zhiye.jpa.repository.ZhiyeReposititoty;
import com.spider.zhiye.service.WordSegmentationService;
import com.spider.zhiye.vo.WordCloudVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/echart")
@Slf4j
public class EchartRest {

    @Autowired
    private StatisticReposititoty statisticReposititoty;
    @Autowired
    private WordCloudReposititory wordCloudReposititory;
    @Resource
    private WordSegmentationService wordSegmentationService;

    @RequestMapping("/statistic")
    private StatisticByJobNumbersVO statistic() {
        List<StatisticEntity> statisticEntities = statisticReposititoty.selectByPkid();
        return this.buildStatisticByJoByNumbers(statisticEntities);
    }

    @RequestMapping("/wordCloud")
    private List<WordCloudVO> wordCloud() {
        List<WordCloudEntity> wordCloudEntities = wordCloudReposititory.selectWordCloud();
        return this.buildWordCloudVOs(wordCloudEntities);
    }

    private List<WordCloudVO> buildWordCloudVOs(List<WordCloudEntity> wordCloudEntities) {
        List<WordCloudVO> wordCloudVOList = new ArrayList<>();
        wordCloudEntities.forEach(w -> {
            WordCloudVO wordCloudVO = new WordCloudVO();
            BeanUtils.copyProperties(w, wordCloudVO);
            wordCloudVOList.add(wordCloudVO);
        });
        return wordCloudVOList;
    }

    @RequestMapping("/segment")
    private Object wordSegment() {
        wordSegmentationService.startJob();
        return null;
    }

    private StatisticByJobNumbersVO buildStatisticByJoByNumbers(List<StatisticEntity> statisticEntities) {
        StatisticByJobNumbersVO result = new StatisticByJobNumbersVO();
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
