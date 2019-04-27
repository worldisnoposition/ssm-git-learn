package com.create.result;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PriceEntity {
    private String projectId;
    private String projectName;
    private String performId;
    private String performName;
    private Date startTime;
    private String priceId;
    private String priceName;
    private BigDecimal price;
    private String venueId;
    private String venueName;
}
