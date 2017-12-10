package com.cloudsea.forms.formservice.question.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "dropDown")
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
	public ValidationResult validate(String value) throws IllegalArgumentException {

		if (isRequired() && StringUtils.isBlank(value))
			return new ValidationResult(getRefId(), "Cannot be empty");

		if (!choices.contains(value))
			return new ValidationResult(getRefId(), "Mismach found");

		return null;
	}

	@Override
	public void validateElements() throws IllegalArgumentException {
		if (this.choices == null || this.choices.size()<2)
			throw new IllegalArgumentException("There must be atleast two choices");
	}


}
