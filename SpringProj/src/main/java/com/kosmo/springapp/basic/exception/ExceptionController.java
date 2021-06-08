package com.kosmo.springapp.basic.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExceptionController {
   
   /* 방법1]
    * 어노테이션으로 세션영엑에 저장된 속성을 읽어올 때 저장된 속성이 없는 경우,
    * @RequestParam int years일때 문자열을 입력하는 경우 등
    * try~catch절로 처리가 안됨으로 반드시 어노테이션으로 처리
    */
//   @RequestMapping("/Exception/Exception.do")
//   public String execute(@RequestParam String years, Model model) {
//      //int가 아닌 String으로 받으면 try~catch가능
//
//      //데이터 저장]
//      try {
//         model.addAttribute("message","[당신의 10년후 나이는 : " + (Integer.parseInt(years)+10) + "]");
//      }
//      catch(NumberFormatException e) {//String으로 받을때만 가능
//         model.addAttribute("errorMsg","나이는 숫자만....");
//      }
//      //뷰정보 반환
//      return "exception13/Exception";
//   }
   
   // 방법2] @ExceptionHandler 사용 - 예외처리할는 모든 컨트롤러마다 작성해야함 즉 하나의 메서드에서 처리
   // @Exception({Exception1.class, Exception2.class})
   // @Exception({NumberFormatException.class})
   
//   @ExceptionHandler({NumberFormatException.class})
//   public String error(Model model) {
//      model.addAttribute("errorMsg","나이는 숫자만 입력하슈!");
//      return "exception13/Exception";
//   }
   
   //방법3] 모든 컨트롤러에서 발생하는 예외를 처리하기- 위 error메서드 추석후 테스트
   //@ControllerAdvice를 통해 모든 컨트롤러에서 발생하는 예외 처리 가능
   @RequestMapping("/Exception/Exception.do")
   public String execute(@RequestParam int years, Model model) {
      //데이터 저장]
      model.addAttribute("message","[당신의 10년후 나이는 : " + (years+10) + "]");
      //뷰정보 반환
      return "exception13/Exception";
   }
   
}