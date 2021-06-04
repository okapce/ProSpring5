package com.prospring.ch16;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.validation.ConstraintViolationException;

import com.prospring.ch13.DataSets;
import com.prospring.ch13.config.DataConfig;
import com.prospring.ch13.config.ServiceConfig;
import com.prospring.ch13.entities.Singer;
import com.prospring.ch13.service.SingerService;
import com.prospring.ch16.config.ServiceTestConfig;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ExtendWith(SpringExtension.class)//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceTestConfig.class, ServiceConfig.class, DataConfig.class})
@TestExecutionListeners({ServiceTestExecutionListener.class})
@ActiveProfiles("test")
public class SingerServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
	 
	@Autowired
	SingerService singerService;
	 
	@PersistenceContext
	private EntityManager em;
	 
	@DataSets(setUpDataSet= "/com/prospring/ch13/SingerServiceImplTest.xls")
	@Test
	public void testFindAll() throws Exception {
		List<Singer> result = singerService.findAll();
		assertNotNull(result);
		assertEquals(1, result.size());
	} 
	 
	 @DataSets(setUpDataSet= "/com//prospring/ch13/SingerServiceImplTest.xls")
	 @Test
	 public void testFindByFirstNameAndLastName_1() throws Exception {
		 Singer result = singerService.findByFirstNameAndLastName("John", "Mayer");
		 assertNotNull(result);
	 }
	 
	 @DataSets(setUpDataSet= "/com/prospring/ch13/SingerServiceImplTest.xls")
	 @Test
	 public void testFindByFirstNameAndLastName_2() throws Exception {
		 Singer result = singerService.findByFirstNameAndLastName("BB", "King");
		 assertNull(result);
	 }
}
