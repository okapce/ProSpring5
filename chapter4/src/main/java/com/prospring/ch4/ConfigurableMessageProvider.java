package com.prospring.ch4;

import com.prospring.ch2.decoupled.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("provider")
public class ConfigurableMessageProvider {
	private String message;
	 public ConfigurableMessageProvider(
	 @Value("Love on the weekend")String message) {
	 this.message = message;
	 }
	 
	 public String getMessage() {
	 return this.message;
	 }
}
