package com.prospring.ch8;

import java.util.List;

import com.prospring.ch8.data.repository.CrudRepository;
import com.prospring.ch8.entities.Singer;

public interface SingerRepository extends CrudRepository<Singer, Long> {
	 List<Singer> findByFirstName(String firstName);
	 List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}
