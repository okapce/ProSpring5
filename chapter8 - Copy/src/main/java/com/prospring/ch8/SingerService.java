package com.prospring.ch8;

import java.util.List;

import com.prospring.ch8.entities.Singer;

public interface SingerService {
	 List<Singer> findAll();
	 List<Singer> findAllWithAlbum();
	 Singer findById(Long id);
	 Singer save(Singer singer);
	 void delete(Singer singer);
	 List<Singer> findAllByNativeQuery();
	 List<Singer> findByCriteriaQuery(String firstName, String lastName);
}
