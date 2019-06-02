package com.spider.zhiye.jpa.id;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class WordSementId implements Serializable {
    private String jobId;

    private String word;
}
