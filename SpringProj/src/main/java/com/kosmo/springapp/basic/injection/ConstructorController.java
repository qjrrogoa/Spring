package com.kosmo.springapp.basic.injection;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//컨트롤러 클래스]

@Controller
public class ConstructorController {
	
	/* [현재 클래스에서 Person객체에 의존]
	  ※ new 연산자로 Person객체를 직접 생성하지는 않고 설정파일(.xml)을 통해서 생성자로 주입을 받는다.	 */
	//생성자 인젝션을 받기위한 단계]
	//STEP1] 주입받는 타입의 갯수에 맞게 멤버변수 선언
	private Person personType, personIndex;
	
	//STEP2] 인자 생성자 정의
	@Autowired //생략가능
	public ConstructorController(Person personType, Person personIndex) {
		this.personType = personType;
		this.personIndex = personIndex;
	}
	
	@RequestMapping("/Injection/Constructor.do")
	public String execute(Map map) {
		//데이터 저장
		map.put("personInfo", personType+"<hr/>"+personIndex);
		//뷰정보 반환
		return "Injection05/Injection";
	}

}///////ConstructorController