package com.kosmo.springapp.basic.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.springapp.onememo.service.ListPagingData;
import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;

@Controller
public class AjaxController {
   @Autowired
   private OneMemoService service;
   

   //ajax미사용-새로고침이됨.
   //반환타입 void:직접 웹브라우저에 출력 스트림으로 결과값 출력
   //String:디스패처서블릿에게 뷰 반환, 결과값은 모델에 저장(jsp로포워드)
//   @RequestMapping("/Ajax/NoAjax.do")
//      public void noajax(@RequestParam Map map,HttpServletResponse resp) throws IOException {
//      //1.컨텐츠 타입 설정
//      resp.setContentType("text/html; charset=UTF-8");
//      //2.웹브라우저에 출력하기 위한 출력스트림 얻기
//      PrintWriter out=resp.getWriter();
//      //3.비지니스 로직 호출
//      boolean isLogin=service.isLogin(map);
//      if(isLogin) {
//         out.println("<h2>"+map.get("id")+"님 즐감하세요</h2>");
//      }
//      else {
//         out.println("<script>");
//         out.println("alert('아뒤와 비번이 틀려요');");
//         out.println("history.back();");
//         out.println("</script>");
//      }
//      //4.웹브라우저와 연결된 스트림 닫기
//      out.close();
//   }
   @RequestMapping("/Ajax/NoAjax.do")
   public String noajax(@RequestParam Map map,Model model) {
      // 1.서비스 호출
      boolean isLogin=service.isLogin(map);
      //2.데이타 저장
      model.addAttribute("isLogin", isLogin?map.get("id")+"님 반갑습니다":"회원정보 불일치");
      //3.뷰 정보 반환
      return "ajax12/Ajax";
   }
   //ajax사용-페이지를전송하지않고 데이타만 전송-반환타입이 String인경우는 @ResponseBody()어노테이션 사용
//   @RequestMapping("/Ajax/AjaxText.do")
//   public void ajaxText(@RequestParam Map map,HttpServletResponse resp) throws IOException {
//      //1.컨텐츠 타입 설정
//      resp.setContentType("text/html; charset=UTF-8");
//      //2.웹브라우저에 출력하기 위한 출력스트림 얻기
//      PrintWriter out=resp.getWriter();
//      //3.비지니스 로직 호출
//      boolean isLogin=service.isLogin(map);
//      //Y혹은 N값으로 응답할때-반드시 print()메소드로 안그러면 println은 줄바꿈이 추가됨
//      //out.print(isLogin?"Y":"N");
//      //메시지로 응답할때
//      out.print(isLogin?map.get("id")+"님 즐감!!":"회원 가입해주세요");
//      //4.웹브라우저와 연결된 스트림 닫기
//      out.close();
//   }
   @RequestMapping(value="/Ajax/AjaxText.do", produces = "text/plain;charset=UTF-8")
   public @ResponseBody String ajaxText(@RequestParam Map map) {
      // 1.서비스 호출
      boolean isLogin=service.isLogin(map);
      // y혹은 n값으로 응답할때
      //return isLogin?"Y":"N";
      //메시지로 응답할때
      return isLogin?"map.get(\"id\")+\"님 즐감하삼!!":"회원가입 하삼";
   }
   //json으로 응답할때
   @Autowired
   private ObjectMapper objectMapper;
   
   @RequestMapping(value="/Ajax/AjaxJson.do",produces = "application/json;charset=UTF-8")
   public @ResponseBody String ajaxJson(@RequestParam Map map) throws JsonProcessingException {
      // 1.서비스 호출
      boolean isLogin=service.isLogin(map);
      //Jackson의 ObjectMapper의 writeValueAsString()메소드로
      //Map 을 JSON형태의 스트링으로 변경:{"id":"KIM","pwd":"9999"}
      //String jsonString=objectMapper.writeValueAsString(map);
      //반환 :{"isLogin":"방가방가"} 혹은 {"isLogin":"다음기회에"}
      return String.format("{\"isLogin\":\"%s\"}",isLogin?"방가방가":"다음기회에");
   }
   
   @RequestMapping(value="/Ajax/AjaxJsonArray.do", produces = "application/json; charset=UTF-8")
      public @ResponseBody String ajaxJsonArray(HttpServletRequest req) throws JsonProcessingException {
         // 1] 서비스 호출
         Map map = new HashMap();
         map.put("start", 1);
         map.put("end", 10);
         ListPagingData<OneMemoDTO> datas = service.selectList(map, req, 1);
         List<OneMemoDTO> list = datas.getLists();
         
         String jsonString = objectMapper.writeValueAsString(list);
         System.out.println(jsonString);
         return jsonString;
      }
   
}