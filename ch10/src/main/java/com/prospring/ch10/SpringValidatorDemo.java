package com.prospring.ch10;

import com.prospring.ch10.config.ValAppConfig;
import com.prospring.ch10.entities.Singer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SpringValidatorDemo {
	private static Logger logger =	 LoggerFactory.getLogger(SpringValidatorDemo.class);
	
	public static void main(String... args) {
		 GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ValAppConfig.class);
		 
		 Singer singer = new Singer();
		 singer.setFirstName(null);
		 singer.setLastName("Mayer");
		 
		 System.out.println("Validating:");
		 Validator singerValidator = ctx.getBean("singerValidator",	 Validator.class);
		 BeanPropertyBindingResult result =	 new BeanPropertyBindingResult(singer, "John");
		 ValidationUtils.invokeValidator(singerValidator, singer, result);
		 List<ObjectError> errors = result.getAllErrors();
		 
		 logger.info("No of validation errors: " + errors.size());
		 errors.forEach(e -> logger.info(e.getCode()));
		 
		 ctx.close();
	}
}
