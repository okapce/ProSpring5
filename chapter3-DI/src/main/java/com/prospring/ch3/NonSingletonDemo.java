package com.prospring.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class NonSingletonDemo {
	public static void main(String... args) {
		 GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		 ctx.load("classpath:spring/app-context-xml.xml");
		 ctx.refresh();
		 
		 com.prospring.ch3.annotated.Song song1 = ctx.getBean("nonSingleton", com.prospring.ch3.annotated.Song.class);
		 com.prospring.ch3.annotated.Song song2 = ctx.getBean("nonSingleton", com.prospring.ch3.annotated.Song.class);
		 
		 System.out.println("Identity Equal?: " + (song1 ==song2));
		 System.out.println("Value Equal:? " + song1.equals(song2));
		 System.out.println(song1);
		 System.out.println(song2);
		 
		 ctx.close();
		 }
}
