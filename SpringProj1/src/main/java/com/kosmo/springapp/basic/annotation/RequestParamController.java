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

@Controller
@RequestMapping("/Annotation")
public class RequestParamController {
	
	
	//컨트롤러 메소드-파라미터명과 변수 일치시
//	@RequestMapping("/RequestParamEqual.do")
//	public String equals(HttpServletRequest req) {
//		try {
//			//파라미터 받기] - HttpServletRequest사용시
//			String name = req.getParameter("name");
//			int age = Integer.parseInt(req.getParameter("years"));
//			//리퀘스트 영역에 저장
//			req.setAttribute("name", name);
//			req.setAttribute("years", age+10);
//		}
//		catch(Exception e) {
//			req.setAttribute("errorNumber","나이는 숫자만...");
//		}
		//뷰 정보 반환]
	
	
		//이 방식은 오류 내용이 안나온다, try,catch도 안됨
		//해결방법
		//@RequestParam 사용시 - 나이를 숫자가 아닌 값 입력시 오류 처리]
		@ExceptionHandler({Exception.class})
		public String error(Exception e, Model model) {
			model.addAttribute("errorNumber","Please Input Number in the Years Field");
			return "annotation06/Annotation";
//2.		return "annotation06/Annotation.jsp?error="+e.getMessage();
		}
		
		@RequestMapping("/RequestParamEqual.do")
		public String equals(@RequestParam String name,@RequestParam int years, Model model) {
		//@ReuqestParam으로 받을때는 위의 getParameter() 불필요 및 형변환 불 필
		model.addAttribute("name",name);
		model.addAttribute("years",years+10);
		return "annotation06/Annotation";
	}

		@RequestMapping("/RequestParamDiff.do") //파라미터랑 변수랑 다를 때 방법 2가지
		public String diff(Model model, @RequestParam(defaultValue = "한소인",required =false,value="nick2") String name, @RequestParam("age") int years) {
			//데이터 저장
			model.addAttribute("name",name);
			model.addAttribute("years",years+10);
			return "annotation06/Annotation";
		}/////
		
		//컨트롤러 메서드] - Map으로 파라미터 받기.
		//단, 체크박스는 여러개 선택해도 하나만 받는다.
		@RequestMapping("/RequestParamMap.do")
		public String map(@RequestParam Map map, ModelMap model, @RequestParam String[] inters) {
			//폼의 파라미터명이 키 값이 되고 입력하거나 선택하거나 값이 값이 된다.
			//단 체크박스류는 무조건 첫번째 선택한 것이 선택된다.
			//데이터 저장
			System.out.println("inters(map에 저장된 키 값) : "+map.get("inters"));
			System.out.println("inters(String[]) : "+inters);
			map.put("inters",Arrays.toString(inters));
			model.addAllAttributes(map);
			//뷰정보 반환]
			return "annotation06/Annotation";
		}
}
