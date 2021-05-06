package com.prospring.ch8.service;

import java.util.List;

import com.prospring.ch8.entities.SingerAudit;

public interface SingerAuditService {
	List<SingerAudit> findAll();
	SingerAudit findById(Long id);
	SingerAudit save(SingerAudit singer);
}
