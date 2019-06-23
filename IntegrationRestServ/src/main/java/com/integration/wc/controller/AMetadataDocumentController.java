
package com.integration.wc.controller;

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

import com.integration.wc.models.IntegrationMetadataDocument;
import com.integration.wc.service.MetadataDocumentService;

@RestController
@RequestMapping("/metadataDocument")
public class AMetadataDocumentController {

	@Autowired
	MetadataDocumentService metadatadocumentService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<IntegrationMetadataDocument>> getAllPosts(Pageable pageable) {

		Iterable<IntegrationMetadataDocument> metadataDocument = metadatadocumentService.getAll();
		if (((List<IntegrationMetadataDocument>) metadataDocument).isEmpty()) {
			return new ResponseEntity<List<IntegrationMetadataDocument>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<IntegrationMetadataDocument>>(
				(List<IntegrationMetadataDocument>) metadataDocument, HttpStatus.OK);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces =  {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

	public ResponseEntity<IntegrationMetadataDocument> get(@PathVariable("id") String id) {
		IntegrationMetadataDocument inc = metadatadocumentService.get(id);
		if (inc == null) {
			return new ResponseEntity<IntegrationMetadataDocument>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<IntegrationMetadataDocument>(inc, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody IntegrationMetadataDocument IntegrationMetadataDocument) {
		metadatadocumentService.add(IntegrationMetadataDocument);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody IntegrationMetadataDocument IntegrationMetadataDocument) {
		IntegrationMetadataDocument inc = metadatadocumentService.find(IntegrationMetadataDocument.getDocID());
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			metadatadocumentService.update(IntegrationMetadataDocument);
			return new ResponseEntity<Void>(HttpStatus.OK);

		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
		IntegrationMetadataDocument inc = metadatadocumentService.find(id);
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			metadatadocumentService.remove(inc);
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
}