package com.cloudsea.forms.formservice.answers.controller;

import static com.cloudsea.forms.formservice.utils.Utils.getElementMap;
import static com.cloudsea.forms.formservice.utils.Utils.getElementType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudsea.forms.formservice.answers.model.AnswerDTO;
import com.cloudsea.forms.formservice.answers.model.Answers;
import com.cloudsea.forms.formservice.answers.model.Result;
import com.cloudsea.forms.formservice.answers.model.ResultDTO;
import com.cloudsea.forms.formservice.answers.service.ResultsService;
import com.cloudsea.forms.formservice.question.service.FormsService;
import com.cloudsea.forms.formservice.questions.model.Element;
import com.cloudsea.forms.formservice.questions.model.Form;
import com.cloudsea.forms.formservice.validate.ValidationResult;

@RestController
@RequestMapping("/forms")
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
	public ResponseEntity<List<ValidationResult>> validateForms(@RequestBody ResultDTO resultDTO) {
		Form form = formsService.findById(resultDTO.getFormId());

		List<ValidationResult> validationResults = new ArrayList<>();
		List<AnswerDTO> answersDTO = resultDTO.getAnswers();

		Result finalResult = new Result();
		finalResult.setFormId(resultDTO.getFormId());
		List<Answers> answers = new ArrayList<>();

		Map<String, Element> map = getElementMap(form.getElements());
		
		for (AnswerDTO answer : answersDTO) {
			Element elm = map.get(answer.getFieldRefID());
			ValidationResult validationResult = elm.validate(answer.getValue());
			if (validationResult != null)
				validationResults.add(validationResult);

			answers.add(new Answers(getElementType(elm), answer.getValue(),
					elm.getQuestion()));

		}

		if (!validationResults.isEmpty())
			return new ResponseEntity<>(validationResults, HttpStatus.CONFLICT);

		finalResult.setAnswers(answers);

		resultService.saveResults(finalResult);

		return new ResponseEntity<>(HttpStatus.OK);

	}

}
