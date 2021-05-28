package com.kosmo.springapp.onememo.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kosmo.springapp.onememo.service.LineCommentDTO;
import com.kosmo.springapp.onememo.service.ListPagingData;
import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;

@SessionAttributes("id")
@RequestMapping("/OneMemo/BBS/")
@Controller
public class OneMemoController {
	
	//서비스 주입
	@Resource(name="memoservice")
	private OneMemoService memoservice;
	
	@ExceptionHandler({HttpSessionRequiredException.class})
	public String notLogin(Model model) {
		//세션영역에 ID가 없으면 예외가 발생하는데, 그것을 처리하기위한 메소드
		//이 메소드가 있으면 IsLogin.jsp를 인클루드할 필요가 없음. 단, 에러처리는 해야함
		model.addAttribute("NotMember", "로그인후 이용 바랍니다.");
		//뷰정보 반환
		return "onememo10/member/Login";
	}
	
	//목록 처리]
	@RequestMapping("List.do")
	public String list(@ModelAttribute("id") String id,
			@RequestParam Map map,
			@RequestParam(required = false, defaultValue = "1") int nowPage,
			HttpServletRequest req,
			Model model) {//세션영역에서 id가져오기
		System.out.println("id : "+id);
		//서비스 호출]
		ListPagingData listPagingData = memoservice.selectList(map,req,nowPage);
		
		//데이터 저장
		model.addAttribute("listPagingData",listPagingData);
		
		//뷰정보 반환
		return "onememo10/bbs/List";
	}
	
	//입력폼으로 이동]
	@RequestMapping(value = "Write.do",method = RequestMethod.GET)
	public String write(@ModelAttribute("id") String id) {
		//뷰정보 반환]
		return "onememo10/bbs/Write";
	}/////////////
	
	//입력처리]
	@RequestMapping(value = "Write.do",method = RequestMethod.POST)
	public String write(@ModelAttribute("id") String id, @RequestParam Map map) {
		//서비스 호출]
		map.put("id",id);//호출전 아이디를 Map에 저장
		memoservice.insert(map);
		
		//뷰정보 반환 : 목록으로 이동
		return "forward:/OneMemo/BBS/List.do";
	}
		
/*
	컨트롤러 메소드의 작성 규칙
	접근지정자 : public
	반환타입 : 주로 String(뷰정보를 문자열로 반환)
	메소드명 : 임의
	인자 : 원하는 타입 및 어노테이션을 사용할 수 있다. (모든 타입이 가능한것은 아님)	*/
	//상세보기
	@RequestMapping("View.do")
	public String view(@RequestParam Map map, Model model, @ModelAttribute("id") String id) {
		//서비스 호출]
		OneMemoDTO record = memoservice.selectOne(map);
		
		//데이터 저장]
		record.setContent(record.getContent().replace("\r\n", "<br/>"));//줄바꿈 처리
		model.addAttribute("record",record);
//		List<LineCommentDTO> coments = record.getComments();
		//댓글이 없는 게시글을 클릭하면 오류가 발생함
//		System.out.println("댓글 작성자 : "+coments.get(0).getName());
		//뷰정보 반환 : 상세페이지로 이동
		return "onememo10/bbs/View";
	}
	//삭제 처리]
	@RequestMapping("Delete.do")
	public String delete(@RequestParam Map map) {
		System.out.println("id : "+map.get("no"));
		//서비스 호출]
		memoservice.delete(map);
		
		//뷰정보 반환
		return "forward:/OneMemo/BBS/List.do";
	}
	
	//수정창
	@RequestMapping("Edit.do")
	public String edit(@RequestParam Map map, @ModelAttribute("id") String id, HttpServletRequest req) {
		if(req.getMethod().equals("GET")) { //수정폼으로 이동
			//서비스 호출]
			OneMemoDTO record = memoservice.selectOne(map);
			//데이터 저장]
			req.setAttribute("record",record);
			//뷰정보 반환 : 상세페이지로 이동
			return "onememo10/bbs/Edit";
		} else { //수정완료후 상세보기로 이동
			System.out.println("수정된 글번호 : "+(String) map.get("no"));
			//서비스 호출]
			memoservice.update(map);
			//뷰정보 반환 (map에 이미 글번호가 저장되어 있으므로 따로 넘길필요없음)
			return "forward:/OneMemo/BBS/View.do";
		}
	}////

}////OneMemoController