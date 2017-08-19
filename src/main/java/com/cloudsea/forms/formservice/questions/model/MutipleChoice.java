package com.cloudsea.forms.formservice.questions.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class MutipleChoice extends Element {

	private List<String> choices;
	private boolean mutipleAllowed;

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

	public boolean isMutipleAllowed() {
		return mutipleAllowed;
	}

	public void setMutipleAllowed(boolean mutipleAllowed) {
		this.mutipleAllowed = mutipleAllowed;
	}

	public MutipleChoice() {
	}

	public MutipleChoice(String question, String helpText, boolean required, String attachmentUrl) {
		super(question, helpText, required, attachmentUrl);
	}

	@Override
	public void validate(String value) throws IllegalArgumentException {

		if (isRequired() && StringUtils.isBlank(value))
			throw new IllegalArgumentException("Cannot be empty");

		if (mutipleAllowed) {
			List<String> answers = Arrays.asList(value.split(","));

			if (answers.size() > choices.size())
				throw new IllegalArgumentException("");

			if (Collections.disjoint(answers, choices))
				throw new IllegalArgumentException("");

		} else {
			if (!choices.contains(value))
				throw new IllegalArgumentException("");

		}

	}

}
