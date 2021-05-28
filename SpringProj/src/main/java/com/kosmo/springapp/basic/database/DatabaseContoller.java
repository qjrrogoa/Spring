package com.kosmo.springapp.basic.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//1] pom.xml에 스프링 JDBC 라이브러리 추가 (메이븐 리포지토리에서 검색)
//2] DataSource를 얻기 위한 빈 등록은 root-context.xml에서 등록한다.
//(각각 디스패처 서블릿에서 공통으로 빈을 사용하기 위함)
@Controller
public class DatabaseContoller {

	//빈에서 등록한 datasourceByxxx라는 이름으로 세터 인젝션을 통해서 데이터 소스 자동 주입
	@Resource(name = "datasourceByJDBC")
	private DataSource jdbc;
	@Resource(name = "datasourceByJNDI")
	private DataSource jndi;
	
	
	@RequestMapping("/Database/JDBConnection.do")
	public String jdbc(@RequestParam String method, Model model) throws SQLException {
		
		//주입받은 DataSource 객체로 Connection 객체 얻기
		Connection conn = jdbc.getConnection();
		
		//데이터 저장
		model.addAttribute("message", conn==null?"[데이터베이스 연결 실패]":"[JDBC 커넥션 성공]");
		//커넥션 객체 메모리 해제
		if(conn!=null) conn.close();
		
		//뷰정보 반환
		return "database07/Database";
	}
	
	//커넥션 풀 사용
	@RequestMapping("/Database/JNDIConnection.do")
	public String jndi(@RequestParam String method, Model model) throws SQLException {
		
		//주입받은 DataSource 객체로 Connection 객체 얻기
		Connection conn = jndi.getConnection();
		
		//데이터 저장
		model.addAttribute("message", conn==null?"[데이터베이스 연결 실패]":"[JDBC 커넥션 성공]");
		//커넥션 객체 반납
		if(conn!=null) conn.close();
		
		//뷰정보 반환
		return "database07/Database";
	}
	
}/////DatabaseContoller