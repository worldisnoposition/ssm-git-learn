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
public class ProjectRespDTO implements Target {
    private Integer code;
    private String message;
    private LinkedList<ProjectDto> projectDtoList;
    private LinkedList<VenueDto> venueDtos;

    @Data
    @Level(key = "projectId",level = 1)
    public class ProjectDto{
        private String projectId;
        private String projectName;
        private LinkedList<PerformDto> performDtos;
    }
    @Data
    @Level(key = "performId",level = 2)
    public class PerformDto{
        private String performId;
        private String performName;
        private String venueId;
        private Date startTime;
        private LinkedList<PriceDto> priceDtos;
    }
    @Data
    @Level(key = "priceId",level = 3)
    public class PriceDto{
        private String priceId;
        private String priceName;
        private BigDecimal price;
    }
    @Data
    @Level(key = "venueId",level = 1)
    public class VenueDto{
        private String venueId;
        private String venueName;
    }
}
