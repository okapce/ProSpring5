package com.prospring.ch16;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.prospring.ch13.service.TodoBusinessImpl;
import com.prospring.ch13.service.TodoService;
import com.prospring.ch16.service.TodoServiceStub;

public class FirstMockitoTest {
	
	@Test
	public void test() {
		
		TodoService todoService = mock(TodoService.class); //new TodoServiceStub();
		
		List<String> todos = Arrays.asList("Spring one", "Spring two", "three");
		
		when(todoService.retrieveTodos("meaningless value")).thenReturn(todos);
		
		TodoBusinessImpl todoBImpl = new TodoBusinessImpl(todoService);
		
		
		
		List<String> filteredTodo = todoBImpl.retrieveTodosRelatedToSpring("meaningless value");
		Assertions.assertEquals(2, filteredTodo.size());
	}
}
