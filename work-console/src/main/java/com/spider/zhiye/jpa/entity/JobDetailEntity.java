package com.spider.zhiye.jpa.entity;

import com.spider.zhiye.dto.ZhiyeDTO;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * CREATE TABLE `job_detail` (
 *    `id` int(11) NOT NULL AUTO_INCREMENT,
 *    `create_time` datetime DEFAULT NULL,
 *    `update_time` datetime DEFAULT NULL,
 *    `channel_name` varchar(255) DEFAULT NULL,
 *    `url` varchar(255) DEFAULT NULL,
 *    `job_id` varchar(255) DEFAULT NULL,
 *    `job_time` varchar(255) DEFAULT NULL,
 *    `others` varchar(255) DEFAULT NULL,
 *    `salary` varchar(255) DEFAULT NULL,
 *    `salary_high` varchar(255) DEFAULT NULL,
 *    `salary_low` varchar(255) DEFAULT NULL,
 *    `city` varchar(255) DEFAULT NULL,
 *    `work_address` varchar(255) DEFAULT NULL,
 *    `job_name` varchar(255) DEFAULT NULL,
 *    `requirement` text,
 *    `work_years` varchar(255) DEFAULT NULL,
 *    `education` varchar(255) DEFAULT NULL,
 *    `company_name` varchar(255) DEFAULT NULL,
 *    `financ_stage` varchar(255) DEFAULT NULL,
 *    `company_size` varchar(255) DEFAULT NULL,
 *    `detail` varchar(255) DEFAULT NULL,
 *    `company_id` varchar(255) DEFAULT NULL,
 *    `company_full_name` varchar(255) DEFAULT NULL,
 *    `registration_informatinon` text,
 *    `wel_fare` varchar(255) DEFAULT NULL,
 *    PRIMARY KEY (`id`)
 *  ) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8
 * CREATE TABLE `job_detail` (
 *    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 *    `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
 *    `channel_name` varchar(255) DEFAULT NULL,
 *    `url` varchar(255) DEFAULT NULL,
 *    `job_id` varchar(255) DEFAULT NULL,
 *    `job_time` varchar(255) DEFAULT NULL,
 *    `others` varchar(255) DEFAULT NULL,
 *    `salary` varchar(255) DEFAULT NULL,
 *    `salary_high` varchar(255) DEFAULT NULL,
 *    `salary_low` varchar(255) DEFAULT NULL,
 *    `city` varchar(255) DEFAULT NULL,
 *    `work_address` varchar(255) DEFAULT NULL,
 *    `job_name` varchar(255) DEFAULT NULL,
 *    `requirement` text,
 *    `work_years` varchar(255) DEFAULT NULL,
 *    `education` varchar(255) DEFAULT NULL,
 *    `company_name` varchar(255) DEFAULT NULL,
 *    `financ_stage` varchar(255) DEFAULT NULL,
 *    `company_size` varchar(255) DEFAULT NULL,
 *    `detail` varchar(255) DEFAULT NULL,
 *    `company_id` varchar(255) DEFAULT NULL,
 *    `company_full_name` varchar(255) DEFAULT NULL,
 *    `registration_informatinon` text,
 *    `wel_fare` varchar(255) DEFAULT NULL,
 *    PRIMARY KEY (`job_id`)
 *  ) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8
 */
@Data
@Table(name = "job_detail")
@Entity
public class JobDetailEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
    @LastModifiedDate
    private Timestamp createTime;
    @LastModifiedDate
    private Timestamp updateTime;
    private String channelName;
    private String url;
    @Id
    private String jobId;
    private String jobTime;
    private String others;
    private String salary;
    private String salaryHigh;
    private String salaryLow;
    private String city;
    private String workAddress;
    private String jobName;
    private String requirement;
    private String workYears;
    private String education;
    private String companyName;
    private String financStage;
    private String companySize;
    private String detail;
    private String companyId;
    private String companyFullName;
    private String registrationInformatinon;
    private String welFare;
}
