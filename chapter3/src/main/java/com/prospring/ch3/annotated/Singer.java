package com.prospring.ch3.annotated;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("Scissor Sisters")
//@Scope("prototype")
public class Singer {
	private String lyric = "I wanna rock";
	
	public void sing() {
		 //commented because it pollutes the output
		 System.out.println(lyric);
	}
}
