package com.cloudsea.forms.formservice.answers.model;

import java.util.List;

public class ResultDTO {

	private String formId;
	private List<AnswerDTO> answers;

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public List<AnswerDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerDTO> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Result [formId=" + formId + ", answers=" + answers + "]";
	}

}
