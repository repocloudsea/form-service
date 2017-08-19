package com.cloudsea.forms.formservice.questions.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DropDown extends Element {

	private List<String> choices;

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

	public DropDown() {
	}

	public DropDown(String question, String helpText, boolean required, String attachmentUrl) {
		super(question, helpText, required, attachmentUrl);
	}

	@Override
	public void validate(String value) throws IllegalArgumentException {

		if (isRequired() && StringUtils.isBlank(value))
			throw new IllegalArgumentException("Cannot be empty");

		if (!choices.contains(value))
			throw new IllegalArgumentException("");
	}

}
