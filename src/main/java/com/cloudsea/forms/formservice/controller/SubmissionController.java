package com.cloudsea.forms.formservice.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudsea.forms.formservice.answers.model.Answer;
import com.cloudsea.forms.formservice.answers.model.Result;
import com.cloudsea.forms.formservice.questions.model.Element;
import com.cloudsea.forms.formservice.questions.model.Form;
import com.cloudsea.forms.formservice.service.FormsService;

@RestController
@RequestMapping(value = "/")
public class SubmissionController {

	private FormsService formsService;

	@Autowired
	public SubmissionController(FormsService formsService) {
		this.formsService = formsService;
	}

	private Map<String, Element> getElementMap(List<Element> elements) {
		return elements.stream().collect(Collectors.toMap(Element::getRefId, e -> e));
	}

	@RequestMapping(value = "/submit")
	public void saveResult(@RequestBody Result result) {

		Form form = formsService.findById(result.getFormId());
		List<Answer> answers = result.getAnswers();

		Map<String, Element> map = getElementMap(form.getElements());

		for (Answer answer : answers) {
			Element elm = map.get(answer.getFieldRefID());
			elm.validate(answer.getValue());
		}

		System.out.println(result);
	}

}
