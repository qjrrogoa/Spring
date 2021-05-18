package com.kosmo.springapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
[일반 자바클래스 형태 즉 POJO(Plain Old Java Object)]

컴파일러에게 "아래 클래스는 사용자 요청을 처리하는 클래스야" 라고
알려주는 역할]-컨트롤러 클래스
*/
@Controller
public class IndexController {
	
	//컨트롤러 메소드]
	@RequestMapping("/handlermapping.do")
	public String handlerMapping() {
		//뷰정보 반환]
		return "handlermapping01/HandlerMapping";
	}///////////////////handlerMapping()
	
	@RequestMapping("/controller.do")
	public String controller() {
		//뷰정보 반환]
		return "controller02/Controller";
	}///////////////////handlerMapping()

}
