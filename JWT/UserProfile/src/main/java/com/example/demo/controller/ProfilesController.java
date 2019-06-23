package com.example.demo.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Profile;
import com.example.demo.model.Profile2;
import com.example.demo.service.AppUserDetailsService;
import com.example.demo.service.ProfileService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin("*")
public class ProfilesController {
	
	int user_account;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	
	
	@GetMapping("/search/{user_account}")
	public ResponseEntity<?>findbyid(@PathVariable("user_account") Integer user_account){
		Profile result = profileService.findbyid(user_account);
		this.user_account = user_account;
		return new ResponseEntity<>(result , HttpStatus.OK);
	}
	
	@PostMapping("/send/{email_id}")
	public ResponseEntity<?>SendToOtp(@PathVariable("email_id") String email_id){
		Profile result = profileService.SendToOtp(user_account , email_id);
		return new ResponseEntity<>(result , HttpStatus.OK);
	}
	
	@PostMapping("/send")
	public ResponseEntity<?> SendToOtp1(@RequestBody Profile2 profile){

		System.out.println("email id from frontend "+profile);
		profileService.SendToOtp1(profile);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?>login(@RequestBody Profile login){
		
		System.out.print("hey");
		String username = login.getUsername();
		String password = login.getUser_password();
		
		UserDetails userdetails = appUserDetailsService.loadUserByUsername(username);
		Profile result = profileService.findByUsername(username);
		if(password.equals(userdetails.getPassword())) {
			
			return new ResponseEntity<>(result , HttpStatus.OK);
		}
		return new ResponseEntity<>("Wrong password" , HttpStatus.OK);
	}
	
	@PostMapping("/login1")
	public String login1(@RequestBody Profile login) throws ServletException {

	    String jwtToken = "";

	    if (login.getUsername() == null || login.getUser_password() == null) {
	        throw new ServletException("Please fill in username and password");
	    }

	    String username = login.getUsername();
	    String password = login.getUser_password();

	    Profile user = profileService.findByUsername(username);

	    if (user == null) {
	        throw new ServletException("User email not found.");
	    }

	    String pwd = user.getUser_password();

	    if (!password.equals(pwd)) {
	        throw new ServletException("Invalid login. Please check your name and password.");
	    }

	    jwtToken = Jwts.builder().setSubject(username).claim("roles", "user").setIssuedAt(new Date())
	            .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

	    return jwtToken;
	}
}
