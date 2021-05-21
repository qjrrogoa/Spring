package com.kosmo.springapp.basic.viewresolver;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

//컨트롤러 클래스]
@Controller
public class ViewResloverController {

	//컨트롤러 메서드
//	@RequestMapping("/ViewResolver/ViewResolver.do")
//	public String execute(Model model) {
		//데이터 저장]
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
//		model.addAttribute("message",dateFormat.format(new Date()));
		//뷰 정보 반환]
		//접두어와 접미어를 뺀 논리적인 이름만 반환
		
		//1. .jsp페이지로 .forward(디폴트)
//		return "viewresolver03/ViewResolver";
		
		//2. .do로 forward
//		return "/ViewResolver/NotJSP.do";
		//위처럼 뷰 정보 반환시 접두어 접미어가 붙어서 404에러 발생
		
		// 해결방법
		// 접두어나 접미어에 영향을 안받으려면 return시 "forward:" 이나 "redirect:"
		// 단,"redirect:"는 ModelAndView객체(View객체)로 반환하면 된다.
		
		// 접두어/접미어 영향 받지 않기-주로 .do로 이동시에 적용한다
		//2-1. [forward로 이동시]
//		return "forward:/WEB-INF/views/viewresolver03/ViewResolver.jsp";
		//2-2. do로 forward
//		return "forward:/ViewResolver/NotJSP.do?message=parameter";
		
		//[redirect로 이동시]
		// 리다이렉트시 모델 객체에 저장된 데이터는 쿼리스트링으로 전달된다.
		//2-3 .jsp페이로 redirect-/WEB-INF밑에 있는 JSP파일을 직접 URL로 요청한거와 같아 그래서 404에러
//		return "redirect:/WEB-INF/views/viewresolver03/ViewResolver.jsp";
		//2-4 .do로 redirect
//		return "redirect:/ViewResolver/NotJSP.do?message=parameter"; //파라미터값만 전달 됨
//	}
	//2-5 .do로 redirect 할 때
	@RequestMapping("/ViewResolver/ViewResolver.do")
	public ModelAndView execute(Model model) {
		// 리다이렉트시 모델객체에 저장된 데이터는 쿼리스트링으로 전달 됨
		//데이터 저장
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		model.addAttribute("message",dateFormat.format(new Date()));
		
		return new ModelAndView(new RedirectView("/ViewResolver/NotJSP.do",true)); //파라미터값만 전달 됨
	}//
	
	
	
	//컨트롤러 메서드(execute()메서드에서 포워드로 같은 요청을 받는 메서드)
	@RequestMapping("/ViewResolver/NotJSP.do")
	public String notjsp(@RequestParam String message) {
		
		System.out.println("파라미터:"+message);
		return "viewresolver03/ViewResolver"; 
		
	}

}