package com.kosmo.springapp.onememo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kosmo.springapp.onememo.service.LineCommentService;

@Service("commentService")
public class LineCommentServiceImpl implements LineCommentService {

	//LineCommentDAO주입]
	@Resource(name="commentDao")
	private LineCommentDAO dao;
	
	@Override
	public List<Map> selectList(Map map) {
		return dao.selectList(map);
	}

	@Override
	public String insert(Map map) {
		//영향받은 행의 수(affected)가 아니라, 새로 입력된 record의 key(lno)를 반환받는다.
		int lno = dao.insert(map);
		String name = dao.findNameById(map.get("id").toString());
		//콜론(:)으로 구분자를 둬서 받는쪽에서 split해서 앞은 이름, 뒤는 key로 사용한다.
		return name+":"+lno;
	}

	@Override
	public int delete(Map map) {
		return dao.delete(map);
	}

	@Override
	public int update(Map map) {
		return dao.update(map);
	}

}/////