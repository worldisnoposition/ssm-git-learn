package com.auto.download.maven;


import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class DownloadMavenJar {


    public static void main(String[] args) {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("D:\\coding\\java\\ssm-git-learn\\auto-download-maven\\pom.xml"));
        request.setGoals(Collections.singletonList("compile"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File("D:\\程序包\\apache-maven-3.5.0"));

        invoker.setLogger(new PrintStreamLogger(System.out, InvokerLogger.INFO) {

        });
        invoker.setOutputHandler(new InvocationOutputHandler() {
            public void consumeLine(String s) throws IOException {
            }
        });


//        try {
//            invoker.execute(request);
//        } catch (MavenInvocationException e) {
//            e.printStackTrace();
//        }


        try {
            if (invoker.execute(request).getExitCode() == 0) {
                System.out.println("success");
            } else {
                System.err.println("error");
            }
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }

}