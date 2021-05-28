package com.kosmo.springapp.basic.annotation;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
/*
	@SessionAttribute() 어노테이션]
	- 서블릿 API(HttpSession)를 사용하지 않고 세션처리를 하기위한 어노테이션
	- 클래스 앞에 붙인다.
	- 세션변수(세션 영역에 저장한 속성명)에 값을 저장하려면
	  메소드의 매개변수중 모델계열(Model, Map, ModelMap)을 추가하여 add계열("세션변수명",값)으로
	  저장하면 해당이름으로 세션영역에도 저장된다. (리퀘스트 영역뿐만 아니라)
	 
	예] @SessionAttribute("세션변수명")
	※세션변수명이 여러개일때는 @SessionAttribute({"세션변수명1","세션변수명2",..})
	
	- 세션영역에서 값 읽어올때 : 
	  컨트롤러 메소드(@ModelAttribute(value="세션변수명") String 세션값을받을변수)
	- 세션해제 : 
	  컨트롤러 메소드(SessionStatus session){
	  session.setComplete(); 
	  }
*/

/* 2. 커맨드 객체 미사용 - 아이디/비번 파라미터를 맵으로 받을때는 
세션 영역에 저장할 속성명은 폼의 파라미터명과 일치시켜야한다.*/
//@SessionAttributes({"user","pass"})

//3. 커맨드 객체 사용 - 아이디/비번 파라미터를 커맨드 객체로 받을때 사용
@SessionAttributes(types = LoginCommand.class)
@Controller
@RequestMapping("/Annotation")
public class SessionAttributeController {
	
	//1. 컨트롤러 메소드] - 서블릿 API 사용
/*	
	//로그인 처리]
	@RequestMapping("/SessionAttributeLogin.do")
	public String login(HttpSession session, @RequestParam Map map, Model model) {
		
		//데이터 저장
		//회원여부 판단
		if("KIM".equals(map.get("user")) && "1234".equals(map.get("pass"))) {
			session.setAttribute("user", map.get("user"));
		} else {
			model.addAttribute("errorMessage", "아이디와 비밀번호 불일치!!!");
		}
		
		//View 정보 반환]
		return "annotation06/Annotation";
	}
	
	//로그아웃]
	@RequestMapping("/SessionAttributeLogout.do")
	public String logout(HttpSession session) {
		//로그아웃 처리 - 세션영역에 저장된 속성 삭제]
		session.invalidate();
		//View 정보 반환]
		return "annotation06/Annotation";
	}
	
	//로그인 여부 판단]
	@RequestMapping("/isLogin.do")
	public String isLogin(HttpSession session, Model model) {
		//로그인 여부 판단 - 세션영역에 존재 유무로 판단후 데이터 저장
		session.setAttribute("isLoginMessage", session.getAttribute("user")==null?"로그인 하세요":"로그인 되었습니다");
		//View 정보 반환]
		return "annotation06/Annotation";
	}
*/
	
	//2. 컨트롤러 메소드] - @SessionAttribute() 어노테이션 사용
/*	
	//로그인 처리]
	@RequestMapping("/SessionAttributeLogin.do")
	public String login(@RequestParam Map map, Model model) {
		//데이터 저장
		//회원여부 판단
		if("KIM".equals(map.get("user")) && "1234".equals(map.get("pass"))) {
			//@SessionAttributes({"user","pass"}) 사용시 리퀘스트영역과 세션영역에 동시에 저장됨
			model.addAllAttributes(map);
		} else {
			model.addAttribute("errorMessage", "아이디와 비밀번호 불일치!!!");
		}
		//View 정보 반환]
		return "annotation06/Annotation";
	}
	
	//로그아웃]
	@RequestMapping("/SessionAttributeLogout.do")
	public String logout(SessionStatus status) {
		//로그아웃 처리 - 세션영역에 저장된 속성 삭제]
		status.setComplete();
		//View 정보 반환]
		return "annotation06/Annotation";
	}
	
	//로그인 여부 판단] - Session 영역에 반드시 user라는 속성의 값이 존재해야 메소드가 정상 실행됨
	//@ModelAttribute 대신 HttpSession session 인자를 사용하거나, @ExceptionHandler로 에러처리
	@RequestMapping("/isLogin.do")
	public String isLogin(@ModelAttribute(value = "user") String id, Model model) {
		//로그인 여부 판단 - 세션영역에 존재 유무로 판단후 데이터 저장
		model.addAttribute("isLoginMessage", id+"님 로그인 되었어요");
		//View 정보 반환]
		return "annotation06/Annotation";
	}
*/
	@ExceptionHandler({Exception.class})
	public String error(Exception e, Model model) {
		model.addAttribute("isLoginMessage", "로그인 해주세요 : "+e);
		//View 정보 반환]
		return "annotation06/Annotation";
	}
	
	//3. 컨트롤러 메소드] - @SessionAttribute() 어노테이션 및 커맨드 객체 사용
	//로그인 처리]
	@RequestMapping("/SessionAttributeLogin.do")
	public String login(LoginCommand cmd, Model model,SessionStatus status) {
		//데이터 저장
		//회원여부 판단
		/* ※키값은 자동으로 커맨드 클래스명이 첫글자가 소문자로 바뀌면서 키값(loginCommand)이 됨
		   매개변수의 LoginCommand객체가 무조건(회원이든 아니든) 자동으로 세션 영역에 저장됨
		   단, 빈 설정 파일에 <annotation-driven/>태그가 추가되어있어야함
		   예]session.setAttribute("loginCommand",cmd)식으로 저장됨		*/
		if(!("KIM".equals(cmd.getUser()) && "1234".equals(cmd.getPass()))) {
			//로그인 처리 - 세션영역에 필요한 값 저장
			model.addAttribute("errorMessage","아이디/비번이 불일치합니다");
			status.setComplete();
		}
		//View 정보 반환]
		return "annotation06/Annotation";
	}
	
	//로그아웃]
	@RequestMapping("/SessionAttributeLogout.do")
	public String logout(SessionStatus status) {
		//로그아웃 처리 - 세션영역에 저장된 속성 삭제]
		status.setComplete();
		//View 정보 반환]
		return "annotation06/Annotation";
	}
	
	//로그인 여부 판단] - Session 영역에 반드시 logincommand라는 속성의 값이 존재해야 메소드가 정상 실행됨
	//@ModelAttribute 대신 HttpSession session 인자를 사용하거나, @ExceptionHandler로 에러처리
	@RequestMapping("/isLogin.do")
	public String isLogin(@ModelAttribute(value = "loginCommand") LoginCommand id, Model model) {
		//로그인 여부 판단 - 세션영역에 존재 유무로 판단후 데이터 저장
		model.addAttribute("isLoginMessage", id+"님 로그인 되었어요");
		//View 정보 반환]
		return "annotation06/Annotation";
	}
}//////////