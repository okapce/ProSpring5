package com.prospring.ch3.sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class TrickyTarget {
	 Foo fooOne;
	 Foo fooTwo;
	 Bar bar;
	 
	 public TrickyTarget() {
		 System.out.println("Target.constructor()");
	 }
	 
	 public TrickyTarget(Foo fooOne) {
		 System.out.println("Target(Foo) called");
	 }
	
	 public TrickyTarget(Foo fooOne, Bar bar) {
		 System.out.println("Target(Foo, Bar) called");
	 }
	 
	 @Autowired
	 public void setFooOne(Foo fooOne) {
		 this.fooOne = fooOne;
		 System.out.println("Property fooOne set");
	 }
	 
	 @Autowired
	 public void setFooTwo(Foo foo) {
		 this.fooTwo = foo;
		 System.out.println("Property fooTwo set");
	 }
	 
	 @Autowired
	 public void setBar(Bar bar) {
		 this.bar = bar;
		 System.out.println("Property bar set");
	 }
	 
	 public static void main(String... args) {
	 GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
	 
	 ctx.load("classpath:spring/app-context-04.xml");
	 ctx.refresh();
	 
	 TrickyTarget t = ctx.getBean(TrickyTarget.class);
	 
	 ctx.close();
	 }
}
