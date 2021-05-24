package com.kosmo.springapp.basic.annotation;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Annotation")
public class RequestParamController {
	
	/*[ 파라미터 받기 ]*/	
	 /*
	  *  파라미터를 받기 위해 HttpServletRequest 사용 안하고
	  *  @RequestParam어노테이션 사용
	  * 
	  * -파라미터의 자료형으로 받을 수 있다(String이나 int로 즉 형변환 불필요)
	  * -단,파라미터가 여러개일때는 
	  *  @ModelAttribute어노테이션이나 @RequestParam Map map이 유리
	  *  단,Map으로 받을때 체크박스류는 여러개 값중 하나만
	  *  
	  * -파라미터가 1~2개일때 유리하다
	  * -사용자가 전달한 파라미터 값이 매개변수에 저장됨
	  *  즉 매개변수명=리퀘스트객체.getParameter("파라미터명")와 같다
	  *  
	  * -required속성은 디폴트가 true이다 
	  *  만약 파라미터명이 매개변수 명과 다르다면
	  *  
	  *  방법1]    
	  *  @RequestParam(value="파라미터명") 자료형 매개변수명 -
	  *  required가 true
	  *  해당 파라미터명으로 전달이 안되면 에러(400에러)
	  *  
	  *  방법2]
	  *   @RequestParam(value="파라미터명",required=false) 자료형 매개변수명 
	  *   해당 파라미터명으로 전달이 안되도 필수가 아니기때문에
	  *   에러안남  *  
	  *  
	  */ 
	
	//@RequestParam어노테이션 사용시-나이를 숫자가 아닌 값 입력시 오류처리]	
	/*
	@ExceptionHandler({Exception.class})
	public String error(Exception e,Model model) {
		//model.addAttribute("errorNumber","Please Input Number in the Years Field!!");
		//return "annotation06/Annotation";
		//return "annotation06/Annotation.jsp?error="+e.getMessage();
		return "forward:/WEB-INF/views/annotation06/Annotation.jsp?error="+e.getMessage();
	}////////////	
	*/
	//컨트롤러 메소드-파라미터명과 변수 일치시
	@RequestMapping("/RequestParamEqual.do")
	public String equals(@RequestParam String name,@RequestParam int years,Model model ) {
//	public String equals(HttpServletRequest req) {
//		try {
//			//파라미터 받기]-HttpServletRequest사용시
//			String name=req.getParameter("name");
//			int age=Integer.parseInt(req.getParameter("years"));
//			//리퀘스트 영역에 저장
//			req.setAttribute("name", name);
//			req.setAttribute("years", age+10);
//		}
//		catch(Exception e) {
//			req.setAttribute("errorNumber", "나이는 숫자만...");
//		}
		//@RequestParam으로 받을때는 위의 getParameter() 불필요 및
		//형변환도 불필요.	
		//데이타 저장]
		model.addAttribute("name", name);
		model.addAttribute("years", years+10);
		//뷰정보 반환]
		return "annotation06/Annotation";
	}///////////////
	@RequestMapping("/RequestParamDiff.do")
	public String diff(Model model,@RequestParam(defaultValue = "한소인",required = false,value = "nick2") String name,@RequestParam("age") int years) {
		//데이타 저장]
		
		model.addAttribute("name", name);
		model.addAttribute("years", years+10);
		//뷰정보 반환]
		return "annotation06/Annotation";
		
	}////////////
	//컨트롤러 메소드]-Map으로 파라미터 받기.
	//단,체크박스는 여러개 선택해도 하나만 받는다.
	@RequestMapping("/RequestParamMap.do")
	public String map(@RequestParam Map map,ModelMap model,@RequestParam String[] inters) {
		//폼의 파라미터명이 키값이 되고 입력하거나 선택한 값이 값이된다
		//단 체크박스류는 무조건 첫번째 선택한 것만 저장된다.
		//데이타 저장]
		System.out.println("inters(map에 저장된 키값):"+map.get("inters"));
		System.out.println("inters(String[]):"+inters);
		map.put("inters",Arrays.toString(inters));
		model.addAllAttributes(map);
		//뷰정보 반환]
		return "annotation06/Annotation";
	}///////////////
	
	
}
