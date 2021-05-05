package com.prospring.ch8;

import org.springframework.boot.SpringApplication;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.prospring.ch8.config.JpaConfig;
import com.prospring.ch8.entities.Singer;
import com.prospring.ch8.service.SingerService;

public class SingerJPATest {
	
	private static Logger logger = LoggerFactory.getLogger(SingerJPATest.class);
	
	public static void main(String[] args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
		SingerService singerService = ctx.getBean(SingerService.class);
		//assertNotNull(singerService);
		
		List<Singer> singers = singerService.findAll();
		 //assertEquals(3, singers.size());
		listSingers(singers);
		
		ctx.close();
	}
	
	
	 private static void listSingers(List<Singer> singers) {
		 logger.info(" ---- Listing singers:");
		 for (Singer singer : singers) {
			 logger.info(singer.toString());
		 }
	 }
}
