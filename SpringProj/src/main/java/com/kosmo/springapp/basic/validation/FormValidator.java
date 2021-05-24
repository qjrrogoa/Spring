package com.kosmo.springapp.basic.validation;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/*
 * Validator인터페이스를 상속 받았기때문에
 * 특정 DTO계열 클래스(커맨드 객체)의 유효성 검증을 할 수 
 * 있는 자격을 갖춘 Validator클래스 역할을 함.
 */

public class FormValidator implements Validator {
	
	
	/*
	  supports()]   
	   매개변수에 전달된 커맨드 객체가 유효성 검증을 
	   지원 할 수 있는
	   커맨드 객체 인지 아닌지 판단하는 메소드
	   만약 이 메소드를 통과하지 못하면 
	   실제 유효성 검사를
	   하는 validate()메소드가 호출이 안된다.
	 */
	@Override
	public boolean supports(Class<?> command) {
		/*
	      매개변수로 받은 command가 FormCommand타입인지
	      혹은 FormCommand의 자식타입인지 판단하는 메소드
	      supports()메소드는 프레임워크가 호출함.
	      매개변수인 command는 컨트롤러 클래스가 매개변수로  받은 커맨드 객체를 넘겨줌.
	    */
		//return FormCommand.class.isAssignableFrom(command);
		//equals():command가FormCommand타입이면 true,아니면false반환
		return FormCommand.class.equals(command);
	}
	/*
	   * supports()메소드에서 true반환시에만 실행됨.
	   * 즉 유효성 검사를 하고자하는 커맨드객체가 
	   * 맞는 경우에만 실행됨.
	   * 
	   * 첫번째 매개변수:커맨드 객체(command)
	   * 두번째 매개변수:에러정보를 저장할 Errors타입(BindingResult타입)
	   * -개발자가 컨트롤러 메소드에서 호출한다.
	   */
	@Override
	public void validate(Object command, Errors errors) {
		/*
		  오류 시] 
		 Errors타입에 에러정보 저장
		 rejectValue("FormCommand의 속성명","에러코드");
		  에러코드(중복 불가능)는 임의로.........
		*/ 
		FormCommand cmd=(FormCommand)command;
		
		if(cmd.getName().trim().equals("")) {
			errors.rejectValue("name", "nameError");			
		}
		if(cmd.getYears().trim().length()==0) {
			errors.rejectValue("years", "yearsError");			
		}
		else {
			try {
				Integer.parseInt(cmd.getYears().trim());
			}
			catch(Exception e) {
				errors.rejectValue("years", "yearsNotNumber");					
			}
		}
		if(cmd.getGender() == null) {
			errors.rejectValue("gender", "genderError");				
		}
		if(cmd.getInters() == null) {
			errors.rejectValue("inters", "intersError");				
		}
		else {			
			
			if(cmd.getInters().length < 2) {
				errors.rejectValue("inters", "intersLackError");
				
			}
		}
		if(cmd.getGrade().trim().equals("")) {
			errors.rejectValue("grade", "gradeError");
	
		}
		if(cmd.getSelf().trim().equals("")) {
			errors.rejectValue("self", "selfError");			
		}		
	}//////////////////

}
