package com.kosmo.springapp.basic.injection;

import org.springframework.stereotype.Component;

@Component
public class Person {
	
	//[속성들-멤버 변수]
	private String name;
	private String addr;
	private int age;
	
	//[기본 생성자]
	public Person() {
		System.out.println("Person의 기본 생성자");
	}

	//[인자 생성자]
	public Person(String name, String addr, int age) {
		this.name = name;
		this.addr = addr;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		String print = String.format("[이름 : %s 주소 : %s 나이 : %s]",name,addr,age);
		return print;
	}
	
	
	
}
