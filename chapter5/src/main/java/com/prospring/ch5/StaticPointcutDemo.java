package com.prospring.ch5;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import com.prospring.ch4.Singer;

public class StaticPointcutDemo {
	public static void main(String... args) {
		 GoodGuitarist johnMayer = new GoodGuitarist();
		 GreatGuitarist ericClapton = new GreatGuitarist();
		 Singer proxyOne;
		 Singer proxyTwo;
		 
		 Pointcut pc = new SimpleStaticPointcut();
		 //pointcut directs to all methods set in simplestaticpointcut
		 SimpleAdvice advice = new SimpleAdvice();
		 //advice shows where is meant to be executed/invoked
		 Advisor advisor = new DefaultPointcutAdvisor(pc, advice);
		 
		 ProxyFactory pf = new ProxyFactory();
		 pf.addAdvisor(advisor);
		 pf.setTarget(johnMayer);
		 proxyOne = (Singer)pf.getProxy();
		 
		 pf = new ProxyFactory();
		 pf.addAdvisor(advisor);
		 pf.setTarget(ericClapton);
		 proxyTwo = (Singer)pf.getProxy();
		 
		 proxyOne.sing();
		 proxyTwo.sing();
		 
		 }
}
