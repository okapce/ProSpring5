package com.prospring.ch16.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.prospring.ch16.entities.Singer;

public interface SingerService {
	List<Singer> findAll();
	Singer findById(Long id);
	Singer save(Singer singer);
	Page<Singer> findAllByPage(Pageable pageable);
}
