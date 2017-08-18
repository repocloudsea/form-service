package com.cloudsea.forms.formservice.questions.model;

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
		// TODO Auto-generated method stub
		
	}

}
