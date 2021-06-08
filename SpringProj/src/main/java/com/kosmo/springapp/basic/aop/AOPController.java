package com.kosmo.springapp.basic.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AOPController {

	@Autowired
	private TargetClass target;
	
	@RequestMapping("/AOP/AOP.do")
	public String execute(Model model) {
		long total=target.getTotal();
		model.addAttribute("total", total);
		return "aop15/Aop";
	}
}
