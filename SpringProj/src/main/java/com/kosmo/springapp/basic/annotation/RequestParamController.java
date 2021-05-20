package com.kosmo.springapp.basic.annotation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
//1.			//model.addAttribute("errorNumber","Please Input Number in the Years Field");
//1.			//return "annotation06/Annotation";
			return "annotation06/Annotation.jsp?error=";
		}
		
		@RequestMapping("/RequestParamEqual.do")
		public String equals(@RequestParam String name,@RequestParam int years, Model model) {
		//@ReuqestParam으로 받을때는 위의 getParameter() 불필요 및 형변환 불 필
		model.addAttribute("name",name);
		model.addAttribute("years",years+10);
		return "annotation06/Annotation";
	}

}
