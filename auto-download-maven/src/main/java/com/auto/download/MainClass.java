package com.auto.download;

import com.auto.download.maven.MavenHandler;

public class MainClass {
    public static void main(String[] args) {
        System.out.println(ClassLoader.getSystemClassLoader().getClass());
        MavenHandler.download();
    }
}
