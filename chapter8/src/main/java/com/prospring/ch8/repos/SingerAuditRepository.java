package com.prospring.ch8.repos;

import org.springframework.data.repository.CrudRepository;

import com.prospring.ch8.entities.Singer;

public interface SingerAuditRepository extends CrudRepository<Singer, Long> {

}
