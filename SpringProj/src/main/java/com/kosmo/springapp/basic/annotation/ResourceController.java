package com.kosmo.springapp.basic.annotation;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResourceController {
	//@Resource : id-> 타입->Qualifier
	//반드시 IOC가 되어 빈이 생성되어 있어야 한다.그렇지 않으면 무조건 에러
	@Resource(name ="fCommand")//name속성에 id값 지정
	//@Qualifier("fCommand")
	private Command fCmd;
	@Resource(name="sCommand")
	//@Qualifier("sCommand")
	private Command sCmd;
	
	


	@RequestMapping("/Annotation/Resource.do")
	public String execute(Model model) {
		model.addAttribute("message", String.format("fCcommand:%s,sCommand:%s", fCmd,sCmd));
		//뷰정보 반환]
		return "annotation06/Annotation";
	}
	
	
}
