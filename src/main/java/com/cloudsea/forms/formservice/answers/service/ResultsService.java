package com.cloudsea.forms.formservice.answers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudsea.forms.formservice.answers.model.Result;
import com.cloudsea.forms.formservice.answers.repository.ResultsRepository;

@Service
public class ResultsService {

	private ResultsRepository resultsRepository;

	@Autowired
	public ResultsService(ResultsRepository resultsRepository) {
		this.resultsRepository = resultsRepository;
	}

	public List<Result> findByFormId(String formId) {
		return resultsRepository.findByFormId(formId);
	}
		
	public void saveResults(Result result){
		resultsRepository.save(result);
	}

}
