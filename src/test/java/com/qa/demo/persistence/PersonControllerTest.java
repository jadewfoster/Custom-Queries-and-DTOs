package com.qa.demo.persistence;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.demo.persistence.domain.Person;
import com.qa.demo.persistence.domain.PersonController;
import com.qa.demo.persistence.domain.PersonDTO;
import com.qa.demo.persistence.domain.PersonService;

import io.swagger.v3.oas.models.media.MediaType;

@RestController
@RequestMapping("/person")
@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
	
	@Mock
	private PersonService perService;
	
	@Autowired
	@InjectMocks
	private PersonController perController;
	
	@Autowired
	MockMvc mockMvc;
	
	Person per1;
	Person per2;
	Person per3;
	
	List<Person> perList;
	List<PersonDTO> perListDTO;
	 
	@BeforeEach
	public void setUp() {
		per1 = new Person(1L, 22, "per1");
		per2 = new Person(2L, 25, "per2");
		per3 = new Person(3L, 45, "per3");
		perList = Arrays.asList(per1,per2,per3);
		
		mockMvc = MockMvcBuilders.standaloneSetup(perController).build();
	}
	
	@AfterEach
	public void tearDown() {
		per1 = per2 = per3 = null;
		perList = null;
		
	}
	
	@Test
	@DisplayName("save-person-test")
	public void given_Person_To_Save_Person_Should_Return_Person_As_JSON_With_Status_Created() throws Exception {
		when(perService.savePerson(any())).thenReturn(per1);
		mockMvc.perform(post("/api/v1/person-service/person")
				        .contentType(MediaType.APPLICATION_JSON)
				        .content(asJsonString(per1)))
		        .andExpect(status().isCreated())
		        .andExpect(jsonPath("$.name").value("per1"));
	}
	
	@Test
	@DisplayName("get-person-test")
	public void given_GetAllPeople_Should_Return_List() throws Exception {
		when(perService.getAllPeople()).thenReturn(perListDTO);
		mockMvc.perform(get("/api/v1/person-service/person")
				        .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].name").value("per2"));
	}

	
	public static String asJsonString(Object obj) {
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = null;
        try {
            jsonStr = Obj.writeValueAsString(obj);

            System.out.println(jsonStr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
	}
}
