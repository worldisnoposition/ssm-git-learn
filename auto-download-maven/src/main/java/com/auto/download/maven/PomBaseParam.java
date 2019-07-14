package com.auto.download.maven;

import lombok.Data;

@Data
public class PomBaseParam {
    private String groupId;

    public PomBaseParam(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    private String artifactId;
    private String version;
    private final static String commonad = " -DgroupId=%s -DartifactId=%s -Dversion=%s";
    public String getCommondBaseInfo(){
        return String.format(commonad, this.groupId, this.artifactId, this.version);
    }
}
