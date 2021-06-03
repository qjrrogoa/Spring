package com.kosmo.springapp;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.impl.OneMemoDAO;

import jdk.nashorn.internal.ir.annotations.Ignore;

@RunWith(SpringJUnit4ClassRunner.class) //JUnit 테스트 환경을 위한 테스트용 스프링 컨테이너 생성
@ContextConfiguration(locations= {"classpath:test/root-context.xml","classpath:test/servlet-context.xml"})
@Transactional//설정파일에 트랜잭션 빈 설정. 디폴트가 rollback
public class OneMemoDAOTest {
	
	@Autowired
	private OneMemoDAO dao;
	
	/*모든 테스트 메서드가 실행되기 전에 딱 한번만 호출되는 메서드*/
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeClass");
	}

	/*모든 테스트 메서드가 끝나고(실행되고나서) 딱 한번만 호출되는 메서드*/
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterClass");
	}

	/*각 테스트 대상 메서드가 실해오디기 전에 호출되는 메서드*/
	@Before
	public void setUp() throws Exception {
		System.out.println("@Before");
		
	}
	/* 각 테스트 대상 메서드가 실행된 후에 호추로디는 메서드 */
	@After
	public void tearDown() throws Exception {
		System.out.println("@After");

	}
	/* @Test 어노테이션 : 아래 메서드는 테스트 대상이 되는 메서드임을 컴파일러에게 알려주는 어노테이션 */
	
	@Test
	public void testIsLogin() {
		/* 단정문 : 예상치와 실제치가 같다고 단정을 짓는다 같으면 성공 틀리면 실패
		 * assertEquals:가장 많이 쓰고 데이터가 같은지 비
		 */
		Map map = new HashMap();
		map.put("id","KIM");
		map.put("pwd", "1234");
		assertEquals(true, dao.isLogin(map));
		assertTrue(dao.isLogin(map));
	}//////////
	
	@Test
	public void testSelectList() {
		Map map = new HashMap();
		map.put("start",1);
		map.put("end", 10);
		List<OneMemoDTO> lists = dao.selectList(map);
		//assertEquals(false, lists.isEmpty()); 
		assertFalse(lists.isEmpty());
	}/////////
	
	@Test
	//@Commit
	//@Rollback(value=false) // 디폴트가 true, (value=false)는 @Commit과 같다
	public void testInsert() {
		Map map = new HashMap();
		map.put("title","단위 테스트입니다.");
		map.put("content", "내용입니다.");
		map.put("id", "KIM");
		assertEquals(1, dao.insert(map));
	}///////////
	
	//존재하지 않는 PK입력 - 에러가 발생해야 정상
	@Test(expected = Exception.class)
	@Ignore/*아래 메서드는 테스트 대상이지만 테스트 안하겠다라는 의미*/
	public void testInsertError() {
		Map map = new HashMap();
		map.put("title","단위 테스트입니다.");
		map.put("content", "내용입니다.");
		map.put("id", "NOKEY");
		dao.insert(map);
	}//////////
	
	//객체의 주소 비교 단정문 : assertSame()
	@Test
	public void testSame() {
		OneMemoDTO kosmo = OneMemoDTO.builder().name("코스모").build();
		OneMemoDTO 한소인 = OneMemoDTO.builder().name("한소인").build();
		System.out.println(kosmo + ":" + 한소인);
		assertSame(kosmo,한소인);
		assertNotSame(kosmo, 한소인);
	}
	
	//배열에 저장된 데이터(값) 비교, 같으면 성공, 다르면 실패
	@Test
	public void testArray() {
		int[] arr1 = {1,2,3,4,5};
		int[] arr2 = {1,2,3,4,5};
		assertArrayEquals(arr1,arr2);
	}
}
