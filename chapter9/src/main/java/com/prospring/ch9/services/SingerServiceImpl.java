package com.prospring.ch9.services;

import com.prospring.ch9.entities.Singer;
import com.prospring.ch9.repos.SingerRepository;
//import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service("singerService")
@Transactional
public class SingerServiceImpl implements SingerService {
	private SingerRepository singerRepository;
	
	@Autowired
	public void setSingerRepository(SingerRepository singerRepository) {
		 this.singerRepository = singerRepository;
	}
	@Override
	@Transactional(readOnly = true)
	public List<Singer> findAll() {
		 return null;//Lists.newArrayList(singerRepository.findAll());
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

}
