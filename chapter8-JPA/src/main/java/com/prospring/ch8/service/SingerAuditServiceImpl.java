package com.prospring.ch8.service;
import com.prospring.ch8.entities.SingerAudit;
import com.prospring.ch8.repos.SingerAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
//import com.google.common.collect.Lists;

@Service("singerAuditService")
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService {
	 @Autowired
	 private SingerAuditRepository singerAuditRepository;
	 
	 @Transactional(readOnly=true)
	 public List<SingerAudit> findAll() {
		  return null;//Lists.newArrayList(singerAuditRepository.findAll());
	 }
	 
	 public SingerAudit findById(Long id) {
		 return null;//singerAuditRepository.findOne(id).get();
	 }
	 
	 public SingerAudit save(SingerAudit singer) {
		 return null;// singerAuditRepository.save(singer);
	 }
}
