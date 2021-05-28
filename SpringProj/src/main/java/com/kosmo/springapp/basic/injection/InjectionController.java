package com.kosmo.springapp.basic.injection;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InjectionController {

	//Person은 주입받자. @Resource는 생성자를 제외한 세터 및 속성에 붙인다.
	@Resource(name="personDefault")
	private Person personDefault;
	
/*	
	//@Autowired는 생성자에도 붙일 수 있다. (=생성자 인제션)
	@Autowired
	public InjectionController(Person personDefault) {
		this.personDefault = personDefault;
	}
*/
	
	//컨트롤러 메소드]
	@RequestMapping("Injection/Injection.do")
	public String execute(Model model,@RequestParam Map map) {
		
		//사용자 입력값으로 세팅
		personDefault.setAddr(map.get("addr").toString());
		personDefault.setAge(Integer.parseInt(map.get("age").toString()));
		personDefault.setName(map.get("name").toString());
		
		//데이터 저장
		model.addAttribute("personInfo",personDefault);
		//뷰정보 반환
		return "Injection05/Injection";
	}
	
	
	
}///