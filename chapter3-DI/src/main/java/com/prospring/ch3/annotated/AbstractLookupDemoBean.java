package com.prospring.ch3.annotated;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import com.prospring.ch3.DemoBean;
import com.prospring.ch3.annotated.Singer;

@Component("abstractLookupBean")
public class AbstractLookupDemoBean implements DemoBean {
	@Lookup("singer")
	public com.prospring.ch3.Singer getMySinger() {
		return null;
	}
	 
	@Override
	public void doSomething() {
		 getMySinger().sing();
	}

}
