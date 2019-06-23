
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

import com.integration.wc.models.IntegrationAuditPoint;
import com.integration.wc.service.AuditPointService;

@RestController
@RequestMapping("/auditPoint")
public class AuditPointController {

	@Autowired
	AuditPointService auditPointService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<IntegrationAuditPoint>> getAllPosts(Pageable pageable) {

		Iterable<IntegrationAuditPoint> integrationAuditPoint = auditPointService.getAll();
		if (((List<IntegrationAuditPoint>) integrationAuditPoint).isEmpty()) {
			return new ResponseEntity<List<IntegrationAuditPoint>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<IntegrationAuditPoint>>((List<IntegrationAuditPoint>) integrationAuditPoint,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces =  {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<IntegrationAuditPoint> get(@PathVariable("id") String id) {
		IntegrationAuditPoint inc = auditPointService.get(id);
		if (inc == null) {
			return new ResponseEntity<IntegrationAuditPoint>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<IntegrationAuditPoint>(inc, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)

	public ResponseEntity<Void> create(@Valid @RequestBody IntegrationAuditPoint IntegrationAuditPoint) {
		auditPointService.add(IntegrationAuditPoint);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody IntegrationAuditPoint IntegrationAuditPoint) {
		IntegrationAuditPoint inc = auditPointService.find(IntegrationAuditPoint.getId());
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			auditPointService.update(IntegrationAuditPoint);
			return new ResponseEntity<Void>(HttpStatus.OK);

		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
		IntegrationAuditPoint inc = auditPointService.find(id);
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			auditPointService.remove(inc);
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
}