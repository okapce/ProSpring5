package com.prospring.ch3.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StopWatch;

import com.prospring.ch3.DemoBean;
import com.prospring.ch3.Singer;

public class LookupConfigDemo {

	@Configuration
	@ComponentScan(basePackages = {"com.prospring.ch3.annotated"})
	public static class LookupConfig{}
	
	public static void main(String... args) {
		 GenericApplicationContext ctx =
		 new AnnotationConfigApplicationContext(LookupConfig.class);
		 DemoBean abstractBean = ctx.getBean("abstractLookupBean", DemoBean.class);
		 DemoBean standardBean = ctx.getBean("standardLookupBean", DemoBean.class);
		 displayInfo("abstractLookupBean", abstractBean);
		 displayInfo("standardLookupBean", standardBean);
		 ctx.close();
	 }
	
	public static void displayInfo(String beanName, DemoBean bean) {
		 Singer singer1 = bean.getMySinger();
		 Singer singer2 = bean.getMySinger();
		 
		 System.out.println("" + beanName + ": Singer Instances the Same? "+ (singer1 == singer2));
		 
		 StopWatch stopWatch = new StopWatch();
		 stopWatch.start("lookupDemo");
		 for (int x = 0; x < 100000; x++) {
		 Singer singer = bean.getMySinger();
		 singer.sing();
		 }
		 
		 stopWatch.stop();
		 
		 System.out.println("100000 gets took "+ stopWatch.getTotalTimeMillis() + " ms");
	}
}