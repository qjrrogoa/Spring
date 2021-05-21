package com.kosmo.springapp.basic.annotation;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResourceController {
	//@Resource : id -> 타입 -> Qualifier
	//반드시 IOC가 되어 빈이 생성되어 있어야 한다. 그렇지 않으ㄴ

//	@Resource
//	private Command fCommand;
//	@Resource
//	private Command sCommand;
	

	@Resource(name="fCommand")
	private Command fCmd;
	@Resource(name="sCommand")
	private Command sCmd;
	
	
	@RequestMapping("/Annotation/Resource.do")
	public String execute(Model model) {
		model.addAttribute("message",String.format("fCommand : %s, sCommand : %s",fCmd,sCmd));
	      return "annotation06/Annotation";
	}
}
