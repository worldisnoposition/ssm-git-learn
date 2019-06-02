package com.spider.zhiye.vo;

import lombok.Data;

import java.util.List;

@Data
public class WordCloudVO {
    private String name;
    private String value;
    private TextStyle textStyle;
    @Data
    private class TextStyle{
        private List<Normal> normal;
    }
    @Data
    private class Normal{
        private String color;
    }

}
