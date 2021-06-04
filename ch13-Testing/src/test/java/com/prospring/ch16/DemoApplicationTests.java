package com.prospring.ch16;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.prospring.ch13.entities.Gender;
import com.prospring.ch13.entities.Student;
import com.prospring.ch13.repos.StudentRepository;

@DataJpaTest
class DemoApplicationTests {

	@Autowired
	private StudentRepository stRepo;
	
	@Test
	@DisplayName("Test if email exists")
	public void checkExistsEmail() {
		//given
		String email = "nickp@email.com";
		Student student = new Student("nick", email, Gender.MALE);
		stRepo.save(student);
		
		//when
		boolean exists = stRepo.selectExistsEmail(email);
		
		//then
		Assertions.assertTrue(exists);
		
	}

}
