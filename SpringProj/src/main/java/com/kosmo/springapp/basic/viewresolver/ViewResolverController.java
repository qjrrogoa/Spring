package com.kosmo.springapp.basic.viewresolver;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ViewResolverController {

	
	
/*	
	//컨트롤러 메소드] - 1. 로직 호출
	@RequestMapping("/ViewResolver/ViewResolver.do")
	public String execute(Model model) {
		
		//2. 결과값 저장
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
		model.addAttribute("message",dateFormat.format(new Date()));
		
		//3. 뷰 정보 반환 (접두어(/WEB-INF/views/)와 접미어(.jsp)를 제외한 논리적 이름만 반환
		//3-1[O]. 디폴트
		//return "viewresolver03/ViewResolver";
		//3-2[X]. .jsp 접미어 사용시 : 404에러 발생(접미어가 한번 더 붙어서 .jsp.jsp라는 파일을 찾게됨)
		//return "viewresolver03/ViewResolver.jsp";
		//3-3[X]. .do 접미어 사용시 : 404에러 발생(do.jsp라는 파일을 찾을 수 없음)
		//return "/ViewResolver/NotJSP.do";
		
		//※ InternalResourceViewResolver를 통한 접두어, 접미어에 영향을 받지 않으려면 return시에
		//"forward:"나 "redirect:"를 붙여줘야 한다.
		//단, "redirect:"는 "redirect:jsp페이지 경로"가 아닌 Context경로 또는 ModelAndView객체로 반환해야한다.
		
		//3-4[O]. 접두어, 접미어 영향을 받지 않고 이동하기(3-1과 완전히 같음) : .jsp페이지로 forward
		//return "forward:/WEB-INF/views/viewresolver03/ViewResolver.jsp";
		
		//3-5[O]. .do로 forward : 파라미터를 넘길수있음
		//return "forward:/ViewResolver/NotJSP.do?message=parameter";
		
		//3-6[X]. .jsp페이지로 redirect : 404에러 발생(스프링에서는 /WEB-INF 경로에 있는 jsp페이지에 직접 접근할 수 없기 때문)
		//return "redirect:/WEB-INF/views/viewresolver03/ViewResolver.jsp";
		
		//3-5[△]. .do로 redirect : 리다이렉트이기 때문에 영역속성(message)은 전달되지 않음
		//return "redirect:/ViewResolver/NotJSP.do?message=parameter";
		
	}///execute
*/
	//컨트롤러 메소드] - 1. 로직 호출 (ModelAndView객체 반환 방식)
	@RequestMapping("/ViewResolver/ViewResolver.do")
	public ModelAndView execute(Model model) {
		
		//2. 결과값 저장
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
		model.addAttribute("message",dateFormat.format(new Date()));
		model.addAttribute("name","KOSMO");
		///ViewResolver/NotJSP.do?message=dateFormat&name=KOSMO 형식으로 정보가 넘어감
		
		//3. 뷰 정보 반환
		//영역에 저장된 값이 쿼리스트링으로 전달됨. 아래는 message를 직접 "parameter"로 입력되었기 때문에 덮어씌워진 상태
		//return new ModelAndView(new RedirectView("/ViewResolver/NotJSP.do?message=parameter", true));
		return new ModelAndView(new RedirectView("/ViewResolver/NotJSP.do", true));
		
	}
	
	//컨트롤러 메소드] execute()메소드에서 포워드로 같은 요청을 받는 메소드
	@RequestMapping("/ViewResolver/NotJSP.do")
	public String notjsp(@RequestParam String message) {
		System.out.println("파라미터:"+message);
		
		return "viewresolver03/ViewResolver";
	}
	
	
}////ViewResolverController