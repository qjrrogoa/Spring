package com.kosmo.springapp.basic.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AutoWiredController {

	//@Autowired : 타입-> id->Qualifier
	
	//필드에 부착
//	@Autowired(required = false)
//	@Qualifier("fCommand")
//	private Command fCommand;
//	@Autowired(required = false)
//	@Qualifier("sCommand")
//	private Command sCommand;
	
	private Command fCommand;
	private Command sCommand;
	
	//세터에 부착
//	@Autowired
//	@Qualifier("fCommand")
//	public void setfCommand(Command fCommand) {
//		this.fCommand = fCommand;
//	}
//	@Autowired(required = false)
//	@Qualifier("sCommand")
//	public void setsCommand(Command sCommand) {
//		this.sCommand = sCommand;
//	}
	//생성자에 부착(단,@Qualifier부착 불가)
	@Autowired	
	public AutoWiredController(Command fCommand,Command sCommand) {		
		this.fCommand = fCommand;	
		this.sCommand = sCommand;
	}


	@RequestMapping("/Annotation/Autowired.do")
	public String execute(Model model) {
		model.addAttribute("message", String.format("fCcommand:%s,sCommand:%s", fCommand,sCommand));
		//뷰정보 반환]
		return "annotation06/Annotation";
	}

	
}
