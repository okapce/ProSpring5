package com.prospring.ch2;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prospring.ch2.annotated.HelloWorldConfig;
import com.prospring.ch2.decoupled.HelloWorldMessageProvider;
import com.prospring.ch2.decoupled.MessageProvider;
import com.prospring.ch2.decoupled.MessageRenderer;
import com.prospring.ch2.decoupled.MessageSupportFactory;
import com.prospring.ch2.decoupled.StandardOutMessageRenderer;

@SpringBootApplication
@RestController
public class Chapter2Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter2Application.class, args);
	}

	@GetMapping("/message")
	public String showMessage(@RequestParam(value="msg", defaultValue="test") String msg) {
		return String.format("This is the message: %s", msg);
	}
	
	@GetMapping("/render")
	public String renderMessage() {
		MessageRenderer mr = new StandardOutMessageRenderer();
		MessageProvider mp = new HelloWorldMessageProvider();
		mr.setMessageProvider(mp);
		mr.render();
		return String.format("Check the console ");
	}
	
	@GetMapping("/renderF")
	public String renderMessageF() {
		MessageRenderer mr = MessageSupportFactory.getInstance().getMessageRenderer();
		MessageProvider mp = MessageSupportFactory.getInstance().getMessageProvider();
		mr.setMessageProvider(mp);
		mr.render();
		return String.format("Check the console ");
	}
	
	@GetMapping("/renderI")
	public String renderMessageI() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/app-context.xml");
		MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
		mr.render();
		return String.format("Check the console ");
	}
	
	@GetMapping("/renderA")
	public String renderMessageA() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);
		MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
		mr.render();
		return String.format("Check the console ");
	}
	
	
}
