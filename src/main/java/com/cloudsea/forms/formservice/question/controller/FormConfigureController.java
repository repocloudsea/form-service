package com.cloudsea.forms.formservice.question.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
			Resource<Map<String, String>> userFormResource = new Resource<Map<String, String>>(userFormMap);
			userFormResource.add(linkTo(methodOn(getClass()).findById(form.getId())).withSelfRel());
			userFormResource
					.add(linkTo(methodOn(getClass()).update("{status}", form.getId())).withRel("update_status"));

			if (form.getStatus() == FormStatus.OPEN)
				userFormResource.add(new Link("http://api.cloudsea.in/myforms/display/" + form.getId(), "public"));

			listResource.add(userFormResource);
		}

		Link selfLink = linkTo(methodOn(getClass()).findById(userId)).withSelfRel();

		Resources<Resource<Map<String, String>>> resourceList = new Resources<>(listResource, selfLink);
		return new ResponseEntity<Resources<Resource<Map<String, String>>>>(resourceList, HttpStatus.OK);
	}

	@RequestMapping(method = PATCH, value = "/update/{formId}/status/{status}")
	public ResponseEntity<Resource<Form>> update(@PathVariable("status") String status,
			@PathVariable("formId") String formId) {
		logger.debug("Updaing form with status -> {}", status);

		Form form = formService.findById(formId);
		if (form.getStatus() == null)
			throw new IllegalArgumentException("Status cannot be null");

		form.setStatus(FormStatus.valueOf(status));
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
