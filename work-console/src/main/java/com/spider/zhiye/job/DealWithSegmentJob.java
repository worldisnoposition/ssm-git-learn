package com.spider.zhiye.job;

import com.spider.zhiye.jpa.entity.JobDetailEntity;
import com.spider.zhiye.jpa.entity.WordSegmentEntity;
import com.spider.zhiye.jpa.repository.JobDetailRepository;
import com.spider.zhiye.jpa.repository.WordSegmentRepository;
import com.spider.zhiye.service.WordSegmentationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class DealWithSegmentJob {
    @Autowired
    private JobDetailRepository jobDetailRepository;
    @Autowired
    private WordSegmentRepository wordSegmentRepository;
    @Autowired
    private WordSegmentationService wordSegmentationService;
    private final static String PROPERTY_NAME_JOB_ID = "jobId";

//    @PostConstruct
    private void init() {
        for (int i = 0; ; i++) {
            try {
                Pageable pageable = new PageRequest(i, 1, Sort.Direction.ASC, PROPERTY_NAME_JOB_ID);
                List<JobDetailEntity> jobDetailEntities = jobDetailRepository.findAll(pageable).getContent();
                if(jobDetailEntities ==null || jobDetailEntities.size()==0){
                    break;
                }
                //todo 分詞
                List<WordSegmentEntity> wordSegmentEntities = new ArrayList<>();
                jobDetailEntities.forEach(jobDetailEntity -> {
                    wordSegmentEntities.addAll(wordSegmentationService.buildWordSegment(jobDetailEntity));
                });
                //todo 保存

                wordSegmentEntities.sort(new Comparator<WordSegmentEntity>() {
                    @Override
                    public int compare(WordSegmentEntity o1, WordSegmentEntity o2) {
                        return (o1.getJobId() + o1.getWord()).hashCode() - (o2.getJobId() + o2.getWord()).hashCode();
                    }
                });
                for (int j = 1; j < wordSegmentEntities.size(); j++) {
                    WordSegmentEntity w1 = wordSegmentEntities.get(j);
                    WordSegmentEntity w2 = wordSegmentEntities.get(j - 1);
                    if (w1.getJobId().equals(w2.getJobId()) && w1.getWord().equals(w2.getWord())) {
                        w2.setCount(w1.getCount() + w2.getCount());
                        wordSegmentEntities.remove(w1);
                    }
                }

                if (wordSegmentEntities != null && wordSegmentEntities.size() != 0) {
                    wordSegmentRepository.saveAll(wordSegmentEntities);
                }
            } catch (Exception error) {
                log.error("异常：{}", error);
            }
        }
//        init();
//        jobDetailRepository.findAll();
    }

}
