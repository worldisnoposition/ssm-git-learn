package com.auto.download.maven;

import lombok.Builder;

@Builder
public class CommandDomain {
    private String key;
    private String value;
    private CommandDomain commandDomain;

    public CommandDomain() {
    }

    public CommandDomain(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private CommandDomain append(String key, String value){
        this.commandDomain = new CommandDomain(key, value);
        return this.commandDomain;
    }

    private String toCommondStr(){
        return String.format("-%s=%s",key,value);
    }
}
