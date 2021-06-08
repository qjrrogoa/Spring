package com.kosmo.springapp.basic.ajax;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.springapp.onememo.service.ListPagingData;
import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;
/*
 * 	사전준비:POM.XML에 jackson-databind라이브러리 의존성 추가
 * 
	[Jackson]
	Java 객체(Map혹은 DTO)를 JSON으로 변환하거나 
	JSON을 Java 객체(Map혹은 DTO)로 변환하는 라이브러리
	List계열 컬렉션 즉 List<Map> 혹은 List<DTO>는
	자바스크립트로 변환시 JSON배열로 변환된다
	예] [ {key1:value2,key2:value2,},{},{},{}...]
	
	Spring 3.0 이후로부터, Jacskon과 관련된 API를 제공
	Jackson라이브러리를 사용하면 자동화 처리 가능
	
	Jackson라이브러리 POM.XML에 설정시
	MessageConverter API인 MappingJacksonHttpMessageConverter API가
	작동하여 컨트롤러가 리턴하는 객체를 후킹하여 ObjectMapper API로 JSON
	형태의 문자열로 자동으로 변환하여 반환한다.	
	1.자바객체를 JSON으로 변환시
	writeValue(File객체,DTO혹은 MAP) :객체를 JSON파일(.json)로 변환	
	writeValueAsString(DTO혹은 MAP):객체를 JSON형식의 문자열로 변환
	writerWithDefaultPrettyPrinter().writeValueAsString(DTO혹은 MAP)
	2.JSON을 자바객체로 변환시
	readValue(File객체,DTO혹은 MAP):JSON파일(.json)에 있는 내용을 자바객체로 읽어들일때
	readValue(JSON형식 문자열,DTO혹은 MAP):JSON형식의 문자열을 자바객체로 읽어 들일때
 */
@Controller
public class AjaxController {
	
