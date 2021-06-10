package com.kosmo.springapp.onememo.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


//@SessionAttributes({"id"})//씨큐리티 사용시 주석
@RestController
public class CommentController {
	//서비스 주입]
	@Resource(name="commentService")
	private  LineCommentService commentService;
	
	
	
	
	/*
	 * @RequestParam : key - value쌍으로 받을때
	 * 					1.form으로 전송시
	 *                 <form action="my.do" enctype="x-www-form-urlencoded">
	 *                 	<input type="text" name="age"/>
	 *                 </form>
	 *                
	 * 					key : age ,value : 사용자 입력값
	 * 					전송방식 즉 post 혹은 get상관없다
	 * 					 2.쿼리 스트링으로 전송시
	 * 	                <a href="my.do?age=30">클릭</a>
	 * 					key : age ,value : 30
	 * 
	 * @RequestBody : JSON으로 받을때 즉 자바스크립트 객체로 받을떼 사용
	 * 	
	 * produces = "text/plain;charset=UTF-8"은 응답바디에 쓰여진다
	 * Content-type:text/plain;charset=UTF-8
	 * 
	 * ※@RequestParam Map map으로 받은때는 요청을보낼때 JSON이 아니라 key=value형태로 보내야 한다
	 */
	
	@Autowired
	private ObjectMapper mapper;
	
	@GetMapping(value="/OneMemo/Comment/List.do",produces = "text/plain;charset=UTF-8")	
	public String list(
			//@ModelAttribute("id") String id, 
			Authentication auth,
			@RequestParam Map map) throws JsonProcessingException {
		//서비스 호출]
		List<Map> list=commentService.selectList(map);
		//System.out.println("데이타베이스에서 조회:"+list.get(0).get("LPOSTDATE"));
		
		//JACKSON이 List<Map>을 JSON형태 문자열로 변경시
		//날짜데이타를 2021-05-25 17:52:32.0에서 1621932752000로 변경해버린다
		for(Map comment:list) {
			comment.put("LPOSTDATE",comment.get("LPOSTDATE").toString().substring(0, 10) );
		}
		
		String comments=mapper.writeValueAsString(list);
		//System.out.println("글번호에 따른 댓글들:"+comments);
		//[
		//{"NO":2,"LPOSTDATE":1621932752000,"LINECOMMENT":"COMMENT2","ID":"LEE","LNO":2,"NAME":"이길동"},
		//{"NO":2,"LPOSTDATE":1621932741000,"LINECOMMENT":"COMMENT1","ID":"PARK","LNO":1,"NAME":"박길동"}
		//]
		//위를 아래처럼 변경
		//[{"NO":2,"LPOSTDATE":"2021-05-25","LINECOMMENT":"COMMENT2","ID":"LEE","LNO":2,"NAME":"이길동"},{"NO":2,"LPOSTDATE":"2021-05-25","LINECOMMENT":"COMMENT1","ID":"PARK","LNO":1,"NAME":"박길동"}]
		return comments;
	}
	//코멘트 입력처리]
	@PostMapping(value="/OneMemo/Comment/Write.do",produces = "text/plain;charset=UTF-8")
	public String write(
			//@ModelAttribute("id") String id,
			Authentication auth,
			@RequestParam Map map) {
		//map.put("id", id);//(씨큐리티 미 사용시)한줄 댓글 작성자의 아이디를 맵에 설정
		map.put("id", ((UserDetails)auth.getPrincipal()).getUsername());
		String name=commentService.insert(map);		
		
		return name;//댓글 작성자 이름 반환
	}////////////////////
	//코멘트 수정처리]
	@PostMapping(value="/OneMemo/Comment/Edit.do",produces = "text/plain;charset=UTF-8")
	public String update(
			//@ModelAttribute("id") String id,
			Authentication auth,
			@RequestParam Map map) {
		System.out.println("댓글의 키값:"+map.get("lno"));
		System.out.println("CommentController:"+map.get("lno"));
		commentService.update(map);
		//수정한 글의 키값 반환
		return map.get("lno").toString();
	}
	
	@PostMapping(value="/OneMemo/Comment/Delete.do",produces = "text/plain;charset=UTF-8")
	public String delete(
			//@ModelAttribute("id") String id,
			Authentication auth,
			@RequestParam  Map map) {		
		System.out.println("삭제할 키:"+map.get("lno"));
		commentService.delete(map);	
		return "삭제 성공";
	}
}////////////////
