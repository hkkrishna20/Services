
package com.task.taskmanager.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
import com.task.taskmanager.repository.TaskMangerRepository;
import com.task.taskmanager.service.GenericTaskManagerServiceImpl;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	GenericTaskManagerServiceImpl taskService;

	@Autowired
	TaskMangerRepository repo;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Task>> getAllPosts(Pageable pageable) {

		Iterable<Task> Task = taskService.getAll();
		if (((List<Task>) Task).isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>((List<Task>) Task, HttpStatus.OK);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Task> get(@PathVariable("id") String id) {
		Task inc = taskService.get(id).get();
		if (inc == null) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(inc, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody Task Task) {
		taskService.add(Task);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Task Task) {
		Task inc = taskService.find(Task.getId()).get();
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			taskService.update(Task);
			return new ResponseEntity<Void>(HttpStatus.OK);

		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
		Task inc = taskService.find(id).get();
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			taskService.remove(inc);
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
	
	
	// api to return next two days 

	@RequestMapping(value = "/getDays", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Task>> getDays(Pageable pageable) {
		
		Calendar calendar = Calendar.getInstance();

		// get a date to represent "today"
		Date today = calendar.getTime();
		System.out.println("today:    " + today);

		// add one day to the date/calendar
		calendar.add(Calendar.DAY_OF_YEAR, 1);

		// now get "tomorrow"
		Date tomorrow = calendar.getTime();

		// print out tomorrow's date
		System.out.println("tomorrow: " + tomorrow);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate)); // 2016/11/16
		SimpleDateFormat formatter2=new SimpleDateFormat("dd-MM-yyyy");  

		System.out.println(dtf.format(localDate)); // 2016/11/16
		
		String[] arr = { dtf.format(localDate),formatter2.format(tomorrow) };

		List<Task> Task = repo.findByDeadlineIn(Arrays.asList(arr));
		if (((List<Task>) Task).isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>((List<Task>) Task, HttpStatus.OK);
	}

}