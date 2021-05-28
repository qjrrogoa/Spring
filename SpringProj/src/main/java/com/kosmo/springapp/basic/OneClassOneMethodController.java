package com.kosmo.springapp.basic;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//컨트롤러 클래스]
@Controller
public class OneClassOneMethodController {
	
	//String[] 형식으로 여러개를 매핑
	@RequestMapping({
					"/Controller/OneMethod/List.do",
					"/Controller/OneMethod/Edit.do",
					"/Controller/OneMethod/Delete.do",
					"/Controller/OneMethod/View.do"
					})
	public String parameter(@RequestParam int paramvar, Map map) {
		//데이터 저장
		switch (paramvar) {
		case 1:map.put("message", "[목록 요청입니다.]");
			break;
		case 2:map.put("message", "[수정 요청입니다.]");
			break;
		case 3:map.put("message", "[삭제 요청입니다.]");
			break;
		default:map.put("message", "[상세보기 요청입니다.]");
			break;
		}
		//디스패처서블릿에게 뷰 정보 반환
		return "controller02/controller";
	}////parameter
	
	//매핑주소의 {}이름과 @PathVariable의 변수명과 동일하게 해주어야함
	@RequestMapping("/Controller/OneMethodNoparam/{path}")
	public String noparameter(@PathVariable String path, Map map) {
		//확장자(.do)를 제외한 이름만 남게됨
		System.out.println(path);
		
		//데이터 저장
		switch (path.toUpperCase()) {
		case "LIST":map.put("message", "[목록 요청입니다.]");
			break;
		case "EDIT":map.put("message", "[수정 요청입니다.]");
			break;
		case "DELETE":map.put("message", "[삭제 요청입니다.]");
			break;
		default:map.put("message", "[상세보기 요청입니다.]");
			break;
		}
		//디스패처서블릿에게 뷰 정보 반환
		return "controller02/controller";
	}
	
}/////OneClassOneMethodController