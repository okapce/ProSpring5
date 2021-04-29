package com.prospring.ch3;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prospring.ch2.annotation.HelloWorldConfiguration;
import com.prospring.ch2.decoupled.MessageProvider;
import com.prospring.ch2.decoupled.MessageRenderer;
import com.prospring.ch3.annotation.Singer;
import com.prospring.ch3.xml.ConstructorConfusion;
import com.prospring.ch3.xml.InjectSimple;


@SpringBootApplication
@RestController
public class Chapter3Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter3Application.class, args);
	}
	
	@GetMapping("/xmlBean")
	public String XmlConfigWithBeanFactory() {
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader rdr = new XmlBeanDefinitionReader(factory);
		rdr.loadBeanDefinitions(new ClassPathResource("spring/xml-bean-factory-config.xml"));
		Oracle oracle = (Oracle) factory.getBean("oracle");
		System.out.println(oracle.defineMeaningOfLife());
		return String.format("check console");
	}
	
	@GetMapping("/AppContext")
	public String DeclareSpringComponents() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context.xml");
		ctx.refresh();
		MessageRenderer messageRenderer = ctx.getBean("renderer", MessageRenderer.class);
		messageRenderer.render();
		ctx.close();
		return String.format("Check the console ");
	}
	
	@GetMapping("/AnnoContext")
	public String HelloWorldSpringAnnotated() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
		MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
		mr.render();
		return String.format("Check console");
	}
	
	@GetMapping("/ConsContext")
	public String Constructor() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-annotation.xml");
		ctx.refresh();
		MessageProvider messageProvider = ctx.getBean("provider", MessageProvider.class);
		System.out.println(messageProvider.getMessage());
		ctx.close();
		return String.format("Check the console ");
	}

	@GetMapping("/ConsConfusion")
	public String ConsConfusion() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-xml.xml");
		ctx.refresh();
		ConstructorConfusion cc = (ConstructorConfusion) ctx.getBean("constructorConfusion");
		System.out.println(cc);
		ctx.close();
		return String.format("Check the console ");
	}
	
	@GetMapping("/FieldInj")
	public String FieldInj() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context.xml");
		ctx.refresh();
		
		Singer singerBean = ctx.getBean(Singer.class);
		singerBean.sing();
		
		ctx.close();
		return String.format("Check the console ");
	}
	
	@GetMapping("/InjSimple")
	public String InjSimple() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-xml.xml");
		ctx.refresh();
		
		InjectSimple simple = (InjectSimple) ctx.getBean("injectSimple");
		System.out.println(simple);
		
		ctx.close();
		return String.format("Check the console ");
	}
	
}
