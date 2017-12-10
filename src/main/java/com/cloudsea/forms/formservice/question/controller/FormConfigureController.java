package com.cloudsea.forms.formservice.question.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

import com.cloudsea.forms.formservice.exception.FormNotFoundException;
import com.cloudsea.forms.formservice.question.model.Form;
import com.cloudsea.forms.formservice.question.model.FormStatus;
import com.cloudsea.forms.formservice.question.service.FormsService;

@RestController
@RequestMapping(value = "/forms")
public class FormConfigureController {
	private static final Logger logger = LoggerFactory.getLogger(FormConfigureController.class);

	private FormsService formService;

	@Autowired
	public FormConfigureController(FormsService formService) {
		this.formService = formService;
	}

	@PostMapping
	public ResponseEntity<Resource<Form>> createForm(@Valid @RequestBody Form form) {
		logger.debug("Creating form with -> {}", form.toString());
		formService.create(form);
		return getFormResource(form);
	}

	@GetMapping(value = "/userid/{userId}")
	public ResponseEntity<Resources<Resource<Map<String, String>>>> findByUserId(
			@PathVariable("userId") String userId) {

		logger.debug("Searching all form created by userid -> {}", userId);
		List<Form> forms = formService.findByUserId(userId);
		List<Resource<Map<String, String>>> listResource = new ArrayList<>();
		for (Form form : forms) {
			Map<String, String> userFormMap = new HashMap<>();
			userFormMap.put("title", form.getTitle());
			userFormMap.put("status", form.getStatus() + "");

			
			Map<String, String> updateMap = new HashMap<>();
			updateMap.put("status", "OPEN");
			
			
			Resource<Map<String, String>> userFormResource = new Resource<Map<String, String>>(userFormMap);
			userFormResource.add(linkTo(methodOn(getClass()).findById(form.getId())).withSelfRel());
			userFormResource
					.add(linkTo(methodOn(getClass()).updateStatus(form.getId(), updateMap)).withRel("update_status"));
			
			
			userFormResource.add(new Link("","status"));

			

			if (form.getStatus() == FormStatus.OPEN)
				userFormResource.add(new Link("http://api.cloudsea.in/myforms/display/" + form.getId(), "public"));

			listResource.add(userFormResource);
		}

		Link selfLink = linkTo(methodOn(getClass()).findById(userId)).withSelfRel();

		Resources<Resource<Map<String, String>>> resourceList = new Resources<>(listResource, selfLink);
		return new ResponseEntity<Resources<Resource<Map<String, String>>>>(resourceList, HttpStatus.OK);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<Resource<Form>> updateStatus(@PathVariable("id") String id,
			@RequestBody Map<String, String> updateItemMap) {
		logger.debug("Updaing form with is -> {}", id);

		Form form = formService.findById(id);
		if (form == null)
			throw new FormNotFoundException(String.format("Form with id %s was not found", id));
		
		form.setStatus(FormStatus.valueOf(updateItemMap.get("status")));
		formService.create(form);
		return getFormResource(form);
	}

	@RequestMapping(method = GET, value = "/id/{id}")
	public ResponseEntity<Resource<Form>> findById(@PathVariable("id") String id) {

		logger.debug("Searching for form with id -> {}", id);
		Form form = formService.findById(id);
		Resource<Form> formresource = new Resource<Form>(form);
		formresource.add(linkTo(methodOn(getClass()).findById(form.getId())).withSelfRel());

		ResponseEntity<Resource<Form>> responseEntity = new ResponseEntity<Resource<Form>>(formresource, HttpStatus.OK);
		return responseEntity;
	}

	private ResponseEntity<Resource<Form>> getFormResource(Form form) {
		Resource<Form> formresource = new Resource<Form>(form);
		formresource.add(linkTo(methodOn(getClass()).findById(form.getId())).withSelfRel());
		formresource.add(linkTo(methodOn(getClass()).findByUserId(form.getUserId())).withRel("myforms"));
		ResponseEntity<Resource<Form>> responseEntity = new ResponseEntity<Resource<Form>>(formresource,
				HttpStatus.CREATED);
		return responseEntity;
	}

}
