package com.cloudsea.forms.formservice.answers.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Result {

	private String id;
	private String formId;
	private List<Answers> answers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public List<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}

}
