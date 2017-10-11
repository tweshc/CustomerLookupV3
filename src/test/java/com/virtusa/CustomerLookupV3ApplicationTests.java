package com.virtusa;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceView;
import com.virtusa.model.*;
import static
org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.virtusa.controller.AddressController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerLookupV3ApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;
  
  @Autowired
  AddressController addressController;

  @Before
  public void init() throws Exception {
      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }
  
	@Test
	public void contextLoads() {
	}

  @Test
  public void testHomePage() throws Exception{
    assertEquals("index", addressController.homepage());
  }
  
  
  @Test
  public void shouldShowEnterCustomerId() throws Exception {
    mockMvc.perform(get("/enterCustomerId"))
        .andExpect(view().name("enterCustomerId"))
        .andExpect(model().attributeExists("address"));
  }
  
  @Test
  public void shouldPostShowEnterCustomerId() throws Exception {
    mockMvc
        .perform(post("/enterCustomerId")
        .contentType(MediaType.ALL_VALUE)
        .content("1001"));
  }
  
  @Test
  public void shouldShowEnterCustomerCity() throws Exception {
    mockMvc
        .perform(get("/enterCustomerCity"))
        .andExpect(view().name("enterCustomerCity"))
        .andExpect(model().attributeExists("address"));
  }
  
  @Test
  public void shouldPostShowEnterCustomerCity() throws Exception {
    mockMvc
      .perform(post("/enterCustomerCity")
          .contentType(MediaType.ALL_VALUE)
          .content("Jersey City"));
  }
  
  @Test
  public void shouldShowEnterCustomerState() throws Exception {
    mockMvc
        .perform(get("/enterCustomerState"))
        .andExpect(view().name("enterCustomerState"))
        .andExpect(model().attributeExists("address"));
  }
  
  @Test
  public void shouldPostShowEnterCustomerState() throws Exception {
    mockMvc
      .perform(post("/enterCustomerCity")
          .contentType(MediaType.ALL_VALUE)
          .content("Kolkata"));
  }

}