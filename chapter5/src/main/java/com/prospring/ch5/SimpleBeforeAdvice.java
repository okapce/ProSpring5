package com.prospring.ch5;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class SimpleBeforeAdvice  implements MethodBeforeAdvice {
	public static void main(String... args) {
		/*Initialize Target and PorxyFactory*/
		Guitarist johnMayer = new Guitarist();
		ProxyFactory pf = new ProxyFactory();
		
		/*set advice, set target and get proxy to target (weaving?)*/
		pf.addAdvice(new SimpleBeforeAdvice());
		pf.setTarget(johnMayer);
		Guitarist proxy = (Guitarist) pf.getProxy();
		
		proxy.sing();
	}
	
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("Before '" + method.getName() + "', tune guitar.");
	}
}
