package com.kosmo.springapp.basic.handlermapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

//컨트롤러 클래스]
public class SimpleUrlFirstController extends AbstractController {
	//컨트롤러 메소드]
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("요청이 들어오나?");
		return new ModelAndView("handlermapping01/HandlerMapping","message","[SimpleUrlFirst.do]");
	}

}
