package com.kosmo.springapp.basic.mybatis;

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
		System.out.println("DynamicSQLController 컨트롤러의 생성자");
	}

	@Autowired
	private DynamicSQLDAO dynamic;
	
	@RequestMapping("/If1.do")
	public String if1(Model model,@RequestParam Map map) {
		
		List list = dynamic.if1(map);
		model.addAttribute("message","검색된 총 글 수 : "+list.size());
		return "dynamicsql11/DynamicSQL";
	}
}
