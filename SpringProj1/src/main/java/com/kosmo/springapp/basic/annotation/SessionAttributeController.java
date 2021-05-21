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
	 - 서블릿 API를 사용하지 않고 세션처리를 하기 위한 어노테이션
	 - 클래스 앞에 붙인다,
	 - 세션변수에 값을 저장하려면 메소드의 매개변수 중 모델계열(Model,Map,ModelMap)을 추가하여
	 	add.~~
	 	저장하면 그 이름으로 세션영역에도 저장된다.(리퀘스트 영역뿐만 아니라)
	 	
	 예] @SessionAttributes("세션변수명")
	 세션변수명이 여러개 일 때
	 	@SessionAttributes({"세션변수명1","세션변수명2"})
	 	
	 - 세션영역에서 값 읽어올때
	 
	 	컨트롤러 메서드(@ModelAttribute(value="세션변수명") String 세션값받을변수)
	 
	 - 세션영역 해제
	 	
	 	컨트롤러 메소드(Session session{
	 		session.setComplete();
	 	}
	 	
	 */

//컨트롤러 클래스]
// 1. 커맨드 객체 미 사용 - 아이디/비번 파라미터를 맵으로 받을 시 세션영역에 저장할 속성명은 폼의 파라미터명과 일치 시켜라
//@SessionAttributes({"user","pass"})

// 2. 커맨드 객체를 사용해서 세션처리하기 - 아이디/비번 파라미터를 커맨드 객체로 받을 시
@SessionAttributes(types=LoginCommand.class)

@Controller
@RequestMapping("/Annotation")
public class SessionAttributeController {
	//컨트롤러 메서드]
	//서블릿 API사용]
//	//로그인 처리]
//	@RequestMapping("/SessionAttributeLogin.do")
//	public String login(HttpSession session, @RequestParam Map map,Model model) {
//		//데이터 저장]
//		//회원 여부 판단]
//		if("KIM".equals(map.get("user")) && "1234".equals(map.get("pass"))) {
//			//로그인 처리 - 세션영역에 필요값 저장
//			session.setAttribute("user",map.get("user"));
//		}
//		else {
//			model.addAttribute("errorMessage","아이디와 비번 불일치");
//		}
//		//뷰정보 반환]
//		return "annotation06/Annotation";
//	}
//	
//	//로그아웃]
//	@RequestMapping("/SessionAttributeLogout.do")
//	public String logout(HttpSession session) {
//		//로그아웃 처리- 세션영역에 저장된 속성 삭제]
//		session.invalidate();
//		//뷰정보 반환]
//		return "annotation06/Annotation";
//	}
//	
//	
//	//로그인 판단 여부]
//	@RequestMapping("/isLogin.do")
//	public String isLogin(HttpSession session,Model model) {
//		//로그인 여부 판단- 세션영역에 존재 유뮤로 판단후 데이터 저장]
//		model.addAttribute("isLoginMessage",session.getAttribute("user")==null?"로그인 하세요":"로그인 되었습니다.");
//		//뷰정보 반환]
//		return "annotation06/Annotation";
//	}

	//[@SessionAttribute 어노테이션 사용]
	//[1.세션 영역에 저장할 속성명을 문자열로 지정시
	//로그인 처리]
//	@RequestMapping("/SessionAttributeLogin.do")
//	public String login(@RequestParam Map map, Model model) {
//	//데이터 저장]
//	//회원 여부 판단]
//	if("KIM".equals(map.get("user")) && "1234".equals(map.get("pass"))) {
//		//로그인 처리 - 세션영역에 필요값 저장
//		model.addAllAttributes(map);
//	}
//	else {
//		model.addAttribute("errorMessage","아이디와 비번 불일치");
//	}
//	//뷰정보 반환]
//	return "annotation06/Annotation";
//	}
//	
//	
//	//로그아웃]
//	@RequestMapping("/SessionAttributeLogout.do")
//	public String logout(SessionStatus status) {
//		//로그아웃 처리 - 세션영역에 저장된 속성 삭제]
//		status.setComplete();
//		//뷰정보 반환]
//		return "annotation06/Annotation";
//	}
//	
//	//로그인 여부 판단] - 세션 영역에 반드시 user라는 속성(키가) 존재해야한다. 그렇지 않으면 에러
//	@RequestMapping("/isLogin.do")
//	public String isLogin(@ModelAttribute("user") String id,Model model) {
//		
//		model.addAttribute("isLoginMessage",id+"님 로그인 되었어요");
//		//뷰정보 반환]
//		return "annotation06/Annotation";
//	}
//	
	@ExceptionHandler({Exception.class})
	public String error(Exception e, Model model) {
		model.addAttribute("isLoginMessage","로그인 하세요");
		//뷰정보 반환
		return "annotation06/Annotation";
	}
	
	//[2.커맨드 객체 사용]
	//로그인 처리
	@RequestMapping("/SessionAttributeLogin.do")
	public String login(LoginCommand cmd, Model model,SessionStatus status) {
	//데이터 저장]
	//회원 여부 판단]
		//키값은 자동으로 소문자로 시작하는 커맨드 클래스명이 키값이 됨
		//매개변수의 LoginCommand 객체가 무조건 (회원이든 아니든) 자동으로 세션 영역에 저장됨
		//단,빈 설정 파일에 <annotation-driven/>태그 추가해야됨
		//예]session.setAttribute("loginCommand",cmd)식으로 저장됨
	if(!("KIM".equals(cmd.getUser()) && "1234".equals(cmd.getPass()))) {
		//로그인 처리 - 세션영역에 필요값 저장
		model.addAttribute("errorMessage","아이디 비번 불일치");
		//무조건 커맨드객체를 세션영역에 저장하기 때문에
		//회원이 아닌 경우 삭제 해줘야한다.
		status.setComplete();
		
	}
	//뷰정보 반환]
	return "annotation06/Annotation";
	}
	
	
	//로그아웃]
	@RequestMapping("/SessionAttributeLogout.do")
	public String logout(SessionStatus status) {
		//로그아웃 처리 - 세션영역에 저장된 속성 삭제]
		status.setComplete();
		//뷰정보 반환]
		return "annotation06/Annotation";
	}
	
	//로그인 여부 판단] - 세션 영역에 반드시 loginCommand라는 속성(키가) 존재 해야 한다. 그렇지 않으면 에러
	@RequestMapping("/isLogin.do")
	public String isLogin(@ModelAttribute("loginCommand") LoginCommand cmd,Model model) {
		
		model.addAttribute("isLoginMessage",cmd.getUser()+"님 로그인 되었어요");
		//뷰정보 반환]
		return "annotation06/Annotation";
	}
	
}


