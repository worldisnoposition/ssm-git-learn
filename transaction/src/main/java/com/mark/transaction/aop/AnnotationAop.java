package com.mark.transaction.aop;

import com.mark.transaction.annotation.MyTransactionArgs;
import com.mark.transaction.annotation.MyTransactionMethod;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Service
@Slf4j
@Aspect
public class AnnotationAop {

    @Pointcut(value = "@annotation(com.mark.transaction.annotation.MyTransactionMethod)")
    private void pointCut() {
        log.info("into pointCut");
    }

    @Around(value = "pointCut() && args(enhance) && @annotation(myTransactionMethod))")
    private Object doAroundPointCut(ProceedingJoinPoint proceedingJoinPoint, String enhance, MyTransactionMethod myTransactionMethod) throws Throwable {
        log.info("into doAroundPointCut aop" + enhance);
        log.info(proceedingJoinPoint.toLongString());
        Object proceed = proceedingJoinPoint.proceed();
        proceedingJoinPoint.getSignature().getDeclaringTypeName();
        log.info("类型是否是MethodInvocationProceedingJoinPoint,{}", proceedingJoinPoint instanceof MethodInvocationProceedingJoinPoint);
        MethodInvocationProceedingJoinPoint point = (MethodInvocationProceedingJoinPoint) proceedingJoinPoint;
//        point.get
        log.info("myTransactionMethod,{}", myTransactionMethod.enhanceMethod());
//        log.info("myTransactionArgs,{}", myTransactionArgs.enhanceArgs());
        log.info(String.valueOf(proceed));
        MethodSignature signature = (MethodSignature) point.getSignature();
        Object[] params = point.getArgs();
        Method method = signature.getMethod();

        //参数注解，1维是参数，2维是注解
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            Object param = params[i];
            Annotation[] paramAnn = annotations[i];
            //参数为空，直接下一个参数
            if (param == null || paramAnn.length == 0) {
                continue;
            }
            for (Annotation annotation : paramAnn) {
                //这里判断当前注解是否为Test.class
                if (annotation.annotationType().equals(MyTransactionArgs.class)) {
                    //校验该参数，验证一次退出该注解
                    //TODO 校验参数
                    if (annotation instanceof MyTransactionArgs) {
                        log.info("annotation enhanceArgs:{}", ((MyTransactionArgs) annotation).enhanceArgs());
                    }
                    break;
                }
            }
        }
        return proceed;
    }

//    @Around(value = "@args(myTransactionArgs)")
//    private Object doAroundAnnotationArgs(ProceedingJoinPoint proceedingJoinPoint,  MyTransactionArgs myTransactionArgs) throws Throwable {
//        log.info("into doAroundAnnotationArgs aop :{}",myTransactionArgs);
//        log.info(proceedingJoinPoint.toLongString());
//        Object proceed = proceedingJoinPoint.proceed();
//        log.info(String.valueOf(proceed));
//        return proceed;
//    }

//    @Around(value = "@annotation(com.mark.transaction.annotation.MyTransactionMethod) && args(enhance)")
//    private Object doAroundAnnotation(ProceedingJoinPoint proceedingJoinPoint, String enhance) throws Throwable {
//        log.info("into doAroundAnnotation aop" + enhance);
//        log.info(proceedingJoinPoint.toLongString());
//        Object proceed = proceedingJoinPoint.proceed();
//        log.info(String.valueOf(proceed));
//        return proceed;
//    }
}
