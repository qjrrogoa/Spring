package com.kosmo.springapp.onememo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;


/*
 * 이름 미 지정시 ID값은 소문자로 시작하는 클래스명이됨
 * 예]OneMemoServiceImpl클래인 경우 
 * ID값은 oneMemoServiceImpl
 * 지정도 가능 @Service("임의의ID값")
 */

@Service("memoService")
public class OneMemoServiceImpl implements OneMemoService {
	
	


	//OneMemoDAO주입]	
	@Resource(name="oneMemoDAO")
	private  OneMemoDAO dao;
	
	@Override
	public boolean isLogin(Map map) {
		
		return dao.isLogin(map);
	}

	@Override
	public List<OneMemoDTO> selectList(Map map) {			
		return dao.selectList(map);
	}

	@Override
	public int getTotalRecord(Map map) {		
		return dao.getTotalRecord(map);
	}
	@Override
	public OneMemoDTO selectOne(Map map) {		
		return dao.selectOne(map);
	}

	@Override
	public int insert(Map map) {		
		return dao.insert(map);
	}

	@Override
	public int delete(Map map) {		
		return dao.delete(map);
	}

	@Override
	public int update(Map map) {		
		return dao.update(map);
	}

}
