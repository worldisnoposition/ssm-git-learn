package com.create.result;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.Perf;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
public class ProjectRespDTO {
    private Integer code;
    private String message;
    @Level(level = 1,type = ProjectDto.class)
    private LinkedList<ProjectDto> projectDtoList;
    private LinkedList<VenueDto> venueDtos;
    @Data
    public class ProjectDto{
        private String projectId;
        private String projectName;
        @Level(level = 2,type = PerformDto.class)
        private LinkedList<PerformDto> performDtos;
    }
    @Data
    public class PerformDto{
        private String performId;
        private String performName;
        private String venueId;
        private Date startTime;
        @Level(level = 3,type = PriceDto.class)
        private LinkedList<PriceDto> priceDtos;
    }
    @Data
    public class PriceDto{
        private String priceId;
        private String priceName;
        private BigDecimal price;
    }
    @Data
    public class VenueDto{
        private String venueId;
        private String venueName;
    }
}
