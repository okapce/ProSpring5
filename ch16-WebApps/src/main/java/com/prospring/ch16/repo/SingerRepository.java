package com.prospring.ch16.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.prospring.ch16.entities.Singer;

public interface SingerRepository extends PagingAndSortingRepository<Singer, Long> {
}
