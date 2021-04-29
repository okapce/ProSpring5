package com.prospring.ch4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.prospring.ch2.decoupled.MessageRenderer;

public class JavaConfigXMLExample {
	public static void main(String... args) {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/app-context-xml.xml");
		 MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
		 renderer.render();
		 }
}
