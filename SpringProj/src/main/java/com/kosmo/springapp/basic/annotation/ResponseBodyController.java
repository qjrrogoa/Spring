package com.kosmo.springapp.basic.annotation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //주로 페이지를 응답할때 사용되는 어노테이션
public class ResponseBodyController {

	//웹브라우저로 직접 출력하기
	//방법1] 반환타입은 void, 매개변수는 서블릿 API 사용 - @ResponseBody어노테이션 미사용
/*
	@RequestMapping("/Annotation/ResponseBody.do")
	public void exec(HttpServletResponse resp) throws IOException {
		//한글처리 및 MIME타입 설정
		resp.setContentType("text/html; charset=UTF-8");
		//브라우저로 출력하기 위한 출력 스트림
		PrintWriter out = resp.getWriter();
		out.println("<fieldset>");
		out.println("<legend> 반환타입 : void, 서블릿 API 사용 : HttpServletResponse</legend>");
		out.println("<h2>웹브라우저로 직접 출력합니다.</h2>");
		out.println("</fieldset>");
		//스트림 닫기
		out.close();
	}
*/
	//방법2] 반환타입 String, 서블릿 API 미사용 및 @ResponseBody어노테이션 사용
	@RequestMapping(value="/Annotation/ResponseBody.do", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String exec() {
		return "<h2>반환타입 : String, 서블릿 API 미사용, @ResponseBody 사용</h2>";
	}
	
}/////////