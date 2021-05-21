package com.kosmo.springapp.basic.annotation;

public class LoginCommand {
	//폼의 파라미터명과 일치 시키자
	//속성은 소문자로 시작해야한다
	private String user;
	private String pass;
	
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
	public String toString() {
		return " [아이디 = " + user + ", 비번 = " + pass + "]";
	}
	
}
