package com.kosmo.springapp.onememo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kosmo.springapp.onememo.service.ListPagingData;
import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;
import com.kosmo.springapp.onememo.service.PagingUtil;


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
	
	//리소스파일(onememo.properties)에서 읽어오기
	@Value("${PAGE_SIZE}")
	private int pageSize;
	@Value("${BLOCK_PAGE}")
	private int blockPage;

	@Override
	public ListPagingData<OneMemoDTO> selectList(Map map,HttpServletRequest req,int nowPage) {	
		
		//페이징을 위한 로직 시작]
		//전체 레코드수	
		int totalRecordCount=dao.getTotalRecord(map);		
		//전체 페이지수
		int totalPage =(int)Math.ceil((double)totalRecordCount/pageSize);		
		//시작 및 끝 ROWNUM구하기
		int start = (nowPage -1)*pageSize+1;
		int end = nowPage * pageSize;	
		//페이징을 위한 로직 끝]
		map.put("start", start);
		map.put("end", end);
		//글 전체 목록 얻기
		List lists=dao.selectList(map);		
		String pagingString=PagingUtil.pagingBootStrapStyle(totalRecordCount,pageSize, blockPage, nowPage,req.getContextPath()+"/OneMemo/BBS/List.do?");
		
		ListPagingData<OneMemoDTO> listPagingData = 
				ListPagingData.builder()
					.lists(lists)
					.nowPage(nowPage)
					.pageSize(pageSize)
					.pagingString(pagingString)
					.TotalRecordCount(totalRecordCount)
					.build();
		
		return listPagingData;
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

	@Override
	public String findNameById(String id) {		
		return dao.findNameById(id);
	}

}
