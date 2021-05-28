package com.kosmo.springapp.basic.annotation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller + @ResponseBody와 같은 기능. 주로 페이지가 아닌 데이터를 응답할때 사용됨
public class RequestBodyController {

//@RequestBody : JSON형식의 데이터를 받을때 사용하는 어노테이션. 스프링 부트는 내장되어 있음(Jackson라이브러리)
//Jackson라이브러리 : 자바 객체(DTO클래스)를 JSON 형식으로 또는 JSON 형식을 자바 객체로 변환해주는 라이브러리
	
	//아래는 데이터를 JSON으로 받고, 받은 데이터를 커맨드 객체(DTO 클래스)로 변환. 다시 커맨드객체를 반환.
	//Jakcson이 자동으로 Content Type을 지정해주기 때문에 produces = "text/plain; charset=UTF-8"을 입력할 필요없음
	@PostMapping(value="/Annotation/RequestBody.do")
	public LoginCommand exec(@RequestBody LoginCommand cmd) {
		System.out.println("<RestController 사용>");
		System.out.println("아이디 : "+cmd.getUser());
		System.out.println("비밀번호 : "+cmd.getPass());
		return cmd;
	}
}/////////