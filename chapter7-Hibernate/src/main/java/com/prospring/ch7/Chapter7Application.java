package com.prospring.ch7;


import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.prospring.ch7.config.AppConfig;
import com.prospring.ch7.dao.SingerDao;
import com.prospring.ch7.entities.Album;
import com.prospring.ch7.entities.Instrument;
import com.prospring.ch7.entities.Singer;

@SpringBootApplication
public class Chapter7Application {
	private static Logger logger = LoggerFactory.getLogger(Chapter7Application.class);
	
	public static void main(String... args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerDao singerDao = ctx.getBean(SingerDao.class);
		
		Singer singer = new Singer();
		 singer.setFirstName("BB");
		 singer.setLastName("King");
		 singer.setBirthDate(new Date( (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
		 Album album = new Album();
		 album.setTitle("My Kind of Blues");
		 album.setReleaseDate(new java.sql.Date( (new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
		 singer.addAbum(album);
		 album = new Album();
		 album.setTitle("A Heart Full of Blues");
		 album.setReleaseDate(new java.sql.Date( (new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
		 singer.addAbum(album);
		 singerDao.save(singer);
		 //assertNotNull(singer.getId());
		 List<Singer> singers = singerDao.findAllWithAlbum();
		 //assertEquals(4, singers.size());
		 listSingersWithAlbum(singers);
		 
		 ctx.close();
	}
	

	/*
	//@Before
	public void setUp(){
		ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		singerDao = ctx.getBean(SingerDao.class);
		//assertNotNull(singerDao);
	}
	
	
	//@Test
	public void testInsert(){
		 Singer singer = new Singer();
		 singer.setFirstName("BB");
		 singer.setLastName("King");
		 singer.setBirthDate(new Date( (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
		 Album album = new Album();
		 album.setTitle("My Kind of Blues");
		 album.setReleaseDate(new java.sql.Date( (new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
		 singer.addAbum(album);
		 album = new Album();
		 album.setTitle("A Heart Full of Blues");
		 album.setReleaseDate(new java.sql.Date( (new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
		 singer.addAbum(album);
		 singerDao.save(singer);
		 //assertNotNull(singer.getId());
		 List<Singer> singers = singerDao.findAllWithAlbum();
		 //assertEquals(4, singers.size());
		 listSingersWithAlbum(singers);
	 }
	 

	 public void testFindAll(){
		 List<Singer> singers = singerDao.findAll();
		 //assertEquals(3, singers.size());
		 listSingers(singers);
	 }
	 

	 public void testFindAllWithAlbum(){
		 List<Singer> singers = singerDao.findAllWithAlbum();
		 //assertEquals(3, singers.size());
		 listSingersWithAlbum(singers);
	 }
	 

	 public void testFindByID(){
		 Singer singer = singerDao.findById(1L);
		 //assertNotNull(singer);
		 logger.info(singer.toString());
	 }
	 */
	 private static void listSingers(List<Singer> singers) {
		 logger.info(" ---- Listing singers:");
		 for (Singer singer : singers) {
			 logger.info(singer.toString());
		 }
	 }
	 
	 private static void listSingersWithAlbum(List<Singer> singers) {
		 logger.info(" ---- Listing singers with instruments:");
		 for (Singer singer : singers) {
			 logger.info(singer.toString());
			 if (singer.getAlbums() != null) {
				 for (Album album : singer.getAlbums()) {
					 logger.info("\t" + album.toString());
				 }
			 }
			 if (singer.getInstruments() != null) {
				 for (Instrument instrument : singer.getInstruments()) {
					 	logger.info("\tInstrument: " + instrument.getInstrumentId());
				 }
			 }
		 }
	 }
	 
/*
	 public void tearDown(){
		 ctx.close();
	 }
*/
}
