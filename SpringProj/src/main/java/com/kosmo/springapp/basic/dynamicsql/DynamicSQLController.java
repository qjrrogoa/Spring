package com.kosmo.springapp.basic.dynamicsql;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/MyBatis")
@Controller
public class DynamicSQLController {
	
	public DynamicSQLController() {
		System.out.println("DynamicSQLController 컨트로러의 생성자");
	}
	
	@Autowired
	private DynamicSQLDAO dynamic;
	
	@RequestMapping("/If1.do")
	public String if1(Model model,@RequestParam Map map) {
		List list= dynamic.if1(map);
		model.addAttribute("message", "검색된 총 글 수:"+list.size());
		return "dynamicsql11/DynamicSQL";
	}
	
	@RequestMapping("/If2.do")
	public String if2(Model model,@RequestParam Map map) {
		List list= dynamic.if2(map);
		model.addAttribute("message", "검색된 총 글 수:"+list.size());
		return "dynamicsql11/DynamicSQL";
	}
	
	@RequestMapping("/choose.do")
	public String choose(Model model,@RequestParam Map map) {
		List list= dynamic.choose(map);
		model.addAttribute("message", "검색된 총 글 수:"+list.size());
		return "dynamicsql11/DynamicSQL";
	}
	
	@RequestMapping("/where.do")
	public String where(Model model,@RequestParam Map map) {
		List list= dynamic.where(map);
		model.addAttribute("message", "검색된 총 글 수:"+list.size());
		return "dynamicsql11/DynamicSQL";
	}
	
	@RequestMapping("/trim1.do")
	public String trim1(Model model,@RequestParam Map map) {
		List list= dynamic.trim1(map);
		model.addAttribute("message", "검색된 총 글 수:"+list.size());
		return "dynamicsql11/DynamicSQL";
	}
	
	@RequestMapping("/trim2.do")
	public String trim2(Model model,@RequestParam Map map) {
		
		if(!(map.get("title")==null && map.get("content")==null))
				model.addAttribute("message", "수정된 총 글 수:"+dynamic.trim2(map));
		else
			model.addAttribute("message","타이틀이나 내용 둘중 하나는 전송하세요");
		return "dynamicsql11/DynamicSQL";
	}
	@RequestMapping("/set.do")
	public String set(Model model,@RequestParam Map map) {
		
		if(!(map.get("title")==null && map.get("content")==null))
				model.addAttribute("message", "수정된 총 글 수:"+dynamic.set(map));
		else
			model.addAttribute("message","타이틀이나 내용 둘중 하나는 전송하세요");
		return "dynamicsql11/DynamicSQL";
	}
	@RequestMapping("/foreach.do")
	public String foreach(Model model) {
		
		List lists=Arrays.asList(1,3,100);
		//1.Map인 경우
		Map map = new HashMap();
		map.put("keyno",lists);		
		//List resultList=dynamic.foreach(map);
		
		//2.List인 경우
		List resultList=dynamic.foreach(lists);
		
		model.addAttribute("message","검색된 총 글 수:"+resultList.size());
		return "dynamicsql11/DynamicSQL";
	}
	
	@RequestMapping("/foreachExam.do")
	public String foreachExam(Model model,@RequestParam int[] email) {
		
		int affected=dynamic.foreachExam(email);
		model.addAttribute("message","삭제된 총 글 수:"+affected);
		return "dynamicsql11/DynamicSQL";
	}//////////////////////////////
}
