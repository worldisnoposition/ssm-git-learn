package com.word.segmentation;

//import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WordSegmentationUtil {
    public static void main(String[] args) {
        WordSegmentationUtil wordSegmentationUtil = new WordSegmentationUtil();
        List<String> words = wordSegmentationUtil.segment("杨尚川是APDPlat应用级产品开发平台的作者");
        System.out.println(JSONObject.toJSON(words));
//        System.out.println("====================");
//        words = WordSegmenter.seg("杨尚川是APDPlat应用级产品开发平台的作者");
//        System.out.println(words);
    }

    public List<String> segment(String text) {
        if(text != null && !"".equals(text)) {
            List<Word> words = WordSegmenter.seg(text);
            final List<String> result = new ArrayList<String>();
            words.forEach(w -> result.add(w.getText()));
            return result;
        }
        return new ArrayList<>();
    }
}
