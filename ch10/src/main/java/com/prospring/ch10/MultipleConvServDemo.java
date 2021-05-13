package com.prospring.ch10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;
import com.prospring.ch10.config.AppConfig;
import com.prospring.ch10.entities.AnotherSinger;
import com.prospring.ch10.entities.Singer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class MultipleConvServDemo {
private static Logger logger =LoggerFactory.getLogger(MultipleConvServDemo.class);
public static void main(String... args) {
	GenericApplicationContext ctx =	new AnnotationConfigApplicationContext(AppConfig.class);
	
	Singer john = ctx.getBean("john", Singer.class);
	logger.info("Singer info: " + john);
	ConversionService conversionService =ctx.getBean(ConversionService.class);
	AnotherSinger anotherSinger = conversionService.convert(john, AnotherSinger.class);
	logger.info("Another singer info: " + anotherSinger);
	
	String[] stringArray = conversionService.convert("a,b,c",	String[].class);
	
	logger.info("String array: " + stringArray[0]+ stringArray[1] + stringArray[2]);
	
	List<String> listString = new ArrayList<>();
	listString.add("a");
	listString.add("b");
	listString.add("c");
	
	Set<String> setString = conversionService.convert(listString, HashSet.class);
	
	for (String string: setString)
		System.out.println("Set: " + string);
	}


}
