package com.prospring.ch7.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.prospring.ch6.entities.Singer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@Transactional
@Repository("singerDao")
public class SingerDaoImpl  implements SingerDao {
	
	private static final Log logger = LogFactory.getLog(SingerDaoImpl.class);
	
	 private SessionFactory sessionFactory;
	 public SessionFactory getSessionFactory() {
	 return sessionFactory;
	 }
	 @Resource(name = "sessionFactory")
	 public void setSessionFactory(SessionFactory sessionFactory) {
	 this.sessionFactory = sessionFactory;
	 }
	 
	 @Transactional
	public List<Singer> findAll() {
		 return sessionFactory.getCurrentSession().createQuery("from \"MUSICDB\".Singer s").list();
	}
	@Override
	public List<Singer> findAllWithAlbum() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Singer findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Singer save(Singer contact) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(Singer contact) {
		// TODO Auto-generated method stub
		
	}

}
