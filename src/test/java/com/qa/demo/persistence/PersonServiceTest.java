package com.qa.demo.persistence;

import org.hamcrest.CoreMatchers.*;

import static org.hamcrest.CoreMatchers.any;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.demo.persistence.domain.Person;
import com.qa.demo.persistence.domain.PersonAlreadyExistsException;
import com.qa.demo.persistence.domain.PersonDTO;
import com.qa.demo.persistence.domain.PersonService;
import com.qa.demo.persistence.repo.PersonRepo;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
	
	@Mock
	private PersonRepo perRepository;
	
	@Autowired
	@InjectMocks
	private PersonService perService;
	
	Person per1;
	Person per2;
	Person per3;
	
	List<Person> perList;
	
	@BeforeEach
	public void setUp() {
		per1 = new Person(1L, 22, "per1");
		per2 = new Person(2L, 25, "per2");
		per3 = new Person(3L, 45, "per3");
		perList = Arrays.asList(per1,per2,per3);
	}
	
	@AfterEach
	public void tearDown() {
		per1 = per2 = per3 = null;
		perList = null;
		
	}
	
	@Test
	@DisplayName("save-person-test")
	@Disabled
	public void given_Person_To_Save_Should_Return_The_Saved_Person() throws PersonAlreadyExistsException {
		when(perRepository.findByName(any())).thenReturn(null);
		when(perRepository.save(any())).thenReturn(per1);
		PersonDTO savedPerson = perService.addPerson(per1);
		assertNotNull(savedPerson);
		assertEquals(1,savedPerson.getId());
		verify(perRepository,times(1)).findByName(any());
		verify(perRepository,times(1)).save(any());
	}
	
	@Test
	@DisplayName("save-person-throws-exception-test")
	
	public void given_Existing_Person_To_Save_Method_Should_Throw_Exception() throws PersonAlreadyExistsException {
		when(perRepository.findByName(any())).thenReturn(per1);
		
		assertThrows(PersonAlreadyExistsException.class,()-> perService.savePerson(per1));
	}
	


}
