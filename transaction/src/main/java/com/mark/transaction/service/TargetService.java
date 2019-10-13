package com.mark.transaction.service;

import com.mark.transaction.annotation.MyTransactionArgs;
import com.mark.transaction.annotation.MyTransactionMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TargetService {

    @MyTransactionMethod(enhanceMethod="enhance")
    public String targetMethod(@MyTransactionArgs(enhanceArgs = "enhanceArgs") String args) {
        log.info("targetMethod args:" + args);
        return args;
    }

    private String enhance(){
        log.info("into enhance");
        return "enter enhance";
    }
}
