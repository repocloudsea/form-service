package com.cloudsea.forms.formservice.questions.model;

public class ShortText extends Element {

	private int maxLength;

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public ShortText() {
	}

	public ShortText(String question, String helpText, boolean required, String attachmentUrl) {
		super(question, helpText, required, attachmentUrl);
	}

	@Override
	public void validate(String value) throws IllegalArgumentException {
		if (value.length() > maxLength)
			throw new IllegalArgumentException(String.format("Maximum %d charecters allowed ", this.maxLength));
	}
}
