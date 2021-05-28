package com.kosmo.springapp.onememo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kosmo.springapp.onememo.service.LineCommentService;

@Repository("commentDao")
public class LineCommentDAO{

	//SqlSessionTemplate 객체 주입]
	@Resource(name="template")
	private SqlSessionTemplate sqlMapper;
	
	public List<Map> selectList(Map map) {
		return sqlMapper.selectList("commentSelectList",map);
	}

	public int insert(Map map) {
		//Insert는 영향받은 행의 수를 반환하도록 되어 있음
		sqlMapper.insert("commentInsert", map);
		//Map에 채워진 lno키의 값을 반환함
		//※ 원래 입력할때는 lno값이 없지만 생성과 동시에 셀렉트를 해서 바로 map에 넣은것임
		return Integer.parseInt(map.get("lno").toString());
	}
	
	public String findNameById(String id) {
		return sqlMapper.selectOne("memoFindNameById", id);
	}
	
	public int delete(Map map) {
		return sqlMapper.delete("commentDelete",map);
	}

	public int update(Map map) {
		System.out.println("lno : "+map.get("lno")+",linecomment : "+map.get("linecomment"));
		return sqlMapper.update("commentUpdate", map);
	}

	
	
}
