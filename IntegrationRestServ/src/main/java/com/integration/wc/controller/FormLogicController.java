
package com.integration.wc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.integration.wc.models.LogicTableBRE;
import com.integration.wc.service.FormLogicService;

@RestController
@RequestMapping("/form")
public class FormLogicController {

	@Autowired
	FormLogicService formLogicService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces =  {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<LogicTableBRE>> getAllPosts(Pageable pageable) {

		Iterable<LogicTableBRE> formLogic = formLogicService.getAll();
		if (((List<LogicTableBRE>) formLogic).isEmpty()) {
			return new ResponseEntity<List<LogicTableBRE>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<LogicTableBRE>>((List<LogicTableBRE>) formLogic, HttpStatus.OK);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces =  {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

	public ResponseEntity<LogicTableBRE> get(@PathVariable("id") String id) {
		LogicTableBRE inc = formLogicService.getById(id);
		if (inc == null) {
			return new ResponseEntity<LogicTableBRE>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<LogicTableBRE>(inc, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody LogicTableBRE LogicTableBRE) {
		formLogicService.add(LogicTableBRE);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody LogicTableBRE LogicTableBRE) {
		LogicTableBRE inc = formLogicService.find(LogicTableBRE.getTemplateId());
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			formLogicService.update(LogicTableBRE);
			return new ResponseEntity<Void>(HttpStatus.OK);

		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
		LogicTableBRE inc = formLogicService.find(id);
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			formLogicService.remove(inc);
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
}