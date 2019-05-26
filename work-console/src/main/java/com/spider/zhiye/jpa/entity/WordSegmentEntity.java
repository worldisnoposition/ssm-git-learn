package com.spider.zhiye.jpa.entity;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "word_segment", schema = "zhiye", catalog = "")
public class WordSegmentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String word;
    private String channelName;
    private String companyName;
    private Integer count;
    @LastModifiedDate
    private Timestamp createTime;
    @LastModifiedDate
    private Timestamp updateTime;
}
