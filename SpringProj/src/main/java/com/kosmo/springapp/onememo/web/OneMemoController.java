package com.kosmo.springapp.onememo.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;

@SessionAttributes("id")
@RequestMapping("/OneMemo/BBS/")
@Controller
public class OneMemoController {
	
	//서비스 주입]
	@Resource(name="memoService")
	private OneMemoService memoService;
	
	/* 로그인 하지 않고 각 컨트롤러 메소드 실행시 오류:@ModelAttribute("id") String id사용시 */
	@ExceptionHandler({HttpSessionRequiredException.class})
	public String notLogin(Model model) {
		model.addAttribute("NotMember", "로그인후 이용 바람....");
		//로그인이 안된경우 로그인 페이지로
		return "onememo10/member/Login";
	}
	
	//목록 처리]
	@RequestMapping("List.do")
	public String list(@ModelAttribute("id") String id,Model model) {//세션영역에서 id가져오기-isLogin.jsp파일 사용 불필요(단,에러처리는 해야함)
		//서비스 호출]
		List<OneMemoDTO> list= memoService.selectList(null);
		//데이타 저장]
		model.addAttribute("list", list);
		//뷰정보 반환]
		return "onememo10/bbs/List";
	}/////////
	//입력폼으로 이동]
	@RequestMapping(value = "Write.do",method = RequestMethod.GET)
	public String write(@ModelAttribute("id") String id) {
		//뷰정보 반환]
		return "onememo10/bbs/Write";
	}/////////////
	//입력처리]
	@RequestMapping(value = "Write.do",method = RequestMethod.POST)
	public String writeoK(@ModelAttribute("id") String id,@RequestParam Map map) {
		//서비스 호출]		
		map.put("id", id);//호출전 아이디 맵에 저장
		memoService.insert(map);		
		//뷰정보 반환]
		//뷰정보 반환:목록으로 이동
		return "forward:/OneMemo/BBS/List.do";
	}/////////////
}