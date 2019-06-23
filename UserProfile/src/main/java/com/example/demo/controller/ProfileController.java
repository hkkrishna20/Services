package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Login;
import com.example.demo.model.Profile;
import com.example.demo.model.Profile2;

import com.example.demo.service.ProfileService;

@RestController
@CrossOrigin("*")
public class ProfileController {

	int user_account;

	@Autowired
	private ProfileService profileService;

	
	@GetMapping("/search/{user_account}")
	public ResponseEntity<?> findbyid(@PathVariable("user_account") Integer user_account) {
		Profile result = profileService.findbyid(user_account);
		this.user_account = user_account;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/send/{email_id}")
	public ResponseEntity<?> SendToOtp(@PathVariable("email_id") String email_id) {
		Profile result = profileService.SendToOtp(user_account, email_id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/send")
	public ResponseEntity<?> SendToOtp1(@RequestBody Profile2 profile) {

		System.out.println("email id from frontend " + profile);
		profileService.SendToOtp1(profile);
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveAll", method = { RequestMethod.GET, RequestMethod.POST }, consumes = { "text/plain",
			"application/json", "application/xml", "application/text" })
	public Profile saveAll(@RequestBody Profile black) {
		return profileService.saveDetails(black);

	}

	@RequestMapping(value = "/checkPassword", method = { RequestMethod.GET, RequestMethod.POST }, consumes = {
			"text/plain", "application/json", "application/xml",
	"application/text" }, produces = {"text/plain", "application/json", "application/xml",
	"application/text"})
	public ResponseEntity<?> checkPass(@RequestBody Login black) {

		try {
			System.out.println();
			String pasword = profileService.getMd5(black.getUser_password());

			System.out.println("new password encryption" + pasword);
			Profile result = profileService.findbyid(black.getUser_account());
			System.out.println("password stored already" + result.getUser_password());

			if (result.getUser_password().equals(pasword)) {

				return new ResponseEntity<String>("{\"txt\":\"Successfully Login\"}", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("{\"txt\":\"Failed To Login\"}", HttpStatus.UNAUTHORIZED);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<String>("{\"txt\":\"\"}", HttpStatus.BAD_REQUEST);

		}

	}

}
