package com.prospring.ch8;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;
//import com.prospring.ch8.config.AuditConfig;
import com.prospring.ch8.config.DBInitializer;
import com.prospring.ch8.entities.SingerAudit;
import com.prospring.ch8.service.SingerAuditService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class SpringAuditJPADemo {
	public static void main(String... args) {
		 GenericApplicationContext ctx =
		 new AnnotationConfigApplicationContext(DBInitializer.class); //replaced AuditConfig.class
		 SingerAuditService singerAuditService = ctx.getBean(SingerAuditService.class);
		 List<SingerAudit> singers = singerAuditService.findAll();
		 listSingers(singers);
		 
		 System.out.println("Add new singer");
		 SingerAudit singer = new SingerAudit();
		 singer.setFirstName("BB");
		 singer.setLastName("King");
		 singer.setBirthDate(new Date(
		 (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
		 singerAuditService.save(singer);
		 singers = singerAuditService.findAll();
		 listSingers(singers);
		 singer = singerAuditService.findById(4l);
		 System.out.println("");
		 System.out.println("Singer with id 4:" + singer);
		 System.out.println("");
		 System.out.println("Update singer");
		 singer.setFirstName("John Clayton");
		 singerAuditService.save(singer);
		 singers = singerAuditService.findAll();
		 listSingers(singers);
		 ctx.close();
}
	private static void listSingers(List<SingerAudit> singerAudits) {
		 System.out.println("");
		 System.out.println("Listing singers without details:");
		 for (SingerAudit audit: singerAudits) {
		 System.out.println(audit);
		 System.out.println();
		 }
	}
}
		 
