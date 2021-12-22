package kr.or.ddit.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class LoggingAdvice {
	
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {}
	
	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();
		Object[] args = joinPoint.getArgs();
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		String targetName = target.getClass().getSimpleName();
		log.info("{}.{}({}) 이 실행되기 전에 weaving", targetName, methodName, Arrays.toString(args));
		long start = System.currentTimeMillis();
		Object retValue = joinPoint.proceed(args);
		long end = System.currentTimeMillis();
		log.info("{}.{} 호출 후 소요시간 : {}ms, 반환값 : {}", targetName, methodName, (end-start), retValue);
		return retValue;
	}
}
