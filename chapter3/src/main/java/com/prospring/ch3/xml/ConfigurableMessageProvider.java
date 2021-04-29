package com.prospring.ch3.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.prospring.ch2.decoupled.MessageProvider;

//@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider{
	private String message= "Default message";
	
	//@Autowired
	public ConfigurableMessageProvider(//@Value("Configurable message")
		String message) {
		this.message=message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	//@Override
	public String getMessage() {
		return message;
	}

}
