package com.kosmo.springapp.basic.validation;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ValidationController {
	//방법1]스프링 API사용하지 않고 유효성 검증하기
//	@RequestMapping("/Validation/ValidationCheck.do")
//	public String exec(FormCommand cmd,Model model) {
//		//System.out.println("성별:"+cmd.getGender());
//		System.out.println("관심사항:"+Arrays.toString(cmd.getInters()));
//		if(!validate(cmd, model)) {//유효성 실패
//			//뷰정보반환]-다시 입력폼으로 이동
//			//※${param.inters}로 출력시 선택한 처음것만 출력됨으로
//			model.addAttribute("inters", Arrays.toString(cmd.getInters()));//체크된거 유지용
//			return "validation09/Validation";
//		}
//		//데이타 저장]
//		//줄바꿈 처리]
//		cmd.setSelf(cmd.getSelf().replace("\r\n", "<br/>"));
//		model.addAttribute("cmd", cmd);
//		//뷰정보 반환
//		return "validation09/ValidationComplete";
//	}
//	//내가 만든 유효성 검증용 메소드]
//	private boolean validate(FormCommand cmd,Model model ) {
//		if(cmd.getName().trim().equals("")) {
//			model.addAttribute("nameError","이름을 입력하세요");
//			return false;
//		}
//		if(cmd.getYears().trim().length()==0) {
//			model.addAttribute("yearsError","나이를 입력하세요");
//			return false;
//		}
//		else {
//			try {
//				Integer.parseInt(cmd.getYears().trim());
//			}
//			catch(Exception e) {
//				model.addAttribute("yearsError","나이는 숫자만...");
//				return false;
//			}
//		}
//		if(cmd.getGender() == null) {
//			model.addAttribute("genderError","성별을 선택하세요");
//			return false;
//		}
//		if(cmd.getInters() == null) {
//			model.addAttribute("intersError","관심사항을 선택하세요");
//			return false;
//		}
//		else {
//			//private String inters;인 경우
////			/*
////			StringTokenizer tokenizer = new StringTokenizer(cmd.getInters(),",");
////			if(tokenizer.countTokens() < 2) {
////				model.addAttribute("intersError", "관심사항 2개 이상을 선택하세요!");
////				return false;
////			}*/
//			//private String[] inters;인 경우
//			if(cmd.getInters().length < 2) {
//				model.addAttribute("intersError","관심사항은 2개 이상을 선택하세요");
//				return false;
//			}
//			if(cmd.getGrade().trim().equals("")) {
//				model.addAttribute("gradeError","학력을 선택하세요");
//				return false;
//			}
//			if(cmd.getSelf().trim().equals("")) {
//				model.addAttribute("selfError","자기 소개를 입력하세요");
//				return false;
//			}
//		}
//		
//		return true;
//	}//////////////
	// 방법2]스프링 API사용
	//@Resource(name="validator")
	@Autowired
	private FormValidator validator;
	
	@RequestMapping("/Validation/ValidationCheck.do")
	public String exec(FormCommand cmd,BindingResult errors, Model model) {//매개변수 순서 : ※FormCommand다음에 BindingResult순으로	
		
		
		/*
		 * 내가 만든 Validator클래스의 validate()호출 
		 * validate()메소드 첫번째 매개변수에 유효성 검증 해달라고 커맨드 객체 넣어주고 
		 * 두번째 매개변수에는 에러정보를 담아 달라고 Errors타입 전달
		 */
		validator.validate(cmd, errors);
		/*
		  FormValidator에서 한번이라도 
		  rejectValue()를 한다면
		  BindingResult타입의 hasErrors()메소드는 true반환
		  */
		if(errors.hasErrors()) {
			model.addAttribute("inters", Arrays.toString(cmd.getInters()));//체크된거 유지용
			//뷰정보 반환- 다시 입력폼으로 이동
			return "validation09/Validation";
		}
		//데이타 저장]
		//줄바꿈 처리]
		cmd.setSelf(cmd.getSelf().replace("\r\n", "<br/>"));
		model.addAttribute("cmd", cmd);
		//뷰정보 반환
		return "validation09/ValidationComplete";
	}
}/////////////
