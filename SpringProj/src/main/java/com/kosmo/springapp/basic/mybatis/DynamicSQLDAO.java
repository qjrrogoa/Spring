package com.kosmo.springapp.basic.mybatis;

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
}
