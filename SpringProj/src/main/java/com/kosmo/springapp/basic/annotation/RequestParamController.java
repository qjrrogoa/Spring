package com.kosmo.springapp.basic.annotation;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//컨트롤러 클래스
@Controller
@RequestMapping("/Annotation")
public class RequestParamController {

	//기본의 방법] 컨트롤러 메소드
//	@RequestMapping("/RequestParamEqual.do")
/*	public String equals(HttpServletRequest req) {
		try {
		//파라미터 받기] HttpServletRequest 사용시
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("years"));
		
		//리퀘스트 영역에 저장
		req.setAttribute("name", name);
		req.setAttribute("years", age+10);
		}catch (Exception e) {
			req.setAttribute("errorNumber","나이는 숫자만...");
		}
		//뷰정보 반환
		return "annotation06/Annotation";
	}
*/	
	
	//어노테이션을 이용한 방법] 컨트롤러 메소드 - Get파라미터를 받을필요없고, 형변환하지 않아도 됨
	@RequestMapping("/RequestParamEqual.do")
	public String equals(@RequestParam String name,@RequestParam int years, Model model) {
		
		//데이터 저장
		model.addAttribute("name",name);
		model.addAttribute("years",years+10);
		//years에 숫자가 아닌 데이터를 받으면 에러가 발생하지만 try~catch 문법을 시도할수없음
		
		//뷰정보 반환
		return "annotation06/Annotation";
	}
	
	//모든 예외를 처리하는 문구
	/*
	@ExceptionHandler({Exception.class})
	public String error(Exception e, Model model) {
		model.addAttribute("errorNumber","나이는 숫자만...");
		System.out.println(e);
		//에러 발생시
		//return "annotation06/Annotation";
		//return "annotation06/Annotation.jsp?error="+e.getMessage();
		return "forward:/WEB-INF/views/annotation06/Annotation.jsp?error="+e.getMessage();
	}
	*/
	
	@RequestMapping("/RequestParamDiff.do")
	//@RequestParam(required = false) 필수사항이 아니라는 뜻으로, 값이 제대로 넘어오지 않아도 실행이 됨
	//@RequestParam(defaultValue = "한소인") 값이 넘어오지 않는다면 디폴트값으로 지정할 문자열을 설정해줌
	//@RequestParam(value = "nick2") 현재 지정한 매개변수명과 파라미터가 다를경우 value 속성으로 직접 정해줄수있음
	public String diff(@RequestParam(required = false, defaultValue = "한소인", value = "nick2") String name, @RequestParam("age") int years, Model model) {
		//데이터 저장
		System.out.println("name : "+name);
		model.addAttribute("name",name);
		model.addAttribute("years",years+10);
		
		//뷰정보 반환
		return "annotation06/Annotation";
	}
	
	
	//컨트롤러 메소드] - Map으로 파라미터 받기
	//단, 체크박스는 여러개를 선택해도 하나만 받아진다.
	@RequestMapping("/RequestParamMap.do")
	public String map(@RequestParam Map map, ModelMap model,@RequestParam String[] inters) {
		//폼의 파라미터명이 키값이 되고, 입력하거나 선택한 값이 value가 된다.
		//단, 체크박스류는 무조건 첫번째 선택한것만 저장된다.
		System.out.println("inters(map에 저장된 키값):"+map.get("inters"));
		System.out.println("inters(String[]):"+inters);
		
		//데이터 저장
		map.put("inters", Arrays.toString(inters));
		model.addAllAttributes(map);
		
		//뷰정보 반환
		return "annotation06/Annotation";
	}
}///////