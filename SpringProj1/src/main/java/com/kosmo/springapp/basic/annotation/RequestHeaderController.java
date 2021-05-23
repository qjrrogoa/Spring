package com.kosmo.springapp.basic.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestHeaderController {

	@RequestMapping("/Annotation/RequestHeader.do")
	public String rerc(@RequestHeader(value = "user-agent",required = false,defaultValue="헤더명이 존재하지 않아요") String useragent,Model model ) {
		//데이터 저장]
		model.addAttribute("referer",useragent);
		//뷰정보 반환]
		return "annotation06/Annotation";
	}
	
}
