package com.prospring.ch8.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prospring.ch8.Singer_;
import com.prospring.ch8.entities.Singer;

import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {
	 final static String ALL_SINGER_NATIVE_QUERY = "select id, first_name, last_name, birth_date, version from singer";
			 private static Logger logger =	 LoggerFactory.getLogger(SingerServiceImpl.class);
			 private Log log = LogFactory.getLog(SingerServiceImpl.class);
			 
			 @PersistenceContext
			 private EntityManager em;
			 
			 @Transactional(readOnly=true)
			 @Override
			 public List<Singer> findAll() {
				 return em.createNamedQuery(Singer.FIND_ALL, Singer.class).getResultList();
			 }
			 
			 @Transactional(readOnly=true)
			 @Override
			 public List<Singer> findAllWithAlbum() {
				 List<Singer> singers = em.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
				 return singers;
			 }
			 
			 @Transactional(readOnly=true)
			 @Override
			 public Singer findById(Long id) {
				TypedQuery<Singer> query = em.createNamedQuery(Singer.FIND_SINGER_BY_ID, Singer.class);
				query.setParameter("id", id);
				return query.getSingleResult();
			 }
			 
			 @Override
			 public Singer save(Singer singer) {
				 if (singer.getId() == null) {
					 logger.info("Inserting new singer");
					 em.persist(singer);
					 } else {
					 em.merge(singer);
					 logger.info("Updating existing singer");
					 }
				 logger.info("Singer saved with id: " + singer.getId());
				 return singer;
			 }
			 
			@Override
			public void delete(Singer singer) {
				 Singer mergedSinger = em.merge(singer);
				 em.remove(mergedSinger);
				 logger.info("Singer with id: " + singer.getId() + " deleted successfully");
			}
			 
			@Transactional(readOnly=true)
			@Override
			public List<Singer> findAllByNativeQuery() {
				 return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY,Singer.class).getResultList();			 
			}
			
			@Transactional(readOnly=true)
			@Override
			public List<Singer> findByCriteriaQuery(String firstName, String lastName) {
				 log.info("Finding singer for firstName: " + firstName + " and lastName: " + lastName);
				 //instance criteriabuilder
				 CriteriaBuilder cb = em.getCriteriaBuilder();
				 //query creation as Singer type
				 CriteriaQuery<Singer> criteriaQuery = cb.createQuery(Singer.class);
				 //entity class passed, resulting in root object specified to entity
				 Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
				 //fetch relations
				 singerRoot.fetch(Singer_.albums, JoinType.LEFT);
				 singerRoot.fetch(Singer_.instruments, JoinType.LEFT);
				 //select root as return type
				 criteriaQuery.select(singerRoot).distinct(true);
				 
				 //conjunction of one or more restrictions is made through PErsistence conjunction
				 Predicate criteria = cb.conjunction();
				 
				 if (firstName != null) {
					 Predicate p = cb.equal(singerRoot.get(Singer_.firstName),
					 firstName);
					 criteria = cb.and(criteria, p);
				 }
				 
				 if (lastName != null) {
					 Predicate p = cb.equal(singerRoot.get(Singer_.lastName),
					 lastName);
					 criteria = cb.and(criteria, p);
				 }
				 //predicate is constructed with criteria and restrictions
				 criteriaQuery.where(criteria);
				 return em.createQuery(criteriaQuery).getResultList();
			}

}
