
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

import com.task.taskmanager.models.entity.Label;
import com.task.taskmanager.service.GenericLabelManagerServiceImpl;

@RestController
@RequestMapping("/label")
public class LabelController {

	@Autowired
	GenericLabelManagerServiceImpl labelService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Label>> getAllPosts(Pageable pageable) {

		Iterable<Label> Label = labelService.getAll();
		if (((List<Label>) Label).isEmpty()) {
			return new ResponseEntity<List<Label>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Label>>((List<Label>) Label,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces =  {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Label> get(@PathVariable("id") String id) {
		Label inc = labelService.get(id).get();
		if (inc == null) {
			return new ResponseEntity<Label>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Label>(inc, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)

	public ResponseEntity<Void> create(@Valid @RequestBody Label Label) {
		labelService.add(Label);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Label Label) {
		Label inc = labelService.find(Label.getId()).get();
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			labelService.update(Label);
			return new ResponseEntity<Void>(HttpStatus.OK);

		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
		Label inc = labelService.find(id).get();
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			labelService.remove(inc);
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
}