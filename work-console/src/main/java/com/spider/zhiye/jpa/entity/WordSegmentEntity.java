package com.spider.zhiye.jpa.entity;

import com.spider.zhiye.jpa.id.WordSementId;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "word_segment", schema = "zhiye", catalog = "")
@IdClass(WordSementId.class)
public class WordSegmentEntity {
//    @EmbeddedId
    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String jobId;
    @Id
    private String word;
    private String channelName;
    private String companyName;
    private Integer count;
    @LastModifiedDate
    private Timestamp createTime;
    @LastModifiedDate
    private Timestamp updateTime;
}
