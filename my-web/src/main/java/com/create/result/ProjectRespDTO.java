package com.create.result;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.Perf;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@Component
public class ProjectRespDTO implements Target {
    private Integer code;
    private String message;
    private LinkedList<ProjectDto> projectDtoList;
    private LinkedList<VenueDto> venueDtos;

    public ProjectRespDTO() {
        System.out.println("外部类");
    }

    @Data
    @Level(key = "projectId", level = 1)
    @Component
    public class ProjectDto {
        @PostConstruct
        public void construct(){
            System.out.println("内部类的init方法");
        }
        @Autowired//内部类构造方法要传进来一个它的外部类实例
        public ProjectDto(ProjectRespDTO projectRespDTO) {
            System.out.println("内部类也被spring扫描到了");
        }

        //        public ProjectDto(){
//            System.out.println("内部类也被spring扫描到了");
//        }
        private String projectId;
        private String projectName;
        private LinkedList<PerformDto> performDtos;
    }

    @Data
    @Level(key = "performId", level = 2)
    public class PerformDto {
        private String performId;
        private String performName;
        private String venueId;
        private Date startTime;
        private LinkedList<PriceDto> priceDtos;
    }

    @Data
    @Level(key = "priceId", level = 3)
    public class PriceDto {
        private String priceId;
        private String priceName;
        private BigDecimal price;
    }

    @Data
    @Level(key = "venueId", level = 1)
    public class VenueDto {
        private String venueId;
        private String venueName;
    }
}
