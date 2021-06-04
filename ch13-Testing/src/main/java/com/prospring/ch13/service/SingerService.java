package com.prospring.ch13.service;

import java.util.List;

import com.prospring.ch13.entities.Singer;

public interface SingerService {

	List<Singer> findAll();

	List<Singer> findByFirstName(String firstName);

	Singer findById(Long id);

	Singer save(Singer singer);

	void delete(Singer singer);

	Singer findByFirstNameAndLastName(String firstName, String lastName);


}
