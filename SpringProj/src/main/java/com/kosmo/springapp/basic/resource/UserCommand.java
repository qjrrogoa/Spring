package com.kosmo.springapp.basic.resource;

public class UserCommand {

	private String name;
	private String user;
	private String pass;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return String.format("[이름:%s, 아이디:%s, 비밀번호:%s]", name,user,pass);
	}
}//////UserCommand