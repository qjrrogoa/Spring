package com.kosmo.springapp.onememo.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.springapp.onememo.service.LineCommentService;
import com.kosmo.springapp.onememo.service.TestDTO;

@SessionAttributes({"id"})
@Controller
@RequestMapping("/OneMemo/Comment/")
public class CommentController {
	//서비스 주입]
	@Resource(name="commentService")
	private LineCommentService commentService;
	
	@RequestMapping(value="List.do",produces = "text/plain;charset=UTF-8")
	public @ResponseBody String list(@RequestParam Map map) {
		//서비스 호출]
		List<Map> list=commentService.selectList(map);
		
		System.out.println(list.size());
		return "{\"name\":\"코스모\"}";
	}
	
	
}////////////////
