package com.kosmo.springapp;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.impl.OneMemoDAO;




/*
  
  POM.XML에 의존성 추가
  <dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-test</artifactId>
	<version>${org.springframework-version}</version>
  </dependency>
  및 JUNIT버전(4.7에서 4.12이상으로) 변경 변경
  
 JUnit라이브러리 추가
 프로젝트 마우스 우클릭->Build Path->Configue Build path
 -> Add Library에서 JUnit선택
 
클래스 생성:new ->other에서 JUNIT으로 검색
->Junit Test Case로 클래스 생성
name - 클래스명-테스트 대상클래스명뒤에 Test를 붙인다
class under test - 테스트 대상 클래스 브라우징
아래 테스트 클래스 실행-Run As->JUnit Test
 
※JUnit은 각각의 테스트가 서로 영향을 주지 않고 독립적으로 실행되는 것이 원칙
 그래서 보통 Test 메소드 마다 필요한 객체를 생성하여 단위 테스트를 한다 
 
 */



@RunWith(SpringJUnit4ClassRunner.class)//JUnit 테스트 환경을 위한 테스트용 스프링 컨테이너 생성
@ContextConfiguration(locations = {"classpath:test/root-context.xml","classpath:test/servlet-context.xml"})
@Transactional//설정파일에 트랜잭션 빈 설정. 디폴트가 rollback
public class OneMemoDAOTest {
	
	@Autowired
	private OneMemoDAO dao;
	
	/*모든 테스트 메소드가 실행되기 전에 딱 한번만 호출되는 메소드*/
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeClass");
	}
	/*모든 테스트 메소드가 끝나고(실행되고나서) 딱 한번만 호출되는 메소드*/
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterClass");		
	}
	/* 각 테스트 대상 메소드가 실행되기 전에 호출되는 메소드*/
	@Before
	public void setUp() throws Exception {
		System.out.println("@Before");
	}
	/* 각 테스트 대상 메소드가 실행된 후에 호출되는 메소드*/
	@After
	public void tearDown() throws Exception {
		System.out.println("@After");
	}
	/*@Test 어노테이션:아래 메소드는 테스트 대상이되는
    	메소드임을 컴파일러에게 알려주는  어노테이션
		테스트 메소드명: test대상메소드명*/
	@Test	
	public void testIsLogin() {
		/*단정문:예상치와 실제치가 같다고 단정을 짓는다
    	같으면 성공 틀리면 실패
   		예]assertEquals:가장 많이 쓰고 데이타가 같은지 비교
		 */
		Map map = new HashMap();
		map.put("id", "KIM");
		map.put("pwd", "1234");
		
		//assertEquals(true, dao.isLogin(map));
		assertTrue(dao.isLogin(map));
	}////////////////////
	@Test
	public void testSelectList() {
		Map map = new HashMap();
		map.put("start",1);
		map.put("end", 10);
		List<OneMemoDTO> lists = dao.selectList(map);
		//assertEquals(false, lists.isEmpty());
		assertFalse(lists.isEmpty());
	}
	@Test
	//@Rollback(value = false)//디폴트 true,@Commit과 같다
	public void testInsert() {
		Map map = new HashMap();
		map.put("title","단위 테스트입니다");
		map.put("content","입력 테스트 중입니다");
		map.put("id","KIM");
		assertEquals(1, dao.insert(map));
	}/////////////////
	//존재하지 않는 PK입력 - 에러가 발생해야 정상
	@Test(expected = Exception.class)	
	@Ignore/*아래 메소드는 테스트 대상이지만 테스트 안하겠다라는 의미*/
	public void testInsertError() {
		Map map = new HashMap();
		map.put("title","단위 테스트입니다");
		map.put("content","입력 테스트 중입니다");
		map.put("id","NOKEY");
		dao.insert(map);
	}/////////////////
	//객체의 주소 비교 단정문:assertSame()
	@Test
	public void testSame() {
		OneMemoDTO kosmo = OneMemoDTO.builder().name("코스모").build();
		OneMemoDTO 한소인 = OneMemoDTO.builder().name("한소인").build();
		System.out.println("=============================");
		System.out.println(kosmo + ":"+한소인);
		System.out.println("=============================");
		//assertSame(kosmo, 한소인);
		assertNotSame(kosmo, 한소인);
	}
	//배열에 저장된 데이타(값) 비교, 같으면 성공,다르면 실패
	@Test
	public void testArray() {
		int[] arr1 = {1,2,3,4,5};
		int[] arr2 = {1,2,3,4,5};
		assertArrayEquals(arr1, arr2);
	}
	

}
