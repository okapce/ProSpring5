package com.prospring.ch16;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;


import com.prospring.ch13.controller.SingerController;
import com.prospring.ch13.entities.Singer;
import com.prospring.ch13.entities.Singers;
import com.prospring.ch13.service.SingerService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

public class SingerControllerTest {

	private final List<Singer> singers = new ArrayList<>();
	
	@BeforeEach
	public void initSingers() {
		 Singer singer = new Singer();
		 singer.setId(1l);
		 singer.setFirstName("John");
		 singer.setLastName("Mayer");
		 singers.add(singer);
	 }
	
	@Test //JUnit runs this method to test
	 public void testList() throws Exception {
		//SingerService is mocked
		SingerService singerService = mock(SingerService.class);
		
		//mockito mocks SingerService.findAll(), used by SingerController
		when(singerService.findAll()).thenReturn(singers);
		SingerController singerController = new SingerController();
		
		
		//SingerService is mocked with reflectiontestutil.setField
		//reflection provides a collection of reflection based utility methods 
		//for use in unit and interation scenarios.
		ReflectionTestUtils.setField(singerController, "singerService", singerService);
		
		//model of singers, singerController.listaData is invocated
		ExtendedModelMap uiModel = new ExtendedModelMap();	
		uiModel.addAttribute("singers", singerController.listData());
		 
		//result is verified by calling assert method, checking that 
		//singer info is saved in model used by view (singerController)
		 Singers modelSingers = (Singers) uiModel.get("singers");
		 Assertions.assertEquals(1, modelSingers.getSingers().size());
		 
	 }
	
	@Test
	 public void testCreate() {
		
		 final Singer newSinger = new Singer();
		 newSinger.setId(999l);
		 newSinger.setFirstName("BB");
		 newSinger.setLastName("King");
		 
		 SingerService singerService = mock(SingerService.class);
		 when(singerService.save(newSinger)).thenAnswer(new Answer<Singer>() {
			 public Singer answer(InvocationOnMock invocation) throws Throwable {
				 singers.add(newSinger);
				 return newSinger;
			 }
		 });
	 
		 SingerController singerController = new SingerController();
		 ReflectionTestUtils.setField(singerController, "singerService", singerService);
		 
		 Singer singer = singerController.create(newSinger);
		 Assertions.assertEquals(Long.valueOf(999l), singer.getId());
		 Assertions.assertEquals("BB", singer.getFirstName());
		 Assertions.assertEquals("King", singer.getLastName());
		 Assertions.assertEquals(2, singers.size());
	 }
}
