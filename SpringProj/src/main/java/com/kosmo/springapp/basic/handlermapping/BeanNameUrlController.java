package com.kosmo.springapp.basic.handlermapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//컨트롤러 클래스]
public class BeanNameUrlController implements Controller {

	//컨트롤러 메소드]
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1] 요청받고 분석
		//2] 비지니스 로직(모델) 호출 및 결과값 받기
		//3] 필요한 값을 리퀘스트 영역에 저장하거나 ModelAndView에 저장
		ModelAndView mav = new ModelAndView();
		//4] 데이터 저장
		mav.addObject("message","[BeanNameUrlHandlerMapping]");
		//5] 뷰 정보 저장
		mav.setViewName("handlermapping01/handlermapping");
		//6] 디스패처서블릿에게 ModelAndView 반환
		return mav;
	}

	
}////BeanNameUrlController