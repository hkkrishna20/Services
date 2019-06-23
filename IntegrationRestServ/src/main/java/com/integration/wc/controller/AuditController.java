
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.integration.wc.models.IntegrationAudit;
import com.integration.wc.service.AuditService;

@RestController
@RequestMapping("/audit")
public class AuditController {

	@Autowired
	AuditService auditService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<IntegrationAudit>> getAllPosts(Pageable pageable) {

		Iterable<IntegrationAudit> integrationAudit = auditService.getAll();
		if (((List<IntegrationAudit>) integrationAudit).isEmpty()) {
			return new ResponseEntity<List<IntegrationAudit>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<IntegrationAudit>>((List<IntegrationAudit>) integrationAudit, HttpStatus.OK);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces =  {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<IntegrationAudit> get(@PathVariable("id") String id) {
		IntegrationAudit inc = auditService.get(id);
		if (inc == null) {
			return new ResponseEntity<IntegrationAudit>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<IntegrationAudit>(inc, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody IntegrationAudit IntegrationAudit) {
		auditService.add(IntegrationAudit);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody IntegrationAudit IntegrationAudit) {
		IntegrationAudit inc = auditService.find(IntegrationAudit.getReqid());
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			auditService.update(IntegrationAudit);
			return new ResponseEntity<Void>(HttpStatus.OK);

		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
		IntegrationAudit inc = auditService.find(id);
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			auditService.remove(inc);
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
}