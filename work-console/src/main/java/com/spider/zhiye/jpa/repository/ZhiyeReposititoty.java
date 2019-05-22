package com.spider.zhiye.jpa.repository;

import com.spider.zhiye.jpa.entity.OrderEntity;
import com.spider.zhiye.jpa.entity.StatisticEntity;
import com.spider.zhiye.jpa.entity.ZhiyeEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ZhiyeReposititoty extends CrudRepository<ZhiyeEntity, Long> {

}