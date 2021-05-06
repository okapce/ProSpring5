package com.prospring.ch8;

import com.prospring.ch8.config.DataJpaConfig;
import com.prospring.ch8.entities.Album;
import com.prospring.ch8.entities.Singer;
import com.prospring.ch8.service.AlbumService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import java.util.List;

public class SingerDataJPATest {
	private static Logger logger = LoggerFactory.getLogger(SingerDataJPATest.class);
	
	public static void main(String[] args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
		//SingerService singerService = ctx.getBean(SingerService.class);
		AlbumService albumService = ctx.getBean(AlbumService.class);
		
		/* testFindAll()
		List<Singer> singers = singerService.findAll();
		listSingers(singers);
		 */
		
		/* testFindByName 
		List<Singer> singers = singerService.findByFirstName("John");
  		listSingers(singers);
		*/
		
		/* testFindByFirstNameAndLastName 
		List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
		listSingers(singers);
		*/
		
		List<Album> albums = albumService.findByTitle("The");

		 albums.forEach(a -> logger.info(a.toString() + ", Singer: "
		 + a.getSinger().getFirstName() + " "
		 + a.getSinger().getLastName()));
		
		ctx.close();
	}
	
	private static void listSingers(List<Singer> singers) {
		 logger.info(" ---- Listing singers:");
		 for (Singer singer : singers) {
			 logger.info(singer.toString());
		 }
	}
}

