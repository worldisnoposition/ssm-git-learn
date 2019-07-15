package com.auto.download.maven;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class PomBaseParam extends BaseCommand {
    private String groupId;
    private Map<String, String> pomMap = new HashMap<String, String>();
    private final static String GROUP_ID = "groupId";
    private final static String ARTIFACT_ID = "artifactId";
    private final static String VERSION = "version";

    public PomBaseParam(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        pomMap.put(GROUP_ID, groupId);
        pomMap.put(ARTIFACT_ID, artifactId);
        pomMap.put(VERSION, version);
    }

    private String artifactId;
    private String version;
    private final static String commonad = " -DgroupId=%s -DartifactId=%s -Dversion=%s";

    public String getCommondBaseInfo() {
        return String.format(commonad, this.groupId, this.artifactId, this.version);
    }

    Map getCommandMap() {
        return pomMap;
    }
}
