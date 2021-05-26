package com.kosmo.springapp.onememo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kosmo.springapp.onememo.service.impl.LineCommentDAO;

@Service("commentService")
public class LineCommentServiceImple implements LineCommentService {

	//LineCommentDAO주입]
	@Resource(name="commentDao")
	private LineCommentDAO dao;
	
	
	@Override
	public List<Map> selectList(Map map) {		
		return dao.selectList(map);
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
