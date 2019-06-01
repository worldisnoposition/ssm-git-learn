package com.spider.zhiye.rest;

import com.alibaba.fastjson.JSONObject;
import com.spider.zhiye.dto.ZhiyeDTO;
import com.spider.zhiye.jpa.entity.JobDetailEntity;
import com.spider.zhiye.jpa.entity.ZhiyeEntity;
import com.spider.zhiye.jpa.repository.JobDetailRepository;
import com.spider.zhiye.jpa.repository.StatisticReposititoty;
import com.spider.zhiye.jpa.repository.ZhiyeReposititoty;
import com.spider.zhiye.service.WordSegmentationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/zhiye")
@Slf4j
public class ZhiyeRest {

    @Autowired
    private ZhiyeReposititoty zhiyeReposititoty;
    @Resource
    private WordSegmentationService wordSegmentationService;
    @Autowired
    private StatisticReposititoty statisticReposititoty;
    @Autowired
    private JobDetailRepository jobDetailRepository;

    @RequestMapping("/index")
//    @Transactional
    public Object doneTransactional() {
        return "/index.";
    }

    @RequestMapping("/chufa")
    public String add(@RequestBody String param) throws UnsupportedEncodingException {
        try {

            param = URLDecoder.decode(param, "utf-8");
            log.info(param);
            List<ZhiyeEntity> list = null;
            if (param.endsWith("=")) {
                param = param.substring(0, param.length() - 1);
            }
            list = JSONObject.parseArray(param, ZhiyeEntity.class);
            zhiyeReposititoty.saveAll(list);
            return "500";
        } catch (Exception e) {
            log.error("报错了,长度param.length{}，{}", param.length(), e);
            return "error";
        }
    }

    @RequestMapping("/saveSingleData")
    public String saveSingleData(@RequestBody(required = false) String param,
                                 @RequestParam(required = false) String param2) throws UnsupportedEncodingException {
        try {
            param = URLDecoder.decode(param, "utf-8");
            log.info(param);
            if (param.endsWith("=")) {
                param = param.substring(0, param.length() - 1);
            }
            ZhiyeDTO zhiyeDTO = JSONObject.parseObject(param, ZhiyeDTO.class);
//            zhiyeReposititoty.saveAll(list);
            JobDetailEntity jobDetailEntity = new JobDetailEntity();
            BeanUtils.copyProperties(zhiyeDTO, jobDetailEntity);
            BeanUtils.copyProperties(zhiyeDTO.getCompanyDetail(), jobDetailEntity);
            BeanUtils.copyProperties(zhiyeDTO.getJobDetail(), jobDetailEntity);
            jobDetailRepository.saveAndFlush(jobDetailEntity);
            return (int)(Math.random() * 5000) + "";
        } catch (Exception e) {
            log.error("报错了,长度param.length{}，{}", param.length(), e);
            return (int)(Math.random() * 5000) + "";
        }
    }

    @RequestMapping("query")
    private Object queryAll() {
        return zhiyeReposititoty.findAll();
    }

}