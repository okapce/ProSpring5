package com.prospring.ch6;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.prospring.ch6.config.AppConfig;
import com.prospring.ch6.dao.SingerDao;
import com.prospring.ch6.entities.Album;
import com.prospring.ch6.entities.Singer;


public class AnnotationJdbcTest {
	private static Logger logger = LoggerFactory.getLogger(DbConfigTest.class);
	
	private static void testDataSource(SingerDao singerDao) throws SQLException{
		 Connection connection = null;
		 try {
			 /*List by first name */
			 //List<Singer> singers = singerDao.findByFirstName("John");

			 /* List all Singers */
			 Singer insertSinger = new Singer();
			 
			 /* Insert */
			 //insertSinger.setId((long) 1);
			 //insertSinger.setFirstName("Ed");
			 //insertSinger.setLastName("Sheeran");
			 //insertSinger.setBirthDate(new Date((new GregorianCalendar(1991, 1, 17)).getTime().getTime()));
			 //singerDao.insert(insertSinger);
			 
			 //List<Singer> singers = singerDao.findAll();
			 
			 /* insert with batchsqlupdate */
			 insertSinger.setFirstName("BB");
			 insertSinger.setLastName("King");
			 insertSinger.setBirthDate(new Date((new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
			 
			 Album insertAlbum = new Album();
			 insertAlbum.setTitle("My Kind of Blues");
			 insertAlbum.setReleaseDate(new Date((new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
			 insertSinger.addAlbum(insertAlbum);
			 
			 insertAlbum = new Album();
			 insertAlbum.setTitle("A Heart Full of Blues");
			 insertAlbum.setReleaseDate(new Date((new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
			 insertSinger.addAlbum(insertAlbum);
			 
			 singerDao.insertWithAlbum(insertSinger);
			 List<Singer> singers = singerDao.findAllWithAlbums();

			singers.forEach(singer -> {
				System.out.println(singer);
				if (singer.getAlbums() != null) {
					for (Album album : singer.getAlbums()) {
						System.out.println("\t--> " + album);
					}
				}
				});
			 
			 /* Stored Function */
			 //String firstName = singerDao.findFirstNameById(2L);
			 //System.out.println("Retrieved value: " + firstName);
			 
			 } catch (Exception e) {
				logger.debug("Something unexpected happened.", e);
			 } finally {
				 if (connection != null) {
					 connection.close();
				 }
			 }
	}
	
	public static void main(String... args) throws SQLException {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerDao singerDao = ctx.getBean(SingerDao.class);
		
		testDataSource(singerDao);
		
		ctx.close();	
	}
}
