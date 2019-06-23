
package com.task.taskmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.taskmanager.models.entity.Task;
import com.task.taskmanager.models.entity.User;
import com.task.taskmanager.repository.LabelMangerRepository;
import com.task.taskmanager.repository.TaskMangerRepository;
import com.task.taskmanager.repository.UserRepository;
import com.task.taskmanager.service.GenericUserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	GenericUserServiceImpl userService;

	@Autowired
	UserRepository userre;


	@Autowired
	LabelMangerRepository labelre;

	@Autowired
	TaskMangerRepository task;
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<User>> getAllPosts(Pageable pageable) {

		Iterable<User> metadata = userre.findAll();
		if (((List<User>) metadata).isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>((List<User>) metadata, HttpStatus.OK);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces =  {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

	public ResponseEntity<User> get(@PathVariable("id") String id) {
		User inc = userService.get(id).get();
		if (inc == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(inc, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody User user) {
		
		for(Task tasks: user.getListTasks()) {
			task.save(tasks);
			labelre.save(tasks.getLabel());
		}
		
		userre.save(user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody User user) {
		User inc = userService.find(user.getId()).get();
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			userService.update(user);
			return new ResponseEntity<Void>(HttpStatus.OK);

		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
		User inc = userService.find(id).get();
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			userService.remove(inc);
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
}