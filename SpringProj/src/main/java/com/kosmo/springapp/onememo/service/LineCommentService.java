package com.kosmo.springapp.onememo.service;

import java.util.List;
import java.util.Map;

public interface LineCommentService {
	//목록]
	//record를 DTO가 아닌 Map에 담는다.
	List<Map> selectList(Map map);
	
	//입력, 수정, 삭제]
	String insert(Map map);
	int delete(Map map);
	int update(Map map);
	
}/////