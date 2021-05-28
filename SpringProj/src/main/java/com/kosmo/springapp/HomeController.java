package com.kosmo.springapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosmo.springapp.onememo.service.OneMemoService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public HomeController() {
		System.out.println("HomeController의 생성자");
	}
	
	@Resource(name="memoservice")
	private OneMemoService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		Map map = new HashMap();
		map.put("id", "KIM");
		map.put("pwd", "1234");
		System.out.println("회원이면 true, 아니면 false가 반환됩니다 ☞ "+service.isLogin(map));
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//데이터 저장
		model.addAttribute("serverTime", formattedDate );
		
		//뷰정보 반환
		return "home";
	}
}
