package com.kosmo.springapp.basic.dynamicsql;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DynamicSQLDAO {

	public DynamicSQLDAO() {
		System.out.println("DynamicSQLDAO의 생성자");
	}
	@Resource(name="template")
	private SqlSessionTemplate sqlMapper;
	
	public List if1(Map map) {
		return sqlMapper.selectList("findOneMemoWithTitleLike",map);
	}

	public List if2(Map map) {
		return sqlMapper.selectList("findOneMemoLike",map);
	}

	public List choose(Map map) {
		return sqlMapper.selectList("findOneMemoLikeChoose",map);
	}

	public List where(Map map) {
		return sqlMapper.selectList("findOneMemoLikeWhere",map);
	}

	public List trim1(Map map) {
		return sqlMapper.selectList("findOneMemoLikeTrim",map);
	}

	public int trim2(Map map) {		
		return sqlMapper.update("updateOneMemoLikeTrim",map);
	}

	public int set(Map map) {
		return sqlMapper.update("updateOneMemoSet",map);
	}
	/*
	 * Map사용시
	public List foreach(Map map) {//"keyno" :키값, no가 저장된 리스트:밸류
		
		return sqlMapper.selectList("findOneMemoForeach",map);
	}*/
	public List foreach(List list) {
		
		return sqlMapper.selectList("findOneMemoForeach",list);
	}

	public int foreachExam(int[] email) {
		return	sqlMapper.delete("deleteEmail",email);	
	}
}
