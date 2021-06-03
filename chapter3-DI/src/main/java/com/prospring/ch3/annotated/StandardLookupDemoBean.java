package com.prospring.ch3.annotated;

import org.springframework.stereotype.Component;

import com.prospring.ch3.DemoBean;
import com.prospring.ch3.Singer;

@Component("standardLookupBean")
public class StandardLookupDemoBean implements DemoBean{
	private Singer mySinger;
	
	public void setMySinger(Singer mySinger) {
		this.mySinger = mySinger;
	}
	
	@Override
	public Singer getMySinger() {
		return this.mySinger;
	}
	
	@Override
	public void doSomething() {
		mySinger.sing();
	}
}
