package com.prospring.ch10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan(basePackages = "com.prospring.ch10")
public class ValAppConfig {

	@Bean 
	LocalValidatorFactoryBean validator() {
		 return new LocalValidatorFactoryBean();
	}
}