	@Autowired
	private OneMemoService service;
	//[ajax 미 사용]-새로고침이 됨.
	/* 반환타입 void:직접 웹브라우저에 출력 스트림으로 결과값 출력
	*  반환타입 String:디스패처서블릿에게 뷰 반환, 결과값은 모델에 저장(JSP로 포워드)
	*/
//	@RequestMapping("/Ajax/NoAjax.do")
//	public void noajax(@RequestParam Map map,HttpServletResponse resp) throws IOException {
//		//1]컨텐츠 타입 설정
//		resp.setContentType("text/html; charset=UTF-8");
//		//2]웹브라우저에 출력하기위한 출력스트림 얻기
//		PrintWriter out= resp.getWriter();
//		//3]비지니스 로직 호출
//		boolean isLogin=service.isLogin(map);
//		if(isLogin)
//			out.println("<h2>"+map.get("id")+"님 즐감하세요</h2>");
//		else {
//			out.println("<script>");
//			out.println("alert('아뒤와 비번이 틀려요');");
//			out.println("history.back();");
//			out.println("</script>");
//		}
//		//4]웹브라우저와 연결된 스트림 닫기
//		out.close();
//	}////////////
	@RequestMapping("/Ajax/NoAjax.do")
	public String noajax(@RequestParam Map map,Model model) throws JsonProcessingException {
		//1]서비스 호출
		boolean isLogin=service.isLogin(map);
		//2]데이타 저장
		model.addAttribute("isLogin", isLogin? map.get("id")+"님 반갑습니다":"회원정보 불일치");
		
		
		//3]뷰 정보 반환
		return "ajax12/Ajax";
	}////////////////
	//[AJAX 사용]-페이지를 전송하지 않고 데이타만 전송
	//[TEXT로 응답할때]	
	/*
	 * 반환타입은 void이거나 
	 * 반환타입이 String인 경우는 @ResponseBody()어노테이션 사용
	 */
//	@RequestMapping("/Ajax/AjaxText.do")
//	public void ajaxText(@RequestParam Map map,HttpServletResponse resp) throws IOException {
//		//1]컨텐츠 타입 설정
//		resp.setContentType("text/html; charset=UTF-8");
//		//2]웹브라우저에 출력하기위한 출력스트림 얻기
//		PrintWriter out= resp.getWriter();
//		//3]비지니스 로직 호출
//		boolean isLogin=service.isLogin(map);
//		//Y 혹은 N 값으로 응답할때-반드시 print()메소드로 안그러면 println은 줄바꿈이 추가됨
//		//out.print(isLogin?"Y":"N");	
//		//메시지로 응답할때
//		out.print(isLogin?map.get("id")+"님 즐감!!":"회원 가입해...");	
//		//4]웹브라우저와 연결된 스트림 닫기
//		out.close();
//	}
	@RequestMapping(value="/Ajax/AjaxText.do",produces = "text/plain;charset=UTF-8")	
	public @ResponseBody String ajaxText(@RequestParam Map map) {
		//1]서비스 호출
		boolean isLogin=service.isLogin(map);
		//Y 혹은 N 값으로 응답할때
		//return isLogin?"Y":"N";
		//메시지로 응답할때
		return isLogin ?map.get("id")+"님 즐감하삼!!":"회원가입 하삼";
	}
	@Autowired
	private ObjectMapper objectMapper;
	//[JSON으로 응답할때]
	@RequestMapping(value="/Ajax/AjaxJson.do",produces = "application/json;charset=UTF-8")
	//public @ResponseBody String ajaxJson(@RequestParam Map map) throws JsonProcessingException {
	public @ResponseBody Map ajaxJson(@RequestParam Map map) throws JsonProcessingException {
		//1]서비스 호출
		boolean isLogin=service.isLogin(map);
		//Jackson의 ObjectMapper의 writeValueAsString()메소드로
		//Map을 JSON형태의 스트링으로 변경:{"id":"KIM","pwd":"9999"}
		//String jsonString=objectMapper.writeValueAsString(map);
		//반환 : {"isLogin":"방가방가"} 혹은 {"isLogin":"다음기회에"}
		//return String.format("{\"isLogin\":\"%s\"}", isLogin?"방가방가":"다음기회에");
		Map resultMap = new HashMap();
		resultMap.put("isLogin", isLogin?"방가방가":"다음기회에");
		//return objectMapper.writeValueAsString(resultMap);
		return resultMap;
	}////////////
	
	@RequestMapping(value="/Ajax/AjaxJsonArray.do",produces = "application/json;charset=UTF-8")
	public @ResponseBody String ajaxJsonArray(HttpServletRequest req) throws JsonProcessingException {
		//1]서비스 호출
		Map map = new HashMap();
		map.put("start", 1);
		map.put("end", 10);
		ListPagingData<OneMemoDTO> datas= service.selectList(map, req, 1);
		List<OneMemoDTO> list= datas.getLists();
		
		//[{"no":"21","title":"제목입니다","content":"내용입니다","postDate":1622559600000,"id":"PARK","name":"박길동","commentCount":"0","comments":null},{"no":"20","title":"제목입니다","content":"내용입니다","postDate":1622559600000,"id":"PARK","name":"박길동","commentCount":"0","comments":null},{"no":"19","title":"제목입니다","content":"내용입니다","postDate":1622559600000,"id":"PARK","name":"박길동","commentCount":"0","comments":null}]

		String jsonString=objectMapper.writeValueAsString(list);
		System.out.println(jsonString);
		return jsonString;
	}
	
