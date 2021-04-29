package com.prospring.ch6;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.prospring.ch6.dao.PlainSingerDao;
import com.prospring.ch6.dao.SingerDao;
import com.prospring.ch6.entities.Singer;

public class PlainJdbcDemo {
	private static SingerDao singerDao = new PlainSingerDao();
	 private static Logger logger = LoggerFactory.getLogger(PlainJdbcDemo.class);
	 public static void main(String... args) {
		 logger.info("Listing initial singer data:");
		 listAllSingers();
		 logger.info("-------------");
		 logger.info("Insert a new singer");
		 
		 Singer singer = new Singer();
		 singer.setFirstName("Ed");
		 singer.setLastName("Sheeran");
		 singer.setBirthDate(new Date((new GregorianCalendar(1991, 2, 1991)).getTime().getTime()));
		 singerDao.insert(singer);
		 
		 logger.info("Listing singer data after new singer created:");
		 listAllSingers();
		 logger.info("-------------");
		 logger.info("Deleting the previous created singer");
		 singerDao.delete(singer.getId());
		 logger.info("Listing singer data after new singer deleted:");
		 listAllSingers();
	 }
	 
	 private static void listAllSingers() {
		 List<Singer> singers = singerDao.findAll();
		 for (Singer singer: singers) {
			 logger.info(singer.toString());
		 }
	 }
}
