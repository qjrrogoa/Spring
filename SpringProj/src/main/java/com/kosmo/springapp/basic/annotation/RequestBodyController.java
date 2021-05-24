package com.kosmo.springapp.basic.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller  // 주로 페이지를 응답할때
//public class RequestBodyController {
//
//	//@RequestBody : JSON형식 데이타 받을 때 사용 .스프링 부트는 내장되어 있다(Jackson라이브러리)
//	//               스프링 메이븐(레거시)는 내장되어 있지 않다
//	//Jackson라이브러리 : 자바 객체(DTO계열)를 JSON형식(자스 객체)으로
//	//                  JSON형식(자스 객체)을 자바객체로 변환시켜주는 라이브러리
//	
//	//아래는 데이타를 JSON으로 받고 받은 데이타를 커맨드객체로 변환.다시 커맨드객체 반환(JSON으로 변환되서 반환)
//	@RequestMapping(value="/Annotation/RequestBody.do")
//	@ResponseBody
//	public LoginCommand exec(@RequestBody LoginCommand cmd) {
//		System.out.println("아이디:"+cmd.getUser());
//		System.out.println("비번:"+cmd.getPass());
//		return cmd;
//	}
//}
import org.springframework.web.bind.annotation.RestController;

@RestController  // @Controller + @ResponseBody와 같다.주로 페이지가 아닌 데이타를 응답할때
public class RequestBodyController {

	//@RequestBody : JSON형식 데이타 받을 때 사용 .스프링 부트는 내장되어 있다(Jackson라이브러리)
	//               스프링 메이븐(레거시)는 내장되어 있지 않다
	//Jackson라이브러리 : 자바 객체(DTO계열)를 JSON형식(자스 객체)으로
	//                  JSON형식(자스 객체)을 자바객체로 변환시켜주는 라이브러리
	
	//아래는 데이타를 JSON으로 받고 받은 데이타를 커맨드객체로 변환.다시 커맨드객체 반환(JSON으로 변환되서 반환)
	@PostMapping(value="/Annotation/RequestBody.do")	
	public LoginCommand exec(@RequestBody LoginCommand cmd) {
		System.out.println("RestController 사용");
		System.out.println("아이디:"+cmd.getUser());
		System.out.println("비번:"+cmd.getPass());
		return cmd;
	}
}
