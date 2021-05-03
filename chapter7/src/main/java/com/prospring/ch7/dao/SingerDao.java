package com.prospring.ch7.dao;

import java.util.List;

import com.prospring.ch6.entities.Singer;

public interface SingerDao {
	List<Singer> findAll();
	 List<Singer> findAllWithAlbum();
	 Singer findById(Long id);
	 Singer save(Singer contact);
	 void delete(Singer contact);
}
