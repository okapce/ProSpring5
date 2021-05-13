package com.prospring.ch10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospring.ch10.entities.Singer;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service("singerValidationService")
public class SingerValidationService {
	 
	@Autowired
	private Validator validator;
	
	public Set<ConstraintViolation<Singer>>	 validateSinger(Singer singer) {
		return validator.validate(singer);
	}
}
