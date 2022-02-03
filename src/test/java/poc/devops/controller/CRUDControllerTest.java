package poc.devops.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import poc.devops.bean.model.Element;
import poc.devops.service.ElementService;

@WebMvcTest(CRUDController.class)
public class CRUDControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ElementService service;
	
	
	@Test
	public void testGet200() throws Exception {
		// set variables
		String name = "Enrico Fermi";
		
		// set mocks
		when(service.getAll()).thenReturn(Collections.singletonList(new Element(name, 1)));
		
		// execute test
		this.mockMvc.perform(get("/crud"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(name)));
	}
	
	
	@Test
	public void testGetName200() throws Exception {
		// set variables
		String name = "Enrico Fermi";
		List<Element> list = Collections.singletonList(new Element(name, 1));
		
		// set mocks
		when(service.get(anyString())).thenReturn(list);
		
		// execute test
		this.mockMvc.perform(get("/crud/"+name))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(name)))
	                .andExpect(jsonPath("$.elements", Matchers.hasSize(1)))
	                .andExpect(jsonPath("$.elements[0].name", Matchers.is(name)));
	}
	
	
	@Test
	public void testPost400() throws Exception {
		// set variables
		String json = "{\"points\": 1}";
		
		// set mocks
//		when(service.add(any())).thenReturn(Collections.singletonList(element));
		
		// execute test
		this.mockMvc.perform(post("/crud").contentType(MediaType.APPLICATION_JSON)
										  .content(json)
										  .characterEncoding("utf-8")
										  .accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Invalid input: the field [name] must not be blank")))
	                .andExpect(jsonPath("$.errors", Matchers.hasSize(1)))
	                .andExpect(jsonPath("$.errors[0].field", Matchers.is("name")));
	}
	
	
	@Test
	public void testPost201() throws Exception {
		// set variables
		String name = "Enrico Fermi";
		String json = "{\"name\": \""+name+"\",\"points\": 1}";
		
		// set mocks
		when(service.add(any())).thenReturn(Collections.singletonList(new Element(name, 1)));
		
		// execute test
		this.mockMvc.perform(post("/crud").contentType(MediaType.APPLICATION_JSON)
										  .content(json)
										  .characterEncoding("utf-8")
										  .accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isCreated())
					.andExpect(content().string(containsString(name)))
	                .andExpect(jsonPath("$.elements", Matchers.hasSize(1)))
	                .andExpect(jsonPath("$.elements[0].name", Matchers.is(name)));
	}
    
}