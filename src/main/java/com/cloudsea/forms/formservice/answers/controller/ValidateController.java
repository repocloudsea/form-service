package com.cloudsea.forms.formservice.answers.controller;

import static com.cloudsea.forms.formservice.utils.Utils.getElementMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudsea.forms.formservice.question.model.Element;
import com.cloudsea.forms.formservice.question.model.Form;
import com.cloudsea.forms.formservice.question.service.FormsService;
import com.cloudsea.forms.formservice.validate.ValidationResult;

@RestController
@RequestMapping(value = "/validate")
public class ValidateController {

	private FormsService formsService;

	@Autowired
	public ValidateController(FormsService formsService) {
		this.formsService = formsService;
	}

	@RequestMapping(value = "/formId/{formId}/element/{elementId}", method = RequestMethod.POST)
	public ResponseEntity<ValidationResult> validateElements(@PathVariable("formId") String formId,
			@PathVariable("elementId") String elementId, @RequestBody String data) {

		Form form = formsService.findById(formId);
		Map<String, Element> elementMap = getElementMap(form.getElements());
		ValidationResult validationResult = elementMap.get(elementId).validate(data);
		if (validationResult != null)
			return new ResponseEntity<>(validationResult, HttpStatus.CONFLICT);

		return new ResponseEntity<>(HttpStatus.OK);

	}
}
