package com.prospring.ch9;

import com.prospring.ch9.config.DataJpaConfig;
import com.prospring.ch9.config.ServicesConfig;
import com.prospring.ch9.services.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TxProgrammaticDemo {
	public static void main(String... args) {
		 GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, DataJpaConfig.class);
		 SingerService singerService = ctx.getBean(SingerService.class);
		 
		 System.out.println("Singer count: " + singerService.countAll());
		 
		 ctx.close();
		 }
}
