package com.auto.download.maven;


import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class MavenHandler {

    private final static String pomFilePath = "H:\\ssm-git-learn\\auto-download-maven\\pom.xml";
    private final static String mavenHomePath = "F:\\apache\\apache-maven-3.5.0";
    private final static String repositoryDirectory = "H:\\downloadMavenAutoRepository";
    private final static String remoteRepositoryUrl = "http://repo1.maven.org/maven2/";
    public static void download() {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File(pomFilePath));
        request.setLocalRepositoryDirectory(new File(repositoryDirectory));
        PomBaseParam pomBaseParam = new PomBaseParam("junit","junit","4.8.2");
        Command command = new Command();
        command.setRoot(CommandEnum.DOWNDLOAD_GET.getCommand());
        command.appendCommand(pomBaseParam);
        String singletonCommand = String.format(command.fullCommandStr(), remoteRepositoryUrl, pomBaseParam.getCommondBaseInfo());
        System.out.println(singletonCommand);
        request.setGoals(Collections.singletonList(singletonCommand));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(mavenHomePath));

        invoker.setLogger(new PrintStreamLogger(System.out, InvokerLogger.INFO) {

        });
        invoker.setOutputHandler(new InvocationOutputHandler() {
            public void consumeLine(String s) throws IOException {
                System.out.println(s);
            }
        });

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