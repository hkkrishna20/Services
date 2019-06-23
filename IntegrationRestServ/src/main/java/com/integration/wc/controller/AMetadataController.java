
package com.integration.wc.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import com.integration.wc.models.IntegrationMetadata;
import com.integration.wc.service.MetadataService;

@RestController
@RequestMapping("/metadata")
public class AMetadataController {

	@Autowired
	MetadataService metadataService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<IntegrationMetadata>> getAllPosts(Pageable pageable) {

		Iterable<IntegrationMetadata> metadata = metadataService.getAll();
		if (((List<IntegrationMetadata>) metadata).isEmpty()) {
			return new ResponseEntity<List<IntegrationMetadata>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<IntegrationMetadata>>((List<IntegrationMetadata>) metadata, HttpStatus.OK);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces =  {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

	public ResponseEntity<IntegrationMetadata> get(@PathVariable("id") String id) {
		IntegrationMetadata inc = metadataService.get(id);
		if (inc == null) {
			return new ResponseEntity<IntegrationMetadata>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<IntegrationMetadata>(inc, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody IntegrationMetadata integrationMetadata) {
		metadataService.add(integrationMetadata);
		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody IntegrationMetadata integrationMetadata) {
		IntegrationMetadata inc = metadataService.find(integrationMetadata.getReqid());
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			metadataService.update(integrationMetadata);
			return new ResponseEntity<Void>(HttpStatus.OK);

		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
		IntegrationMetadata inc = metadataService.find(id);
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			metadataService.remove(inc);
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
}