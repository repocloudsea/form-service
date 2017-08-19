package com.cloudsea.forms.formservice.answers.controller;

import static com.cloudsea.forms.formservice.utils.Utils.getElementMap;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudsea.forms.formservice.answers.model.Answer;
import com.cloudsea.forms.formservice.answers.model.Result;
import com.cloudsea.forms.formservice.answers.service.ResultsService;
import com.cloudsea.forms.formservice.question.service.FormsService;
import com.cloudsea.forms.formservice.questions.model.Element;
import com.cloudsea.forms.formservice.questions.model.Form;;

@RestController
@RequestMapping(value = "/results")
public class SubmissionController {

	private static final Logger logger = LoggerFactory.getLogger(SubmissionController.class);

	private FormsService formsService;
	private ResultsService resultService;

	@Autowired
	public SubmissionController(FormsService formsService, ResultsService resultsService) {
		this.formsService = formsService;
		this.resultService = resultsService;
	}

	@RequestMapping(value = "/find/formid/{formId}", method = RequestMethod.POST)
	public List<Result> findByFormId(@PathVariable("formId") String formId) {
		return resultService.findByFormId(formId);
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public void saveResult(@RequestBody Result result) {
		logger.debug(result.toString());
		Form form = formsService.findById(result.getFormId());
		List<Answer> answers = result.getAnswers();

		Map<String, Element> map = getElementMap(form.getElements());

		for (Answer answer : answers) {
			Element elm = map.get(answer.getFieldRefID());
			elm.validate(answer.getValue());
		}

		resultService.saveResults(result);
	}

	@RequestMapping(method = POST, value = "/update")
	public void update(@RequestBody Result result) {
		saveResult(result);
	}

}
