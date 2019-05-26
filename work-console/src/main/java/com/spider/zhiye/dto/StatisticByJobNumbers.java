package com.spider.zhiye.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticByJobNumbers extends EchartBase{
    private List<String> companyNames;
    private List<Integer> jobNumbers;
 }
