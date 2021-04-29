package com.prospring.ch5;

import com.prospring.ch4.Singer;

public class Guitarist implements Singer {
	private String lyric="You're gonna live forever in me";
	
	@Override
	public void sing(){
		System.out.println(lyric);
	}
	
	public void sing2() {
		System.out.println("Just keep me where the light is");
	}
	
	public void rest() {
		System.out.println("zzz");
	}
}
