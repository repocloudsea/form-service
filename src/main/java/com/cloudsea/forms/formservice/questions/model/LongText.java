package com.cloudsea.forms.formservice.questions.model;

import org.apache.commons.lang3.StringUtils;

public class LongText extends Element {

	private int maxLength;

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public LongText() {
	}

	public LongText(String question, String helpText, boolean required, String attachmentUrl) {
		super(question, helpText, required, attachmentUrl);
	}

	@Override
	public void validate(String value) throws IllegalArgumentException {
		
		if (isRequired() && StringUtils.isBlank(value))
			throw new IllegalArgumentException("Cannot be empty");
		
		if (value.length() > maxLength)
			throw new IllegalArgumentException(String.format("Maximum %d charecters allowed ", this.maxLength));
	}

}
