package com.cloudsea.forms.formservice.question.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudsea.forms.formservice.exception.FormNotFoundException;
import com.cloudsea.forms.formservice.question.dto.UserForm;
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

	@GetMapping(value = "/userid/{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resources<Resource<UserForm>>> findByUserId(@PathVariable("userId") String userId) {

		logger.debug("Searching all form created by userid -> {}", userId);
		List<Form> forms = formService.findByUserId(userId);
		List<Resource<UserForm>> listResource = new ArrayList<>();

		for (Form form : forms) {
			UserForm userForms = new UserForm(form.getTitle(), form.getStatus().name());
			Resource<UserForm> userFormResource = new Resource<UserForm>(userForms);
			userFormResource.add(linkTo(methodOn(getClass()).findById(form.getId())).withSelfRel());
			addPublicLinkIfStatusIsOpen(form, userFormResource);
			listResource.add(userFormResource);
		}

		Link selfLink = linkTo(methodOn(getClass()).findById(userId)).withSelfRel();
		Resources<Resource<UserForm>> resourceList = new Resources<>(listResource, selfLink);
		return new ResponseEntity<Resources<Resource<UserForm>>>(resourceList, HttpStatus.OK);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<Resource<Form>> updateStatus(@PathVariable("id") String id,
			@RequestBody Form form) {
		logger.debug("Updaing form with is -> {}", id);

		Form formDb = formService.findById(id);
		if (formDb == null)
			throw new FormNotFoundException(String.format("Form with id %s was not found", id));

		formService.create(form);
		return getFormResource(form);
	}

	@RequestMapping(method = GET, value = "/id/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
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

	private void addPublicLinkIfStatusIsOpen(Form form, Resource<UserForm> userFormResource) {
		if (form.getStatus() == FormStatus.OPEN)
			userFormResource.add(new Link("http://api.cloudsea.in/myforms/display/" + form.getId(), "public"));
	}

}
