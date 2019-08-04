package com.auto.download.maven;

import com.auto.download.ClassDownloader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class DownloadClassMavenImp implements ClassDownloader {
    @Autowired
    private MavenHandler mavenHandler;
    private String defaultClassPath;

    @PostConstruct
    public void init() {
        defaultClassPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        log.info("defaultClassPath is inited,defaultClassPath = {}", defaultClassPath);
    }

    public void downloadClass() {
        this.downloadClass(defaultClassPath);
    }

    public void downloadClass(String saveClassPath) {
        mavenHandler.downloadJarFile(saveClassPath);
    }
}
