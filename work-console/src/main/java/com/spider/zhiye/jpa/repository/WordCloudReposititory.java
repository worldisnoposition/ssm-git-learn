package com.spider.zhiye.jpa.repository;

import com.spider.zhiye.jpa.entity.StatisticEntity;
import com.spider.zhiye.jpa.entity.WordCloudEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordCloudReposititory extends CrudRepository<WordCloudEntity, Long> {
    @Query(value = "select @rowno\\:=@rowno+1 as id,word as name,sum(count) as value from word_segment r,(select @rowno\\:=0) t group by word order by sum(count) desc limit 0,300", nativeQuery = true)
    List<WordCloudEntity> selectWordCloud();
}
