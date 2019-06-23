package com.task.taskmanager.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.taskmanager.models.entity.Categories;
import com.task.taskmanager.models.entity.Task;
import com.task.taskmanager.models.entity.User;
import com.task.taskmanager.repository.LabelMangerRepository;
import com.task.taskmanager.repository.TaskMangerRepository;
import com.task.taskmanager.repository.UserRepository;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	LabelMangerRepository labels;

	@Autowired
	TaskMangerRepository tasks;
	@Autowired
	UserRepository userrep;
	// Search operations on Task based on filters (Priority, Label, Time)
	// e.g. Find next 2 days tasks,

	@RequestMapping(value = "/byNextTwodays", method = RequestMethod.GET, produces = { "application/json" })

	public ResponseEntity<?> searchByNextTwodays() {
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
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");

		System.out.println(dtf.format(localDate)); // 2016/11/16

		String[] arr = { dtf.format(localDate), formatter2.format(tomorrow) };

		List<Task> Task = tasks.findByDeadlineIn(Arrays.asList(arr));
		if (((List<Task>) Task).isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>((List<Task>) Task, HttpStatus.OK);

	}

	// Find today’s task of particular label,
	@RequestMapping(value = "/byTodaysTask/{labelId}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> searchByTodaysTask(@PathVariable("labelId") String priorLabel) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate)); // 2016/11/16
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "foo");
		List<Task> list = tasks.findByDeadline(dtf.format(localDate));
		List<Task> response = new ArrayList<Task>();
		for (Task tas : list) {

			if (tas.getLabel().getCategory().compareTo(Categories.valueOf(priorLabel)) == 0) {

				response.add(tas);
			}
		}

		return new ResponseEntity<>(response, headers, HttpStatus.OK);

	}

	// Find all Critical and high priority tasks etc.
	@RequestMapping(value = "/byCriticalAndHigh", method = RequestMethod.GET, produces = { "application/json" })

	public ResponseEntity<?> searchByFindAllHighAndCritical() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "foo");
		List<Task> ttask = (List<com.task.taskmanager.models.entity.Task>) tasks.findAll();
		List<Task> response = new ArrayList<Task>();
		for (Task tt : ttask) {
			if (tt.getLabel().getCategory().compareTo(Categories.Critical) == 0
					|| tt.getLabel().getCategory().compareTo(Categories.High) == 0) {

				response.add(tt);
			}

		}
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/byPriority/{priorLabel}", method = RequestMethod.GET, produces = { "application/json" })

	public ResponseEntity<?> searchByPriority(@PathVariable("priorLabel") String priorLabel) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "foo");
		List<Task> ttask = (List<com.task.taskmanager.models.entity.Task>) tasks.findAll();
		List<Task> response = new ArrayList<Task>();
		for (Task tt : ttask) {
			if (tt.getLabel().getCategory().compareTo(Categories.valueOf(priorLabel)) == 0) {

				response.add(tt);
			}

		}
		return new ResponseEntity<>(response, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/byLabel/{labelId}", method = RequestMethod.GET, produces = { "application/json" })

	public ResponseEntity<?> searchByLabel(@PathVariable("labelId") String priorLabel) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "foo");

		return new ResponseEntity<>("Custom header set", headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/byTime/{timeId}", method = RequestMethod.GET, produces = { "application/json" })

	public ResponseEntity<?> searchByTime(@PathVariable("timeId") String priorLabel) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "foo");

		return new ResponseEntity<>("Custom header set", headers, HttpStatus.OK);
	}

}
