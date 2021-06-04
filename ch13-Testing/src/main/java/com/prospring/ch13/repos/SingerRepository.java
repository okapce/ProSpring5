package com.prospring.ch13.repos;

import org.springframework.data.repository.CrudRepository;

import com.prospring.ch13.entities.Singer;

import java.util.List;

public interface SingerRepository extends CrudRepository<Singer, Long> {

	List<Singer> findByFirstName(String firstName);

	Singer findByFirstNameAndLastName(String firstName, String lastName);
}
