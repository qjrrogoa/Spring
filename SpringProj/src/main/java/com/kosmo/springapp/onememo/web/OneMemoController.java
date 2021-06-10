package com.kosmo.springapp.onememo.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kosmo.springapp.onememo.service.LineCommentDTO;
import com.kosmo.springapp.onememo.service.ListPagingData;
import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;

/*
 ※스프링 씨큐리티 사용시에는 기존 방식의 로그인처리 및 로그인 여부 판단 그리고 로그아웃등
   모두 제거한다(.jsp 에서 혹은 .java에서)
   그리고 나서 스프링 씨큐리티에서 제공하는 API로 처리한다
   단,로그인처리 및 로그아웃은 스프링 씨큐리티에서 제공함으로  로그인 판단 여부만 처리하면 된다.
 */
//@SessionAttributes("id") //스프링 씨큐리티를 사용할때 주석
@RequestMapping("/OneMemo/BBS/")
@Controller
public class OneMemoController {
	
	//서비스 주입]
	@Resource(name="memoService")
	private OneMemoService memoService;
	
	/* 로그인 하지 않고 각 컨트롤러 메소드 실행시 오류:@ModelAttribute("id") String id사용시 */
	/*
	//씨큐리티 사용시에는 아래 예외처리 불필요
	@ExceptionHandler({HttpSessionRequiredException.class})
	public String notLogin(Model model) {
		model.addAttribute("NotMember", "로그인후 이용 바람....");
		//로그인이 안된경우 로그인 페이지로
		return "onememo10/member/Login.tiles";
	}*/
	
	//목록 처리]
	@RequestMapping("List.do")
	public String list(
			//@ModelAttribute("id") String id,//(씨큐리티 미 사용시)세션영역에서 id가져오기
			Authentication auth,//씨큐리티 사용시(인증이 안된 사용자는 설정파일의 <security:form-login>태그의 login-page속성에 지정한 페이지로 바로 리다이렉트 됨)
			@RequestParam Map map,
			@RequestParam(required = false,defaultValue = "1") int nowPage,
			HttpServletRequest req,
			Model model) {//세션영역에서 id가져오기-isLogin.jsp파일 사용 불필요(단,에러처리는 해야함)
		/*스프링 씨큐리티 적용시 인증(로그인)되었다면
		  Authentication객체에 로그인과 관련된 정보가 전달됨
		   로그인이 안되어 있으면 auth는 null*/
		
		System.out.println("[Authentication객체 출력]");
		System.out.println("auth:"+auth);
		UserDetails userDetails=(UserDetails)auth.getPrincipal();
		System.out.println("아이디:"+userDetails.getUsername());
		System.out.println("비밀번호:"+userDetails.getPassword());
		Collection<GrantedAuthority> authorities= (Collection<GrantedAuthority>) userDetails.getAuthorities();
		for(GrantedAuthority authority:authorities) {
			System.out.println(authority.getAuthority());
		}
		
		
		//서비스 호출]
		ListPagingData listPagingData= memoService.selectList(map,req,nowPage);
		//데이타 저장]
		model.addAttribute("listPagingData", listPagingData);
		//뷰정보 반환]
		return "onememo10/bbs/List.tiles";
	}/////////
	//입력폼으로 이동]
	@RequestMapping(value = "Write.do",method = RequestMethod.GET)
	public String write(
			//@ModelAttribute("id") String id
			Authentication auth
			) {
		//뷰정보 반환]
		return "onememo10/bbs/Write.tiles";
	}/////////////
	//입력처리]
	@RequestMapping(value = "Write.do",method = RequestMethod.POST)
	public String writeoK(
			//@ModelAttribute("id") String id,
			Authentication auth,
			@RequestParam Map map) {
		//서비스 호출]	
		
		//map.put("id", id);//씨큐리티 미 사용시.호출전 아이디 맵에 저장
		map.put("id", ((UserDetails)auth.getPrincipal()).getUsername());//씨큐리티 사용시
		memoService.insert(map);		
		//뷰정보 반환]
		//뷰정보 반환:목록으로 이동
		return "forward:/OneMemo/BBS/List.do";
	}/////////////
	//컨트롤러 메소즈 작성 규칙]
	/*
	 * 접근지정자 : public
	 * 반환타입 : 주로 String(뷰정보를 문자열로 반환)
	 * 메소드명: 임의
	 * 인자 : 원하는 타입을 사용할 수 있다(단, 사용할 수 있는 타입이 정해져 있다)
	 *       어노테이션도 가능
	 * 예외를 throws할 수 있다(선택)
	 * 
	 */
	//상세보기]
	@RequestMapping("View.do")
	public String view(
			//@ModelAttribute("id") String id,
			Authentication auth,
			@RequestParam Map map,Model model) {
		//서비스 호출]
		OneMemoDTO record=memoService.selectOne(map);
		//데이타 저장]
		//줄바꿈 처리
		record.setContent(record.getContent().replace("\r\n","<br/>"));
		model.addAttribute("record", record);
		List<LineCommentDTO> comments=record.getComments();
		
		//뷰정보 반환]
		return "onememo10/bbs/View.tiles";
	}
	//삭제처리]
	@RequestMapping("Delete.do")
	public String delete(@RequestParam Map map,Authentication auth) {
		//서비스 호출
		memoService.delete(map);
		//뷰정보 반환]
		return "forward:/OneMemo/BBS/List.do";
	}
	//수정폼으로 이동 및 수정처리]
	@RequestMapping("Edit.do")
	public String edit(HttpServletRequest req,@RequestParam Map map,Authentication auth) {
		if(req.getMethod().equals("GET")) {
			//서비스 호출]
			OneMemoDTO record=memoService.selectOne(map);
			//데이타 저장]
			req.setAttribute("record", record);
			//수정 폼으로 이동]
			return "onememo10/bbs/Edit.tiles";
		}
		//수정처리후 상세보기로 이동
		//서비스 호출
		memoService.update(map);
		//뷰로 포워드
		return "forward:/OneMemo/BBS/View.do";
		
		
	}/////////////////////
	
}
