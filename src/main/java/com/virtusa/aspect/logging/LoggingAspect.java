package com.virtusa.aspect.logging;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.virtusa.AppConfig;

@Aspect
@Component
public class LoggingAspect {
  Log myLog = new Log("applicationLogs.txt");

  @Pointcut("within(com.virtusa.controller.*)")
  public void addressControllerMethods() {}
  
  @Pointcut("execution(* set*(..))")
  public void allSetters() {}
  
  @Before("addressControllerMethods()")
  public void addressControllerLoggingAdvice(JoinPoint joinpoint) {
    myLog.logger.info(joinpoint.toShortString() +  " has been called");
  }
  
  /**
   * logs the name of logical name of view Controller returns to InternalResourceViewResolver
   * @param returnString
   */
  
  @AfterReturning(pointcut="addressControllerMethods()", returning="returnString")
  public void addressControllerMethodsReturning(String returnString) {
    myLog.logger.info("Controller returned logical view name: " + returnString);
  }
  
  /**
   * logs information about any exception thrown from methods within com.virtusa package
   * @param joinPoint
   * @param ex
   */
  @AfterThrowing(value = "execution(* com.virtusa.*.*.*(..))", throwing = "ex")
  public void throwing(JoinPoint joinPoint, Exception ex) {

    Signature signature = joinPoint.getSignature();
    String methodName = signature.getName();
    String stuff = signature.toString();
    String arguments = Arrays.toString(joinPoint.getArgs());
    String info = "Caught exception in method: "
        + methodName + " with arguments "
        + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
        + ex.getMessage();
    myLog.logger.info(info);
  }
  
  
}