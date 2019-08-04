package com.auto.download.maven;

import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.Map;

public class MavenCommand {
    @Setter
    @Getter
    private String root;
    private Map<String, String> commandMap = new HashMap();
    private final static String D = "-D";
    private final static String EQUAL = "=";
    private final static String SPACE = " ";

    public void appendCommand(String key, String val) {
        commandMap.put(key, val);
    }

    public void appendCommand(MavenBaseCommand base){
        commandMap.putAll(base.getCommandMap());
    }

    public String commandStr() {
        StringBuilder command = new StringBuilder();
        for (Map.Entry entry : commandMap.entrySet()) {
            command.append(D)
                    .append(entry.getKey())
                    .append(EQUAL)
                    .append(entry.getValue())
                    .append(SPACE);
        }
        return command.toString();
    }

    public String fullCommandStr() {
        return this.root + SPACE + commandStr();
    }
}

