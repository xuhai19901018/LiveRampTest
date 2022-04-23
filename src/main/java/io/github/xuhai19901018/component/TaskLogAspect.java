package io.github.xuhai19901018.component;

import java.util.ArrayList;
import java.util.Map;

import org.apache.logging.log4j.core.config.Order;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;


/***
 * 任务运行日志
 * 
 * @author xuhai
 *
 */
@Aspect
@Component
@Slf4j
public class TaskLogAspect {

	@Before("execution(* io.github.xuhai19901018.model.*.*(..))")
	public void log(){
		
		
		System.out.println("123");
	}
	

//	@Pointcut(value = "execution(* io.github.xuhai19901018.model.*.*())")
//	public void taskLog() {
//	}
//
//	@Around("taskLog()")
//	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//
//		log.info("任务开始");
//
//		return joinPoint.proceed();
//	}
//
//	
//	@Before(value="taskLog()")
//	public void before(JoinPoint joinPoint) {
//
//		log.info("before");
//
//		
//	}
	
}
