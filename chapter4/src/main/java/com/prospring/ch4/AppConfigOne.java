package com.prospring.ch4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.prospring.ch2.decoupled.MessageProvider;
import com.prospring.ch2.decoupled.MessageRenderer;
import com.prospring.ch2.decoupled.StandardOutMessageRenderer;

@Configuration
@PropertySource(value = "classpath:message.properties")
public class AppConfigOne {
	Environment env;
	@Bean
	 public ConfigurableMessageProvider messageProvider() {
	 return new ConfigurableMessageProvider(env.getProperty("message"));
	 }
	 @Bean(name = "messageRenderer")
	 public MessageRenderer messageRenderer() {
	 MessageRenderer renderer = new StandardOutMessageRenderer();
	 //renderer.setMessageProvider(messageProvider());
	 return renderer;
	 }
}
