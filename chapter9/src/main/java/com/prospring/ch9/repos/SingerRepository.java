package com.prospring.ch9.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prospring.ch9.entities.Singer;

public interface SingerRepository extends CrudRepository<Singer, Long> {
	@Query(value="select count(s) from \"MUSICDB\".singer s", nativeQuery = true) Long countAllSingers();
	List<Singer> findByFirstName(String firstName);
	List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}