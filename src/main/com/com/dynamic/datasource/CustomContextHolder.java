package com.dynamic.datasource;

public class CustomContextHolder {
    //todo 切面方法中把threadLocal中设置上想要用的dataSource即可
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static final String DATA_SOURCE_FROM = "fromDataSource";//对应动态数据源配置中的key
    public static final String DATA_SOURCE_TO = "toDataSource";

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return DATA_SOURCE_TO;// contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }
}
