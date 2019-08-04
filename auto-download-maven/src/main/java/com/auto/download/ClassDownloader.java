package com.auto.download;

public interface ClassDownloader {
    /**
     * down loadClass to default path
     */
    void downloadClass();

    /**
     * downloadJarFile class and save it to saveClassPath
     * @param saveClassPath
     */
    void downloadClass(String saveClassPath);
}
