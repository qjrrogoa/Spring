package com.kosmo.springapp.onememo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kosmo.springapp.onememo.service.LineCommentService;

@Repository("commentDao")
public class LineCommentDAO implements LineCommentService {
	
	//SqlSessionTemplate객체 주입]
	@Resource(name="template")
	private SqlSessionTemplate sqlMapper;
	

	@Override
	public List<Map> selectList(Map map) {		
		return sqlMapper.selectList("commentSelectList", map);
	}

	@Override
	public int insert(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
