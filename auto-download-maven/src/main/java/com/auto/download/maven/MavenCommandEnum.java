package com.auto.download.maven;

import lombok.Getter;
import lombok.Setter;

public enum MavenCommandEnum {

    DOWNDLOAD_GET("downloadJarFile:get", "下载"),
    DEPENDENCY_GET("dependency:get", "dependency"),
    PACKAGE("package", "打包");
    @Setter
    @Getter
    private String command;
    @Setter
    @Getter
    private String desc;
    @Setter
    @Getter
    private String formatCommand;

    MavenCommandEnum(String command, String desc) {
        this.command = command;
        this.desc = desc;
    }

}
