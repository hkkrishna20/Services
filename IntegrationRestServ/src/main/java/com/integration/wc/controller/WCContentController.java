
package com.integration.wc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import com.integration.wc.models.ContentTsType;
import com.integration.wc.models.HeaderType;
import com.integration.wc.models.WcType;
import com.integration.wc.service.WCContentService;

@RestController
@RequestMapping("/wccontent")
public class WCContentController {

	@Autowired
	WCContentService wccontentService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<WcType> getAllPosts(Pageable pageable) {

		List<ContentTsType> contents = wccontentService.getAll();
		String listString = contents.stream().map(Object::toString).collect(Collectors.joining(", "));
		System.out.println(listString);
		/*
		 * List<ContentTsType> list = new ArrayList<>();
		 * content.forEachRemaining(list::add);
		 */
		// ArrayList<ContentTsType> contents = toList(content);
		HeaderType hc = new HeaderType();
		hc.setContentTs(contents);
		WcType wc = new WcType();
		wc.setHeader(hc);

		if (wc.getHeader().getContentTs().size() < 0 || wc.getHeader().getContentTs().isEmpty()) {
			return new ResponseEntity<WcType>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<WcType>(wc, HttpStatus.OK);
	}

	public static <E> ArrayList<E> toList(Iterable<E> iterable) {
		if (iterable instanceof List) {
			return (ArrayList<E>) iterable;
		}
		ArrayList<E> list = new ArrayList<E>();
		if (iterable != null) {
			for (E e : iterable) {
				list.add(e);
			}
		}
		return list;
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces =  {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

	public ResponseEntity<ContentTsType> get(@PathVariable("id") String id) {
		ContentTsType inc = wccontentService.getById(id);
		if (inc == null) {
			return new ResponseEntity<ContentTsType>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ContentTsType>(inc, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody ContentTsType Content) {
		wccontentService.add(Content);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ContentTsType Content) {
		ContentTsType inc = wccontentService.find(Content.getId());
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			wccontentService.update(Content);
			return new ResponseEntity<Void>(HttpStatus.OK);

		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletePost(@PathVariable("id") String id) {
		ContentTsType inc = wccontentService.find(Integer.parseInt(id));
		if (inc == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			wccontentService.remove(inc);
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}
}