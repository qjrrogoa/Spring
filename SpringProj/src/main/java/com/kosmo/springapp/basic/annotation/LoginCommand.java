package com.kosmo.springapp.basic.annotation;

public class LoginCommand {
	//속성(멤버변수)는 소문자로 시작
	private String user;
	private String pass;
	
	//Getter / Setter
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "[아이디="+user+", 비번="+pass+"]";
	}
	
	
	
}////