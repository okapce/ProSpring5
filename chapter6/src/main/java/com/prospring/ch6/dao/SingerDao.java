package com.prospring.ch6.dao;

import java.util.List;

import com.prospring.ch6.entities.Singer;

public interface SingerDao {
	List<Singer> findAll();
	List<Singer> findByFirstName(String firstName);
	String findLastNameById(Long id);
	String findFirstNameById(Long id);
	List<Singer> findAllWithDetail();
	List<Singer> findAllWithAlbums();
	String findNameById(Long id);
	
	void insert(Singer singer);
	void update(Singer singer);
	void delete(Long singerId);
	
	void insertWithDetail(Singer singer);
	void insertWithAlbum(Singer singer);
	
}
