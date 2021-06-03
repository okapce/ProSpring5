package com.prospring.ch2.annotated;

import org.springframework.context.annotation.Bean;

import com.prospring.ch2.decoupled.HelloWorldMessageProvider;
import com.prospring.ch2.decoupled.MessageProvider;
import com.prospring.ch2.decoupled.MessageRenderer;
import com.prospring.ch2.decoupled.StandardOutMessageRenderer;

public class HelloWorldConfig {
	@Bean
	public MessageProvider provider() {
		return new HelloWorldMessageProvider();
	}
	
	@Bean
	public MessageRenderer renderer() {
		MessageRenderer renderer = new StandardOutMessageRenderer();
		renderer.setMessageProvider(provider());
		return renderer;
	}
}
