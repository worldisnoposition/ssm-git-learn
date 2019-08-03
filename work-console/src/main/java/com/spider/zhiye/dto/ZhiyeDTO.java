package com.spider.zhiye.dto;

import lombok.Data;

@Data
public class ZhiyeDTO {

    private String channelName;
    private String url;
    private String jobId;
    private String jobTime;
    private String others;
    private JobDetailDto jobDetail;
    private CompanyDetailDto companyDetail;

    @Data
    public class JobDetailDto{
        private String salary;
        private String salaryHigh;
        private String salaryLow;
        private String city;
        private String workAddress;
        private String jobName;
        private String requirement;
        private String workYears;
        private String education;
    }

    @Data
    public class CompanyDetailDto{
        private String companyName;
        private String financStage;
        private String companySize;
        private String detail;
        private String companyId;
        private String companyFullName;
        private String registrationInformatinon;
        private String welFare;
    }
}
