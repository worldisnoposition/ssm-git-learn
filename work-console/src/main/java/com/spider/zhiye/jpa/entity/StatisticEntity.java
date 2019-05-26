package com.spider.zhiye.jpa.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class StatisticEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @JSONField(name = "avgMoneyHigh")
    private String avgMoneyHigh;
    private String avgMoneyLow;
    private Integer cnt;
    private String companyName;
    private String xueli;
    private String thirdName;
}
