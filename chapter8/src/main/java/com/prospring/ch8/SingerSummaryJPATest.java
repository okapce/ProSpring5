package com.prospring.ch8;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.prospring.ch8.config.JpaConfig;
import com.prospring.ch8.service.SingerService;
import com.prospring.ch8.service.SingerSummaryService;
import com.prospring.ch8.service.SingerSummaryUntypeImpl;
import com.prospring.ch8.view.SingerSummary;

public class SingerSummaryJPATest {
	private static Logger logger = LoggerFactory.getLogger(SingerSummaryJPATest.class);

	public static void main(String[] args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
		//SingerSummaryUntypeImpl singerSummaryUntype = ctx.getBean(SingerSummaryUntypeImpl.class);
		SingerSummaryService singerSummaryService = ctx.getBean(SingerSummaryService.class);

		List<SingerSummary> singers = singerSummaryService.findAll();
		listSingerSummary(singers);

		 
		ctx.close();
	}
	
	private static void listSingerSummary(List<SingerSummary> singers) {
		 logger.info(" ---- Listing singers summary:");
		 for (SingerSummary singer : singers) {
			 logger.info(singer.toString());
		 }
	}
	
}
