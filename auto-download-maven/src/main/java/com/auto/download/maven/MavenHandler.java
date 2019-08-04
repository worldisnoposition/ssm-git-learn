package com.auto.download.maven;


import org.apache.maven.shared.invoker.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@Component
public class MavenHandler {
    private final static BaseConfig baseConfig = BaseConfig.TAISHIJI_ENV;

    /**
     * 下载jar包并保存到saveClassPath路径下
     * @param saveClassPath
     */
    public void downloadJarFile(String saveClassPath) {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File(baseConfig.getPomFilePath()));
        request.setLocalRepositoryDirectory(new File(baseConfig.getRepositoryDirectory()));
        PomBaseParam pomBaseParam = new PomBaseParam("org.projectlombok", "lombok", "1.16.10");
        MavenCommand mavenCommand = new MavenCommand();
        mavenCommand.setRoot(MavenCommandEnum.DOWNDLOAD_GET.getCommand());
        mavenCommand.appendCommand(pomBaseParam);
        String singletonCommand = String.format(mavenCommand.fullCommandStr(), baseConfig.getRemoteRepositoryUrl(), pomBaseParam.getCommondBaseInfo());
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