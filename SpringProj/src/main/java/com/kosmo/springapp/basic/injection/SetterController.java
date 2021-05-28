package com.kosmo.springapp.basic.injection;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SetterController {
	//[현재 클래스가 Person객체 필요] new하지않고 세터를 통해서 인젝션

	//STEP1] 주입받는 타입의 갯수에 맞게 멤버변수 선언
//	private Person personType, personProperty;
	@Resource(name="personType")
	private Person personType;
	@Resource(name="personProperty")
	private Person personProperty;
	//멤버변수를 만들어서 @Resource 어노테이션을 붙이는게 아래처럼 세터에 이노터이션을 붙이는것보다 나음
	
/*	
	@Resource(name = "personType")//빈 속성의 이름(id)속성에 지정한 값과 일치
	@Qualifier("person1")
	public void setPersonType(Person personType) {
		this.personType = personType;
	}
	
	@Resource(name = "personProperty")
	@Qualifier("person3")
	public void setPersonProperty(Person personProperty) {
		this.personProperty = personProperty;
	}
*/
	//컨트롤러 메소드]
	@RequestMapping("/Injection/Setter.do")
	public String execute(Map map) {
		//데이터 저장
		map.put("personInfo", personType+"<hr/>"+personProperty);
		//뷰정보 반환
		return "Injection05/Injection";
	}
	
	
}////SetterController