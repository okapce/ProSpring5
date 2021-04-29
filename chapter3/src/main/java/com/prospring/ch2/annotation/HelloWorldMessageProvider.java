package com.prospring.ch2.annotation;

import org.springframework.stereotype.Component;

import com.prospring.ch2.decoupled.MessageProvider;

@Component("provider")
public class HelloWorldMessageProvider implements MessageProvider{
	@Override
	 public String getMessage() {
	 return "Hello World!";
	 }
}
