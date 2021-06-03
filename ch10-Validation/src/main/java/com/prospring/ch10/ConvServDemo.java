package com.prospring.ch10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.prospring.ch10.config.AppConfig;
import com.prospring.ch10.entities.Singer;

public class ConvServDemo {
	private static Logger logger = LoggerFactory.getLogger(ConvServDemo.class);
	
	public static void main(String... args) {
		GenericApplicationContext ctx =	new AnnotationConfigApplicationContext(AppConfig.class);
		Singer john = ctx.getBean("john", Singer.class);
		logger.info("Singer info: " + john);
		ctx.close();
	}
}
