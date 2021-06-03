package com.prospring.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InheritanceDemo {
	public static void main(String... args) {
		 GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		 
		 ctx.load("classpath:spring/app-context-xml.xml");
		 ctx.refresh();
		 
		 Person parent = (Person) ctx.getBean("parent");
		 Person child = (Person) ctx.getBean("child");
		 
		 System.out.println("Parent:\n" + parent);
		 System.out.println("Child:\n" + child);
		 }
}
