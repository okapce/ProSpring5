package com.prospring.ch8;

import org.springframework.boot.SpringApplication;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.prospring.ch8.config.JpaConfig;
import com.prospring.ch8.entities.Album;
import com.prospring.ch8.entities.Instrument;
import com.prospring.ch8.entities.Singer;
import com.prospring.ch8.service.SingerService;

public class SingerJPATest {
	
	private static Logger logger = LoggerFactory.getLogger(SingerJPATest.class);
	public static SingerService singerService;
	public static void main(String[] args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
		//singerService = ctx.getBean(SingerService.class);
		singerService = ctx.getBean("jpaSingerService", SingerService.class);
		
		
		/* find all 
		List<Singer> singers = singerService.findAll();
		listSingers(singers);
		*/
		
		/* Find all with albums 
		List<Singer> singers = singerService.findAllWithAlbum();
		listSingersWithAlbum(singers);
		*/
		
		/*find by id 
		Singer singer = singerService.findById(1l);
		showSinger(singer);
		*/
		
		//testInsert();
		//testUpdate();
		//testDelete();
		
		List<Singer> singers = singerService.findByCriteriaQuery("John", "Mayer");
		listSingersWithAlbum(singers);
		
		ctx.close();
	}
	
	 private static void showSinger(Singer singer) {
		 logger.info(" ---- Listing singers:");
		 logger.info(singer.toString());
	}
	 
	private static void listSingers(List<Singer> singers) {
		 logger.info(" ---- Listing singers:");
		 for (Singer singer : singers) {
			 logger.info(singer.toString());
		 }
	}
	 
	private static void listSingersWithAlbum(List<Singer> singers) {
		 logger.info(" ---- Listing singers with instruments:");
		 /*
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
					 logger.info("\tInstrument: " + instrument.getInstrumentId());
				 }
			 }
		 }
		 */
		 singers.forEach(s -> { logger.info(s.toString());
			 if (s.getAlbums() != null) {
				 s.getAlbums().forEach(a -> logger.info("\t" + a.toString()));
			 }
			 if (s.getInstruments() != null) {
				 	s.getInstruments().forEach(i -> logger.info("\tInstrument: " + i.getInstrumentId()));
			 }
		 });
	}	
		 
	
	public static void testInsert(){
		 Singer singer = new Singer();
		 singer.setFirstName("Lemmy");
		 singer.setLastName("the best");
		 singer.setBirthDate(new Date( (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
		 Album album = new Album();
		 album.setTitle("metal");
		 album.setReleaseDate(new java.sql.Date( (new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
		 singer.addAlbum(album);
		 album = new Album();
		 album.setTitle("aces of spads");
		 album.setReleaseDate(new java.sql.Date( (new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
		 singer.addAlbum(album);
		 singerService.save(singer);
		 //assertNotNull(singer.getId());
		 List<Singer> singers = singerService.findAllWithAlbum();
		 //assertEquals(4, singers.size());
		 listSingersWithAlbum(singers);
	}
	
	
	 public static void testUpdate(){
	 Singer singer = singerService.findById(1L);
	 //making sure such singer exists assertNotNull(singer);
	 //making sure we got expected record assertEquals("Mayer", singer.getLastName());
	 //retrieve the album
	 Album album = singer.getAlbums().stream().filter(a -> a.getTitle().equals("Battle Studies")).findFirst().get();
	 singer.setFirstName("John Clayton");
	 //singer.removeAlbum(album);
	 singerService.save(singer);
	 listSingersWithAlbum(singerService.findAllWithAlbum());
	 }
	 
	 public static void testDelete(){
		 Singer singer = singerService.findById(2l);
		 //making sure such singer exists
		 //assertNotNull(singer);
		 singerService.delete(singer);
		 listSingersWithAlbum(singerService.findAllWithAlbum());
	 }
}
