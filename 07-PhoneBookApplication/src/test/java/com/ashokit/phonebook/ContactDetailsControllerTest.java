package com.ashokit.phonebook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ashokit.phonebook.controller.ContactDetailsController;
import com.ashokit.phonebook.entity.Contact;
import com.ashokit.phonebook.props.AppProperties;
import com.ashokit.phonebook.service.ContactDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ContactDetailsController.class)
public class ContactDetailsControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private AppProperties appProps;
 
    @MockBean
    private ContactDetailsServiceImpl contactDetailsServiceImpl;
    
    @Test
    public void test_saveContact() throws Exception {
    	when(contactDetailsServiceImpl.saveContact(Mockito.any())).thenReturn(true);
    	
    	Contact c = new Contact(101,"ashok","ashok@gmail.com",465345);
    	
    	ObjectMapper mapper= new ObjectMapper();
    	
    	String json=null;
    	try {
    		json=mapper.writeValueAsString(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
    	MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.post("/saveContact").contentType("application/json").content(json);
    	
    			 MvcResult requestResult = mockMvc.perform(builder).andReturn();
    	
         MockHttpServletResponse response = requestResult.getResponse();
         
         int status =response.getStatus();
         assertEquals(201, status);
    	
    	
    }
    
    
    @Test
    public void test_saveContact_01() throws Exception {
    	when(contactDetailsServiceImpl.saveContact(Mockito.any())).thenReturn(false);
    	
    	Contact c = new Contact(101,"ashok","ashok@gmail.com",465345);
    	
    	ObjectMapper mapper= new ObjectMapper();
    	
    	String json=null;
    	try {
    		json=mapper.writeValueAsString(c);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
    	MockHttpServletRequestBuilder builder= MockMvcRequestBuilders.post("/saveContact").contentType("application/json").content(json);
    	
    			 MvcResult requestResult = mockMvc.perform(builder).andReturn();
    	
         MockHttpServletResponse response = requestResult.getResponse();
         
         int status =response.getStatus();
         assertEquals(500, status);
    	
    	
    }

	/*
	 * @Test public void test_saveContact_03() throws Exception {
	 * when(contactDetailsServiceImpl.saveContact(Mockito.any())).thenThrow(
	 * Exception.class);
	 * 
	 * Contact c = new Contact(101,"ashok","ashok@gmail.com",465345);
	 * 
	 * ObjectMapper mapper= new ObjectMapper();
	 * 
	 * String json=null; try { json=mapper.writeValueAsString(c); } catch (Exception
	 * e) { // TODO: handle exception }
	 * 
	 * 
	 * MockHttpServletRequestBuilder builder=
	 * MockMvcRequestBuilders.post("/saveContact").contentType("application/json").
	 * content(json);
	 * 
	 * MvcResult requestResult = mockMvc.perform(builder).andReturn();
	 * 
	 * MockHttpServletResponse response = requestResult.getResponse();
	 * 
	 * int status =response.getStatus(); assertEquals(500, status);
	 * 
	 * }
	 */
    @Test
    public void getAllContacts_test_1() throws Exception {
    	
    when(contactDetailsServiceImpl.getAllContactDetails()).thenReturn(Collections.emptyList());
    
    MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/getAllContacts");
    
    MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
    
    MockHttpServletResponse response =mvcResult.getResponse();
    
    int status = response.getStatus();
    
    assertEquals(200, status);
    
    }
    
    
    @Test
    public void getAllContacts_test_2() throws Exception 
    {
    when(contactDetailsServiceImpl.getAllContactDetails()).thenThrow(RuntimeException.class);
    
     MockHttpServletRequestBuilder reqBuilder =MockMvcRequestBuilders.get("/getAllContacts");
     
    MvcResult mvcResult = mockMvc.perform(reqBuilder).andReturn();
    
    MockHttpServletResponse response =mvcResult.getResponse();
    
    int status = response.getStatus();
    
    assertEquals(200, status);
    
    }

    
}
