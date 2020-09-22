package com.myboard.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopAdvice {
	
	//board 컨트롤러 매개변수 출력
	@Before("execution(* com.myboard.controller.*.*(..))")
	public void startLogController(JoinPoint jp) { //어드바이스(무엇을)
		System.out.println(jp.getSignature().toLongString() +":매개변수" + Arrays.toString(jp.getArgs()) );
	}
	
	//dao 매개변수 출력
	@Before("execution(* com.myboard.dao.*.*(..))")
	public void startLogDAO(JoinPoint jp) { //어드바이스(무엇을)
		System.out.println("--매개변수:" + jp.getSignature().toLongString());
		System.out.println("*** " + Arrays.toString(jp.getArgs()));
	}
	
	//메소드 반환값 출력
	//pointcut : 적용할 대상
	//적용대상 정상수행후
	@AfterReturning(pointcut="execution(* com.myboard.service.*.*(..))", returning = "rObj")
	public void afterLog(JoinPoint jp, Object rObj) {
		if (rObj != null) {
			System.out.println("--리턴값:" + jp.getSignature().toLongString());
			System.out.println("*** " + rObj.toString());
		}
	}
	
	
}
