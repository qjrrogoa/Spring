package com.kosmo.springapp.basic.annotation;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResourceController {
	//@Resource : id -> 타입 -> Qualifier
	//반드시 IOC가 되어 빈이 생성되어 있어야 한다. 그렇지 않으면 무조건 에러 발생
	@Resource(name="fCommand")//name 속성에 id값 지정
//	@Qualifier("fCommand")
	private Command fCommand;
	@Resource(name="sCommand")
//	@Qualifier("sCommand")
	private Command sCommand;

	@RequestMapping("/Annotation/Resource.do")
	public String execute(Model model) {
		//데이터 저장
		model.addAttribute("message",String.format("fCommand : %s, sCommand : %s", fCommand, sCommand));
		
		//View 정보 반환]
		return "annotation06/Annotation";
	}
	
}//////