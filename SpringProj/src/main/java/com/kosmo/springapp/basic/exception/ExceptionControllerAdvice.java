package com.kosmo.springapp.basic.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//개발시는 아래 주석
@ControllerAdvice
public class ExceptionControllerAdvice {

   @ExceptionHandler({Exception.class})
   public String error(Model model,Exception e) {
      model.addAttribute("errors",String.format("<h4>담당자에게 연락하세요</h4> <h5>예외메시지 : %s</h5>",e.getMessage()));
      //뷰정보 반환
      return "exception13/Errors";
   }
}