package com.kosmo.springapp.basic.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

//※AspectJ Weaver는 1.8.X이상으로 (AspectJ WEAVER maven로 검색)
//[현재 클래스는 어노테이션으로 AOP구현시, 사용할 Advice다]
/*
* @Aspect어노테이션]
* 1]이 클래스는 Advice역할을 하는 클래스라는 것을
* 컴파일러에게 알려주는 어노테이션
*/
@Aspect
public class AdviceUsingAnnotation {
	/*
	  * 공통 관심 사항을 위빙할 타겟클래스의
	  * 어느 지점에 주입할것인지 정의
	  * 
	  * Pointcut설정시 execution명시자 형식의 문자열 지정
	  * 
	  *  execution명시자:
	  *  Advice를 적용할 패키지,클래스 그리고 
	  *  메서드를 표현할때 사용
	  형식:execution(접근지정자패턴 리턴타입패턴 패키지이름패턴/클래스이름패턴/메서드이름패턴/(파라미터 패턴)) 
	  =>AspectJ표현식이라고 함
	  
	  접근지정자패턴:생략가능(public ,protected등)
	  *:모든 값
	  ..:0개 이상이라는 의미
	  public * spring.aop..*(..)=>
	  접근지정자가 public이고 모든 리턴타입에 대해 spring.aop패키지 
	  및 그 이하에 있는 모든 패키지의
	  모든 클래스의 메서드에 대해 그리고 인자가 0개이상인 모든 메서드를 의미함.
	  */ 
	//2]타겟 클래스의 어느 지점에 현 Advice를 삽입(위빙)
	//  할지 포인트 컷 설정
	@Pointcut("execution(public * com.kosmo.springapp..*(..))")
	//Pointcut를 지정할 공통 메소드 원형 정의
	//(단, 반환타입은 무조건 void,메소드명 임의)
	public void advice() {}
	
	@Around("advice()")
	public Object crossCuttingConcern(ProceedingJoinPoint point) throws Throwable {
		
		String coreConcernName = point.getSignature().toShortString();
		System.out.println("대상 클랙스의 핵심 관점(메소드명) : "+coreConcernName);
		//[대상 클래스의 핵심 로직(getTotal()) 실행전 수행할 공통로직]	
		long startTime = System.currentTimeMillis();
		
		Object object=point.proceed();//공통관점이 삽입된 핵심로직이 실행될때
		//[대상 클래스의 핵심 로직(getTotal()) 실행후 수행할 공통로직]
		long endTime = System.currentTimeMillis();
		System.out.println(coreConcernName+"의 총 소요시간:"+(endTime-startTime)/1000.0+"초");
		return object;
	}
}
