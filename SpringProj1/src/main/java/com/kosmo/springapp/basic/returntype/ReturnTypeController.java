package com.kosmo.springapp.basic.returntype;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

//컨트롤러 클래스]
@Controller
public class ReturnTypeController {
	
	//컨트롤러 메서드] 반환타입 - ModelAndView
	@RequestMapping("/ReturnType/ModelAndView.do")
	public ModelAndView ModelAndView(@RequestParam Map map, Model model) {
		//방법1]
		//데이터 저장 - Model에 데이터 저장
//1		model.addAllAttributes(map);
//		model.addAttribute("message", String.format("[파라미터:%s]", map.get("retrurnType")));
		//뷰정보 저장 - ModelAndView에 뷰 정보만 저장
//		return new ModelAndView("returntype04/ReturnType");
		//방법2] - 인수에 Model 인수에 없음
		//데이터 저장- ModelAndView에 데이터 저장
		ModelAndView mav =new ModelAndView();
		mav.addAllObjects(map);
		mav.addObject("message",String.format("[파라미터:%s]", map.get("returnType")));
		//뷰정보 저장 - ModelAndView에 뷰정보 저장
		//mav.setViewName("returntype04/ReturnType");
		mav.setView(new InternalResourceView("/WEB-INF/views/returntype04/ReturnType.jsp"));// 접두어/ 접미어 영향을 받지 않는다!
		return mav;
	}//////////
	
	//[컨트롤러 메소드] 반환타입 - String - 뷰정보만 반환
	@RequestMapping("/ReturnType/String.do")
	public String strign(@RequestParam String returnType,Map map) {
		//데이터 저장]
		map.put("message", String.format("[파라미터:%s]", returnType));
		map.put("returnType", String.format("[파라미터:%s]", returnType));
		//뷰정보 반환]
		return "returntype04/ReturnType";
	}/////////
	
	//[컨트롤러 메소드] 반환타입 - Void
	@RequestMapping("/ReturnType/Void.do")
	public void noreturn(@RequestParam String returnType,HttpServletResponse resp) throws IOException {
		/*
		 * 
		 * - Ajax나 RestAPI구현시 주로 사용
		 * - DispatcherServlet에게 모델 및 뷰 정도 전달 안함 ViewResolver를 거치지 않음
		 * - 웹브라우저에 바로 출력시 사용
		 *  즉 http응답바디에 데이터 출력시 사용
		 *  
		 */
		//응답헤더에 컨텍트 타입 설정] - 그래야 한글이 안깨짐
		resp.setContentType("text/html; charset=UTF-8");
		
		//웹브라우저에 출력하기 위한 출력 스트림 얻기]
		PrintWriter out = resp.getWriter();
		out.println("<fieldset>");
		out.println("<legend> 반환타입 : "+ returnType+"</legend>");
		out.println("<h2>웹 브라우저로 직접 출력합니다.</h2>");
		out.println("</fieldset>");
		//스트림 닫기
		out.close();
	}
	
}
