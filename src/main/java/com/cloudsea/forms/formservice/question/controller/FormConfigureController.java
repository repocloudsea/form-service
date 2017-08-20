package com.cloudsea.forms.formservice.question.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudsea.forms.formservice.question.service.FormsService;
import com.cloudsea.forms.formservice.questions.model.Form;
import com.cloudsea.forms.formservice.questions.model.FormStatus;

@RestController
@RequestMapping(value = "/forms")
public class FormConfigureController {
	private static final Logger logger = LoggerFactory.getLogger(FormConfigureController.class);

	private FormsService formService;

	@Autowired
	public FormConfigureController(FormsService formService) {
		this.formService = formService;
	}

	@RequestMapping(method = POST, value = "/create")
	public ResponseEntity<Resource<Form>> createForm(@RequestBody Form form) {
		logger.debug("Creating form with -> {}", form.toString());
		formService.create(form);
		return getFormResource(form);
	}

	// TODO: Implement partial update
	@RequestMapping(method = POST, value = "/update")
	public ResponseEntity<Resource<Form>> update(@RequestBody Form form) {
		logger.debug("Updaing form with -> {}", form.toString());
		formService.create(form);
		return getFormResource(form);
	}

	//TODO remove this method or do null checks 
	@RequestMapping(method = PATCH, value = "/update/{id}/status/{status}")
	public ResponseEntity<Resource<Form>> update(@PathVariable("status") String status,
			@PathVariable("id") String formId) {
		logger.debug("Updaing form with status -> {}", status);

		Form form = formService.findById(formId);
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

	@RequestMapping(method = GET, value = "/user/{id}")
	public List<Map<String, String>> findByUserId(@PathVariable("id") String userId) {

		logger.debug("Searching for form with userid -> {}", userId);
		List<Form> forms = formService.findByUserId(userId);

		List<Map<String, String>> userForms = new ArrayList<>();

		for (Form form : forms) {
			Map<String, String> map = new HashMap<>();
			map.put("title", form.getTitle());
			map.put("link", linkTo(methodOn(getClass()).findById(form.getId())).withSelfRel().getHref());
			map.put("update_status", linkTo(methodOn(getClass()).update("{status}", form.getId() )).withRel("update_status").getHref());
			map.put("status", form.getStatus() + "");
			if (form.getStatus() == FormStatus.OPEN) {
				map.put("publicLink", "http://www.form.cloudsea.in/forms/display/" + form.getId());
			}
			userForms.add(map);
		}
		return userForms;
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
