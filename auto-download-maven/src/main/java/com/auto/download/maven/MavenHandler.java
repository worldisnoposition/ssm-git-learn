package com.auto.download.maven;


import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class MavenHandler {
    private final static BaseConfig baseConfig = BaseConfig.TAISHIJI_ENV;

    public static void download() {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File(baseConfig.getPomFilePath()));
        request.setLocalRepositoryDirectory(new File(baseConfig.getRepositoryDirectory()));
        PomBaseParam pomBaseParam = new PomBaseParam("org.projectlombok", "lombok", "1.16.10");
        Command command = new Command();
        command.setRoot(CommandEnum.DOWNDLOAD_GET.getCommand());
        command.appendCommand(pomBaseParam);
        String singletonCommand = String.format(command.fullCommandStr(), baseConfig.getRemoteRepositoryUrl(), pomBaseParam.getCommondBaseInfo());
        System.out.println(singletonCommand);
        request.setGoals(Collections.singletonList(singletonCommand));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(baseConfig.getMavenHomePath()));

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