package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import com.example.demo.model.Profile;
import com.example.demo.service.ProfileService;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserProfileControllerTest extends TestCase {

    @Autowired
    private MockMvc mockMvc;
    private Profile profile;
    @MockBean
    private ProfileService profileService;
    @InjectMocks
    private ProfileController profileController;

    private List<Profile> list = null;


    protected void setUp() throws Exception {
		
    	MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
        profile = new Profile();
        
        profile.setUser_account(1221);
        profile.setAge(23);
        profile.setEmail_id("madhuriranavare23@gmail.com");
        profile.setMobile_no("9130788763");
        profile.setUser_name("Madhuri");
        profile.setUser_password("Madhuri");
        
        
        list = new ArrayList();

        list.add(profile);

//		super.setUp();
	}

	protected void tearDown() throws Exception {
		
		super.tearDown();
	}

	public void testFindbyid() {
		int user_account = 1221;
		String expected;
		expected = "Madhuri";
		Profile result = profileService.findbyid(user_account);
		System.out.println(result.getUser_name());
		String actual =  result.getUser_name();
		
		assertEquals(expected, actual);
		
	}

	public void testSendToOtp() {
		fail("Not yet implemented");
	}

	public void testSendToOtp1() {
		fail("Not yet implemented");
	}

	public void testSaveAll() {
		fail("Not yet implemented");
	}

	public void testCheckPass() {
		fail("Not yet implemented");
	}

}
