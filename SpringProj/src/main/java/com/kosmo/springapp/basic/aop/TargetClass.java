package com.kosmo.springapp.basic.aop;

import lombok.Setter;

/*
 * 공통관점(공통로직)을 주입받는(위빙) 대상 클래스
 */
@Setter //SETTER인젝션 주입-와이어링한다.
public class TargetClass {
	//속성(멤버변수)]
	private int start;
	private int end;
	
	//핵심 로직]-핵심로직 전후에  공통관점(Around Advice) 주입-위빙한다.
	public long getTotal() {
		
		long total=0;
		for(int i=start;i<=end;i++) total+=i;
		return total;
		
	}
}
