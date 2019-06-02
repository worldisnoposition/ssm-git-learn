package com.spider.zhiye.service;

import com.spider.zhiye.jpa.entity.JobDetailEntity;
import com.spider.zhiye.jpa.entity.WordSegmentEntity;
import com.spider.zhiye.jpa.entity.ZhiyeEntity;
import com.spider.zhiye.jpa.repository.WordSegmentRepository;
import com.spider.zhiye.jpa.repository.ZhiyeReposititoty;
import com.word.segmentation.WordSegmentationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class WordSegmentationService {
    @Autowired
    private WordSegmentationUtil wordSegmentationUtil;
    @Autowired
    private WordSegmentRepository wordSegmentRepository;
    @Autowired
    private ZhiyeReposititoty zhiyeReposititoty;
    private final static String SEPARATOR = "\\s";

    @PostConstruct
    public void init() {
        System.out.printf("word segmentation is init");
    }

    public void startJob() {
        //todo 查詢
//        for(int i=0;;i+=50) {
//            Pageable pageable = new PageRequest(0, 1, Sort.Direction.ASC, "id");
//            List<ZhiyeEntity> zhiyeList = zhiyeReposititoty.findAll(pageable).getContent();
//            //todo 分詞
//            List<WordSegmentEntity> wordSegmentEntities = new ArrayList<>();
//            zhiyeList.forEach(zhiyeEntity -> {
//                wordSegmentEntities.addAll(this.buildWordSegment(zhiyeEntity));
//            });
//            //todo 保存
//            if(wordSegmentEntities != null && wordSegmentEntities.size() != 0) {
//                wordSegmentRepository.saveAll(wordSegmentEntities);
//            }
//        }
    }

    public List<WordSegmentEntity> buildWordSegment(JobDetailEntity jobDetailEntity) {
        List<WordSegmentEntity> result = new ArrayList<>();
        this.split(jobDetailEntity.getRequirement()).stream().filter(word -> !StringUtils.isEmpty(word)).forEach(orginWord -> {
                    wordSegmentationUtil.segment(orginWord).forEach(word -> {
                        WordSegmentEntity wordSegmentEntity = new WordSegmentEntity();
                        wordSegmentEntity.setChannelName(jobDetailEntity.getChannelName());
                        wordSegmentEntity.setCompanyName(jobDetailEntity.getCompanyName());
                        wordSegmentEntity.setJobId(jobDetailEntity.getJobId());
                        wordSegmentEntity.setCount(1);
                        wordSegmentEntity.setWord(word);
                        result.add(wordSegmentEntity);
                    });
                }
        );
//        wordSegmentEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
//        wordSegmentEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return result;
    }

    private List<String> split(String target) {
        List<String> result = new ArrayList<>();
        Collections.addAll(result, target.split(SEPARATOR));
        return result;
    }
}
