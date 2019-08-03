package com.auto.download.maven;

import lombok.Getter;

public enum BaseConfig {
    TAISHIJI_ENV("D:\\coding\\java\\ssm-git-learn\\auto-download-maven\\pom.xml"
            , "D:\\Program File\\apache-maven-3.5.0"
            , "D:\\data\\mavenWarehouDownload"
            , "http://repo1.maven.org/maven2/"),
    BIJIBEN_ENV("H:\\ssm-git-learn\\auto-download-maven\\pom.xml"
            , "F:\\apache\\apache-maven-3.5.0"
            , "H:\\downloadMavenAutoRepository"
            , "http://repo1.maven.org/maven2/");

    BaseConfig(String pomFilePath, String mavenHomePath, String repositoryDirectory, String remoteRepositoryUrl) {
        this.pomFilePath = pomFilePath;
        this.mavenHomePath = mavenHomePath;
        this.repositoryDirectory = repositoryDirectory;
        this.remoteRepositoryUrl = remoteRepositoryUrl;
    }

    @Getter
    private String pomFilePath;
    @Getter
    private String mavenHomePath;
    @Getter
    private String repositoryDirectory;
    @Getter
    private String remoteRepositoryUrl;
}
