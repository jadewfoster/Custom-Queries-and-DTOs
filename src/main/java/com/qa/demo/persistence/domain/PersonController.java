package com.qa.demo.persistence.domain;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/person-service")
public class PersonController {

	@Autowired
	private PersonService perService;
	
	@PostMapping("person")
	public ResponseEntity<?> savePerson(@RequestBody Person person) throws PersonAlreadyExistsException{
		ResponseEntity<?> responseEntity = null;
		
		try {
			PersonDTO createdPerson = this.perService.addPerson(person);
			responseEntity = new ResponseEntity<>(createdPerson,HttpStatus.CREATED);
		} catch(Exception e) {
			System.out.println(e);
			responseEntity = new ResponseEntity<>("Internal error occured. Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@GetMapping("employee")
	public ResponseEntity<?> getAllEmployees(){
		ResponseEntity<?>  responseEntity;
		try {
		List<PersonDTO> perList = this.perService.getAllPeople();
		responseEntity = new ResponseEntity<>(perList,HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("Internal error occured. Please try again!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	
	@GetMapping("employee/{id}")
	public ResponseEntity<?> getPersonById(@PathVariable("id") int id ) throws PersonNotFoundException{
		ResponseEntity<?> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<>(this.perService.getPersonById(id),HttpStatus.OK);
		} catch (PersonNotFoundException e) {
			throw e;
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured. Please try again!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@PutMapping("employee")
	public ResponseEntity<?> updateEmployee(@RequestBody Person person) throws PersonNotFoundException{
		ResponseEntity<?> responseEntity = null;
		
		try {
			PersonDTO createdPerson = this.perService.updatePerson(null, person);
			responseEntity = new ResponseEntity<>(createdPerson,HttpStatus.CREATED);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured. Please try again!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
	
	@DeleteMapping("employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") int id) throws PersonNotFoundException{
		ResponseEntity<?> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<>(this.perService.removePerson((long) id),HttpStatus.OK);
		} catch(Exception e) {
			responseEntity = new ResponseEntity<>("some internal error occured. Please try again!!!", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return responseEntity;
	}

}
