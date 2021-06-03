package com.prospring.ch7;

import com.prospring.ch7.config.AppConfig;
import com.prospring.ch7.dao.SingerDao;
import com.prospring.ch7.entities.Album;
import com.prospring.ch7.entities.Instrument;
import com.prospring.ch7.entities.Singer;

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
		 //singerDao.delete(null);
		 
		 //Singer singer = singerDao.findById(2l);
		 //logger.info(singer.toString());
		 //singerDao.delete(singer);
		 listSingersWithAlbum(singerDao.findAllWithAlbum());
		 listSingers(singerDao.findAll());
		 
		 
		 ctx.close();
	}
	
	 private static void listSingersWithAlbum(List<Singer> singers) {
		 logger.info(" ---- Listing singers with instruments:");
		 for (Singer singer : singers) {
			 logger.info(singer.toString());
			 if (singer.getAlbums() != null) {
				 for (Album album :
					 singer.getAlbums()) {
					 logger.info("\t" + album.toString());
			 }
		 }
		 if (singer.getInstruments() != null) {
			 for (Instrument instrument : singer.getInstruments()) {
				 logger.info("\t" + instrument.getInstrumentId());
			 }
		 }
		 }
	 }
			 
	private static void listSingers(List<Singer> singers) {
		 logger.info(" ---- Listing singers:");
		 for (Singer singer : singers) {
			 logger.info(singer.toString());
		 }
	}
}
