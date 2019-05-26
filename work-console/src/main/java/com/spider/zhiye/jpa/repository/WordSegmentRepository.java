package com.spider.zhiye.jpa.repository;

import com.spider.zhiye.jpa.entity.StatisticEntity;
import com.spider.zhiye.jpa.entity.WordSegmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordSegmentRepository extends CrudRepository<WordSegmentEntity, Long> {
}
