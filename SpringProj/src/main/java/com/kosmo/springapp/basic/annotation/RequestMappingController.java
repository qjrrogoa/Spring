package com.kosmo.springapp.basic.annotation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//컨트롤 클래스
@Controller
public class RequestMappingController {
	
	//컨트롤러 메소드]
	/* @RequestMapping("/요청URL")
	 - 주로 요청을 처리하는 메소드 앞에 붙인다.
	 - 컨텍스트 루트를 제외한 /로 시작하는 요청URL
	 - GET방식 및 POST방식 둘다 처리 가능

//	@RequestMapping("/Annotation/RequestMappingBoth.do")
//	@GetMapping("/Annotation/RequestMappingBoth.do")
//	@PostMapping("/Annotation/RequestMappingBoth.do")
/*	public String both(HttpServletRequest req) {
		//데이터 저장] 리퀘스트 영역에 직접저장
		String loginInfo = String.format("[아이디:%s, 비밀번호:%s, 요청방식:%s]",
				req.getParameter("UserID")==null?req.getParameter("user"):req.getParameter("UserID"),
				req.getParameter("UserPWD")==null?req.getParameter("pass"):req.getParameter("UserPWD"),
				req.getMethod()
				);
		req.setAttribute("loginInfo", loginInfo);
		
		//뷰정보 반환]
		return "annotation06/Annotation";
	}*/
	
	//@RequestMapping(value="/요청URL패턴",method=전송방식지정)
	//전송방식을 모두 쓰려면 method = {RequestMethod.GET, RequestMethod.POST} 하면되지만 의미없음
//	@RequestMapping(value="/Annotation/RequestMappingOne.do",method = {RequestMethod.POST})
/*	
 	public String one(HttpServletRequest req) {
		return both(req);
	}/////////
	*/
	
	
	//여러개의 .do 요청과 여러종류의 전송방식을 동시에 쓰기위한 방식
//	@RequestMapping(value= {"/Annotation/RequestMappingOne.do","/Annotation/RequestMappingBoth.do"}, method = {RequestMethod.POST,RequestMethod.GET})
/*
	public String multi(@RequestParam Map map, Model model) {
		String id = map.get("UserID") ==null?map.get("user").toString():map.get("UserID").toString();
		String pwd = map.get("UserPWD")==null?map.get("pass").toString():map.get("UserPWD").toString();
		model.addAttribute("loginInfo",String.format("[아이디:%s, 비밀번호:%s]",id,pwd));
		
		//뷰 정보 반환
		return "annotation06/Annotation";	
	}/////////
*/	
	//여러개의 .do 요청을 처리하기위한 방식
	@RequestMapping("/Annotation/{path}")
	public String path(@PathVariable String path, @RequestParam Map map, Model model) {
		
		String id,pwd;
		switch (path) {
		case "RequestMappingOne":
			id=map.get("user").toString();
			pwd=map.get("pass").toString();
			break;
		default:
			id=map.get("UserID").toString();
			pwd=map.get("UserPWD").toString();
			break;
		}
		model.addAttribute("loginInfo",String.format("[아이디:%s, 비밀번호:%s]",id,pwd));
		
		return "annotation06/Annotation";
	}
	
}/////