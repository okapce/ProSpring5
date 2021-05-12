package com.prospring.ch9.services;

import com.prospring.ch9.entities.Singer;
import com.prospring.ch9.repos.SingerRepository;
//import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("singerService")
//@Transactional
@Repository
public class SingerServiceImpl implements SingerService {
	@Autowired
	private SingerRepository singerRepository;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	public void setSingerRepository(SingerRepository singerRepository) {
		 this.singerRepository = singerRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Singer> findAll() {
		 //return null;//Lists.newArrayList(singerRepository.findAll());
		 List<Singer> list = new ArrayList<Singer>((Collection<? extends Singer>) singerRepository.findAll());
		 return list;
	}
	
	@Override
	public List<Singer> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Singer> findByFirstNameAndLastName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Singer findById(Long id) {
		return singerRepository.findById(id).get();
	}

	@Override
	public Singer save(Singer singer) {
		return singerRepository.save(singer);
	}
	
	@Override
	public long countAll() {
		return transactionTemplate.execute(
				transactionStatus -> em.createNamedQuery(Singer.COUNT_ALL, Long.class).getSingleResult());
	}
	/* following code is the expanded version :
	 public long countAll() {
		 return transactionTemplate.execute(new TransactionCallback<Long>() {
			public Long doInTransaction(TransactionStatus transactionStatus) {
			 	return em.createNamedQuery(Singer.COUNT_ALL, Long.class).getSingleResult();
		 	}
		 });
	 }
	*/
	
	
	/*
	@Override
	@Transactional(propagation = Propagation.NEVER)//propagation.never disallows count to be managed by entityManager //(readOnly=true)
	public long countAll() {
		return singerRepository.countAllSingers();
	}
	*/

}
