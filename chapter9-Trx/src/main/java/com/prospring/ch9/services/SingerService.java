package com.prospring.ch9.services;

import java.util.List;

import com.prospring.ch9.entities.Singer;

public interface SingerService {
	 List<Singer> findAll();
	 List<Singer> findByFirstName(String firstName);
	 List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
	 Singer findById(Long id);
	 Singer save(Singer singer);
	long countAll();
}

