package com.dynamic.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String sourceType = CustomContextHolder.getCustomerType();
        System.out.println("DataSourceType: "+sourceType);

        return sourceType;
    }
}