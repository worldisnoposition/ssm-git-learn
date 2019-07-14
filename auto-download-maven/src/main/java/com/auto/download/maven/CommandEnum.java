package com.auto.download.maven;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public enum CommandEnum {

    DOWNDLOAD_GET("download:get", "下载");
    @Setter
    @Getter
    private String command;
    @Setter
    @Getter
    private String desc;
    @Setter
    @Getter
    private String formatCommand;
    CommandEnum(String command, String desc){
        this.command = command;
        this.desc = desc;
    }

}
