package com.kosmo.springapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
[일반 자바클래스 형태 즉, POJO(Plain Old Java Object)]

 컴파일러에게 "아래 클래스를 사용자 요청을 처리하는 클래스이다."라고 알려주는 역할			*/
//컨트롤러 클래스]
@Controller
public class IndexController {

	//컨트롤러 메소드]
	@RequestMapping("/handlermapping.do")
	public String handlerMapping() {
		//View 정보 반환]
		return "handlermapping01/handlermapping";
	}///handlerMapping
	
	//컨트롤러 메소드]
	@RequestMapping("/controller.do")
	public String controller() {
		//View 정보 반환]
		return "controller02/controller";
	}///handlerMapping
	
	@RequestMapping("/viewresolver.do")
	public String viewresolver() {
		//View 정보 반환]
		return "viewresolver03/ViewResolver";
	}///viewresolver
	
	@RequestMapping("/returntype.do")
	public String returntype() {
		//View 정보 반환]
		return "returntype04/ReturnType";
	}///returntype
	
	@RequestMapping("/injection.do")
	public String injection() {
		//View 정보 반환]
		return "Injection05/Injection";
	}///injection
	
	@RequestMapping("/annotation.do")
	public String annotation() {
		//View 정보 반환]
		return "annotation06/Annotation";
	}///annotation
	
	@RequestMapping("/database.do")
	public String database() {
		//View 정보 반환]
		return "database07/Database";
	}///database
	
	@RequestMapping("/resource.do")
	public String resource() {
		//View 정보 반환]
		return "resource08/Resource";
	}///resource
	
	@RequestMapping("/validation.do")
	public String validation() {
		//View 정보 반환]
		return "validation09/Validation";
	}///resource
	
	@RequestMapping("/dynamicsql.do")
	public String dynamicsql() {
		//View 정보 반환]
		return "dynamicsql11/DynamicSQL";
	}///resource
	
}///IndexController