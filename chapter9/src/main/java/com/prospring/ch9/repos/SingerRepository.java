package com.prospring.ch9.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prospring.ch9.entities.Singer;

public interface SingerRepository extends CrudRepository<Singer, Long> {
	List<Singer> findByFirstName(String firstName);
	List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}