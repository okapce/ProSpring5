package com.prospring.ch8.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prospring.ch8.view.SingerSummary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SingerSummaryServiceImpl  implements SingerSummaryService {
	 @PersistenceContext
	 private EntityManager em;
	 @Transactional(readOnly = true)
	 @Override
	 public List<SingerSummary> findAll() {
		 List<SingerSummary> result = em.createQuery(
				 "select new com.prospring.ch8.view.SingerSummary("
				 + "s.firstName, s.lastName, a.title) from Singer s "
				 + "left join s.albums a "
				 + "where a.releaseDate=(select max(a2.releaseDate)"
				 + "from Album a2 where a2.singer.id = s.id)",
				 SingerSummary.class).getResultList();
				 return result;
	 }
}
