package com.spider.zhiye.vo;

import com.spider.zhiye.dto.EchartBase;
import lombok.Data;

import java.util.List;

@Data
public class StatisticByJobNumbersVO extends EchartBase {
    private List<String> companyNames;
    private List<Integer> jobNumbers;
 }
