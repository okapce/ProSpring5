package com.prospring.ch7;

import com.prospring.ch7.config.AppConfig;
import com.prospring.ch7.dao.SingerDao;
import com.prospring.ch6.entities.Singer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class SpringHibernateDemo {
	private static Logger logger = LoggerFactory.getLogger(SpringHibernateDemo.class);
			 public static void main(String... args) {
			 GenericApplicationContext ctx =new AnnotationConfigApplicationContext(AppConfig.class);
			 SingerDao singerDao = ctx.getBean(SingerDao.class);
			 singerDao.delete(null);
			 listSingers(singerDao.findAll());
			 ctx.close();
			 }
			 private static void listSingers(List<Singer> singers) {
			 logger.info(" ---- Listing singers:");
			 for (Singer singer : singers) {
			 logger.info(singer.toString());
			 }
			 }
}
