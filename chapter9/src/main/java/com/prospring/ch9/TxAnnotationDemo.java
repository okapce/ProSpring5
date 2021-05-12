package com.prospring.ch9;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.prospring.ch9.config.DataJpaConfig;
import com.prospring.ch9.config.ServicesConfig;
import com.prospring.ch9.entities.Singer;
import com.prospring.ch9.services.SingerService;

public class TxAnnotationDemo {
	public static void main(String... args) {
		 GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, DataJpaConfig.class);
		 
		 SingerService singerService = ctx.getBean(SingerService.class);
		 List<Singer> singers = singerService.findAll();
		 System.out.println("Printing: ");
		 singers.forEach(s -> System.out.println(s));
		 
		 System.out.println("Singer count: " + singerService.countAll());
		 
		 Singer singer = singerService.findById(1L);
		 singer.setFirstName("John Clayton change");
		 singer.setLastName("Mayer");
		 singerService.save(singer);
		 System.out.println("Singer saved successfully: " + singer);
		 
		 
		 
		 ctx.close();
	 }
}
