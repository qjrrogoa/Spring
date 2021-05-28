package com.kosmo.springapp.onememo.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.springapp.onememo.service.LineCommentService;
import com.kosmo.springapp.onememo.service.OneMemoService;

@SessionAttributes({"id"})
@RestController
//@RestController를 사용하면 컨트롤러 메소드에 @ResponseBody 어노테이션을 붙이지 않아도됨
public class CommentController {

	//서비스 주입
	@Resource(name="commentService")
	private LineCommentService commentService;
	
//	@Resource(name="memoservice")
//	private OneMemoService memoService;
	
	/*
		@RequestParam : key와 value의 쌍으로 데이터를 받을때 사용한다.
		
		1. 폼으로 전송시
			<form action="my.do" enctype="x-www-form-urlencoded">
				<input type="text" name="age"/>
			</form>
			key : value = age : "사용자가 입력한 값"
			이때, 전송방식은 상관없다. (POST, GET)
		2. 쿼리스트링으로 전송시
			<a href="my.do?age=30">클릭시 전송</a>
			key : value = age : 30
			a태그는 GET방식으로 전송된다.
			
		@RequestBody : JSON으로 자바스크립트 객체를 받을때 사용한다.
		produces = "text/plain; charset=UTF-8"은 응답바디에 쓰여진다.
		Content-Type : "text/plain;charset=UTF-8"
		
		※그러므로 @RequestParam Map map으로 받은경우 요청을 보낼때는 JSON이 아니라,
		  KEY=VALUE의 형태로 보내야한다.
		
	*/
	@Autowired
	private ObjectMapper mapper;
	
	@GetMapping(value="/OneMemo/Comment/List.do", produces = "text/plain; charset=UTF-8")
	//Jackson이 컨버터 역할을 하기때문에 DTO클래스를 만들필요가 없음
	public String list(@ModelAttribute("id") String id, @RequestParam Map map) throws JsonProcessingException {
		System.out.println("Java Script 객체를 Map으로 받기 : "+(map != null ? map.get("no"):"맵으로 받을수없음"));
		
		//서비스 호출
		List<Map> list = commentService.selectList(map);
		System.out.println("데이터베이스에서 조회 : "+list.get(0).get("LPOSTDATE"));
		//Jackson 라이브러리를 거치면 list<Map>에 저장된 날짜 데이터(문자열)가 JSON 형식으로 변형됨
		//2021-05-25 17:53:05.0 → "LPOSTDATE":1621932773000
		System.out.println("댓글의 수 : "+list.size());
		for(Map comment : list) {
			comment.put("LPOSTDATE", comment.get("LPOSTDATE").toString().subSequence(0, 10));
		}
		String comments = mapper.writeValueAsString(list);
		System.out.println("글번호에 따른 댓글들 : "+comments);
		//[{"NO":2,"LPOSTDATE":1621932785000,"LINECOMMENT":"COMMENT2","ID":"LEE","LNO":3,"NAME":"이길동"},
		//{"NO":2,"LPOSTDATE":1621932773000,"LINECOMMENT":"COMMENT1","ID":"PARK","LNO":2,"NAME":"박길동"}]
		return comments;
	}
	
	//코멘트 입력처리
	@PostMapping(value="/OneMemo/Comment/Write.do",produces = "text/plain; charset=UTF-8")
	public String wirte(@ModelAttribute("id") String id, @RequestParam Map map) {
		map.put("id", id); //(씨큐리티 미 사용시) 한줄 댓글 작성자의 아이디를 맵에 설정
		System.out.println("입력된 댓글의 키값 : "+map.get("lno"));
		String name = commentService.insert(map);
		return name; //댓글 작성자 이름 반환
	}
	
	//코멘트 수정처리
	@PostMapping(value="/OneMemo/Comment/Edit.do",produces = "text/plain; charset=UTF-8")
	public String update(@ModelAttribute("id") String id, @RequestParam Map map) {
		//commentService.update(map)==1? "수정성공":"수정 실패";
		commentService.update(map);
		//수정한 글의 키값 반환
		System.out.println("수정된 댓글의 키값 : "+map.get("lno"));
		return map.get("lno").toString();
	}
	
	//코멘트 삭제처리
	@PostMapping(value="/OneMemo/Comment/Delete.do",produces = "text/plain; charset=UTF-8")
	public String delete(@ModelAttribute("id") String id, @RequestParam Map map) {
		commentService.delete(map);
		//수정한 글의 키값 반환
		System.out.println("삭제된 댓글의 키값 : "+map.get("lno"));
		return "삭제한 댓글의 키값 : "+map.get("lno").toString();
	}
	
}///////////