	@RequestMapping(value="/Ajax/AjaxCourse.do",produces = "application/json;charset=UTF-8")
	public @ResponseBody String ajaxCourse(@RequestParam String course) throws JsonProcessingException {
		
		Map map = new HashMap();
		switch(course) {
			case "dotnet":
					map.put("d01", "C#");
					map.put("d02", "ASP.NET");
					map.put("d03", "WPF4");
				break;
			case "java":
				map.put("j01", "자바");
				map.put("j02", "JSP");
				map.put("j03", "SPRING");
				break;
			default:
				map.put("i01", "라즈베리 파이");
				map.put("i02", "파이썬");
		}
		
		return objectMapper.writeValueAsString(map);
	}/////////////////
	
	//1.자바객체를 JSON으로 변환시
	//[KEY=VALUE쌍으로 데이타 받기]
	@RequestMapping("/Ajax/form.do")
	public String form(OneMemoDTO dto,Model model) throws JsonGenerationException, JsonMappingException, IOException {
		//[자바객체를 JSON으로 변환]
		//1.JSON파일로 저장
		objectMapper.writeValue(new File("onememo.json"),dto);

		//2.JSON형태의 문자열로 변환
		//String jsonString=objectMapper.writeValueAsString(dto);		
		String jsonString=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
		System.out.println(jsonString);
		model.addAttribute("formRequestResult", jsonString);
		return "ajax12/Ajax";
	}//////////////
	
	@RequestMapping("/Ajax/ajaxRequest.do")
	//자바빈 반환하는 경우
	/*
	public @ResponseBody OneMemoDTO ajaxRequest(OneMemoDTO dto) {
				
		return dto;//자동으로 자바객체가(자바빈) JSON형식의 문자열로 변환되서 반환됨
	}*/
	//Map으로 반환하는 경우 : null인 속성 제거 효과
	public @ResponseBody Map ajaxRequest(@RequestParam Map map) {
		
		return map;//자동으로 자바객체가(Map) JSON형식의 문자열로 변환되서 반환됨
	}///////
	//[JSON으로 데이타 받기-@RequestBody으로 받는다] Map혹은 DTO에 받기
	@RequestMapping(value="/Ajax/ajaxRequestJson.do",produces = "application/json;charset=UTF-8")
	public @ResponseBody Map ajaxMap(@RequestBody Map map) {
		return map;
	}///////////////
	
	//[JSON배열로 반환하기-List<Map>혹은 List<DTO계열>로 반환하면 자동으로 JSON배열로 반환된다
	@RequestMapping(value="/Ajax/ajaxRequestJsonArray.do",produces = "application/json;charset=UTF-8")
	public @ResponseBody List ajaxArray() {
		List list = new Vector();
		Map map = new HashMap();
		map.put("id", "kim");
		map.put("pwd", "1234");
		Map map1 = new HashMap();
		map1.put("id", "lee");
		map1.put("pwd", "9999");
		list.add(map);
		list.add(map1);
		return list;		
	}
	//2.JSON을 자바객체로 변환시
	//json구조를 자바빈으로 만드어주는 사이트:
	//https://www.jsonschema2pojo.org/
	@RequestMapping(value="/Ajax/jsonToJava.do",produces = "application/json;charset=UTF-8")
	public @ResponseBody Map jsonToJava(@RequestBody Map map) throws JsonParseException, JsonMappingException, IOException {//JSON(요청보낼때 데이타)->Map으로 자동변환
		//.json파일에서 읽기
		//DTO에 저장
		//OneMemoDTO dto =objectMapper.readValue(new File("onememo.json"), OneMemoDTO.class);
		//System.out.println(dto);
		//Map에 저장
		//Map fileMap =objectMapper.readValue(new File("onememo.json"),Map.class);
		//System.out.println(fileMap.get("title"));
		//JSON형식의 문자열 데이타를 DTO혹은 Map에 저장
		String jsonString=objectMapper.writeValueAsString(map);//Map을 JSON형식 문자열로 변경
		OneMemoDTO dto =objectMapper.readValue(jsonString, OneMemoDTO.class);
		System.out.println(dto.getTitle());
		return map;//map->JSON(응답할때 데이타)으로 자동변환
	}
	
	
}
