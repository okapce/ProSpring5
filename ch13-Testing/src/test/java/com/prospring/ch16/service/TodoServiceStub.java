package com.prospring.ch16.service;

import java.util.Arrays;
import java.util.List;

import com.prospring.ch13.service.TodoService;

public class TodoServiceStub implements TodoService{

	@Override
	public List<String> retrieveTodos(String user) {
		// TODO Auto-generated method stub
		return Arrays.asList("Spring one", "Spring two", "three");
	}

	@Override
	public void deleteTodo(String todo) {
		// TODO Auto-generated method stub
		
	}

}
