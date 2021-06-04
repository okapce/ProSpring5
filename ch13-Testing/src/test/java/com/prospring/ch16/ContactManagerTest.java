package com.prospring.ch16;

import java.util.List;

import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.prospring.ch13.service.ContactManager;

public class ContactManagerTest {
	ContactManager contact;
	
	@BeforeAll
	public static void setUpAll() { //don't forget 'static' 
		System.out.println("Should print before all tests");
		
	}
	
	@BeforeEach
	public void setUp() {
		contact = new ContactManager();
	}
	
	
	@Test
	@Disabled
	@DisplayName("Disabled basic create contact test")
	public void shouldCreateContact() {
		contact.addContact("john", "doe", "0562246998");
		Assertions.assertFalse(contact.getAllContacts().isEmpty());
		Assertions.assertEquals(1, contact.getAllContacts().size());
		Assertions.assertTrue(contact.getAllContacts().stream()
				.filter(contactTest -> contactTest.getFirstName().equals("john")
						&& contactTest.getLastName().equals("doe")
						&& contactTest.getPhoneNumber().equals("0562246998"))
				.findAny().isPresent());
	}
	
	@Test
	@DisplayName("Test contact creation on Dev Machine")
	public void shouldCreateContactOnDev() {
		Assertions.assertTrue("DEV".equals(System.getProperty("ENV")));
		contact.addContact("john", "doe", "0562246998");
		Assertions.assertFalse(contact.getAllContacts().isEmpty());
		Assertions.assertEquals(1, contact.getAllContacts().size());
		
	}
	
	@RepeatedTest(5)
	@DisplayName("Repeat Test contact creation")
	public void shouldCreateContactRepeatdetly() {
		contact.addContact("john", "doe", "0562246998");
		Assertions.assertFalse(contact.getAllContacts().isEmpty());
		Assertions.assertEquals(1, contact.getAllContacts().size());
		
	}
	
	@DisplayName("Paramatized test using value source")
	@ParameterizedTest
	@ValueSource(strings = {"0562246998","0123456789", "1234567890"})
	public void shouldCreateContactUsingValueSource(String phoneNumber) {
		contact.addContact("john", "doe", phoneNumber);
		Assertions.assertFalse(contact.getAllContacts().isEmpty());
		Assertions.assertEquals(1, contact.getAllContacts().size());
		
	}
	
	@DisplayName("Paramatized test using method source")
	@ParameterizedTest
	@MethodSource("phoneNumberList")
	public void shouldCreateContactUsingMothodSource(String phoneNumber) {
		contact.addContact("john", "doe", phoneNumber);
		Assertions.assertFalse(contact.getAllContacts().isEmpty());
		Assertions.assertEquals(1, contact.getAllContacts().size());
		
	}
	
	public static List<String> phoneNumberList(){
		return java.util.Arrays.asList("0562246998","0123456789", "1234567890");
	}
	
	@DisplayName("Paramatized test using Csv source")
	@ParameterizedTest
	@CsvSource({"0562246998","0123456789", "1234567890"})
	public void shouldCreateContactUsingCSVSource(String phoneNumber) {
		contact.addContact("john", "doe", phoneNumber);
		Assertions.assertFalse(contact.getAllContacts().isEmpty());
		Assertions.assertEquals(1, contact.getAllContacts().size());
		
	}
	
	@DisplayName("Paramatized test using Csv File source")
	@ParameterizedTest
	@CsvFileSource(resources = "/data.csv")
	public void shouldCreateContactUsingCSVFileSource(String phoneNumber) {
		contact.addContact("john", "doe", phoneNumber);
		Assertions.assertFalse(contact.getAllContacts().isEmpty());
		Assertions.assertEquals(1, contact.getAllContacts().size());
		
	}
	
	
	
	@Test
	@DisplayName("Should not create contact if firstName is null")
	public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
		Assertions.assertThrows(RuntimeException.class, () -> 
		{contact.addContact(null, "doe", "0562246998");});
	}
	
	@Test
	@DisplayName("Should not create contact if lastName is null")
	public void shouldThrowRuntimeExceptionWhenLAstNameIsNull() {
		Assertions.assertThrows(RuntimeException.class, () -> 
		{contact.addContact("john", null, "0562246998");});
	}
	
	@Test
	@DisplayName("Should not create contact if phoneNumber is null")
	public void shouldThrowRuntimeExceptionWhenPhoneNumberIsNull() {
		ContactManager contact = new ContactManager();
		Assertions.assertThrows(RuntimeException.class, () -> 
		{contact.addContact("john", "doe", null);});
	}
	
	@Test
	@DisplayName("should only run on Windows")
	@EnabledOnOs(value = OS.WINDOWS, disabledReason = "Enabled only on windows")
	public void shouldCreateContactOnlyOnWindows() {
		contact.addContact("john", "doe", "0562246998");
		Assertions.assertFalse(contact.getAllContacts().isEmpty());
		Assertions.assertEquals(1, contact.getAllContacts().size());
		Assertions.assertTrue(contact.getAllContacts().stream()
				.filter(contactTest -> contactTest.getFirstName().equals("john")
						&& contactTest.getLastName().equals("doe")
						&& contactTest.getPhoneNumber().equals("0562246998"))
				.findAny().isPresent());
	}
	
	@Test
	@DisplayName("should only run on MAC OS")
	@EnabledOnOs(value = OS.MAC, disabledReason = "Enabled only on MAC")
	public void shouldCreateContactOnlyOnMAC() {
		contact.addContact("john", "doe", "0562246998");
		Assertions.assertFalse(contact.getAllContacts().isEmpty());
		Assertions.assertEquals(1, contact.getAllContacts().size());
		Assertions.assertTrue(contact.getAllContacts().stream()
				.filter(contactTest -> contactTest.getFirstName().equals("john")
						&& contactTest.getLastName().equals("doe")
						&& contactTest.getPhoneNumber().equals("0562246998"))
				.findAny().isPresent());
	}
	
	@Nested
	class RepeatedNestedTest{

		@RepeatedTest(5)
		@DisplayName("Repeat Test contact creation")
		public void shouldCreateContactRepeatdetly() {
			contact.addContact("john", "doe", "0562246998");
			Assertions.assertFalse(contact.getAllContacts().isEmpty());
			Assertions.assertEquals(1, contact.getAllContacts().size());
			
		}
	}
	
	@Nested
	class ParametizedNestedTest{
		
		@DisplayName("Paramatized test using Csv source")
		@ParameterizedTest
		@CsvSource({"0562246998","0123456789", "1234567890"})
		public void shouldCreateContactUsingCSVSource(String phoneNumber) {
			contact.addContact("john", "doe", phoneNumber);
			Assertions.assertFalse(contact.getAllContacts().isEmpty());
			Assertions.assertEquals(1, contact.getAllContacts().size());
			
		}
		
		@DisplayName("Paramatized test using Csv File source")
		@ParameterizedTest
		@CsvFileSource(resources = "/data.csv")
		public void shouldCreateContactUsingCSVFileSource(String phoneNumber) {
			contact.addContact("john", "doe", phoneNumber);
			Assertions.assertFalse(contact.getAllContacts().isEmpty());
			Assertions.assertEquals(1, contact.getAllContacts().size());
			
		}
	}
	
	
	@AfterEach
	public void tearDown() {
		System.out.println("should execute at end of every method test");
	}
	
	@AfterAll
	public static void tearDownAll() {
		System.out.println("Should be executed at end of the test");
	}
}
