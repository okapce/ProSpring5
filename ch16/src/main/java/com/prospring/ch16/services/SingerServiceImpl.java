package com.prospring.ch16.services;

import java.util.ArrayList;
import java.util.List;
import com.prospring.ch16.repo.SingerRepository;
import com.prospring.ch16.entities.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;

@Transactional
@Service("singerService")
public class SingerServiceImpl implements SingerService {
	private SingerRepository singerRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Singer> findAll() {
		//List list = new ArrayList<>();
		//list.add(singerRepository.findAll());
		return Lists.newArrayList(singerRepository.findAll());
	}
	
	@Override
	@Transactional(readOnly=true)
	public Singer findById(Long id) {
		return singerRepository.findById(id).get();
	}
	
	@Override
	public Singer save(Singer singer) {
		return singerRepository.save(singer);
	}
	
	@Autowired
	public void setSingerRepository(SingerRepository singerRepository) {
		this.singerRepository = singerRepository;
	}
	
	@Override
	public Page<Singer> findAllByPage(Pageable pageable) {
		return singerRepository.findAll(pageable);
	}
}
