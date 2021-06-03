package com.prospring.ch5.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prospring.ch5.Contact;
import com.prospring.ch5.introduction.IsModifiedAdvisor;

@Configuration
public class AppConfig {
	@Bean
	public Contact guitarist() {
		Contact guitarist = new Contact();
		guitarist.setName("John Mayer");
		return guitarist;
	}
	
	@Bean
	public Advisor advisor() {
		return new IsModifiedAdvisor();
	}
	
	@Bean ProxyFactoryBean bean() {
	ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
	proxyFactoryBean.setTarget(guitarist());
	proxyFactoryBean.setProxyTargetClass(true);
	proxyFactoryBean.addAdvisor(advisor());
	return proxyFactoryBean;
}
}
