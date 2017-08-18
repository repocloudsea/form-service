package com.cloudsea.forms.formservice.questions.model;

public class Email extends Element {

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Email() {
	}

	public Email(String question, String helpText, boolean required, String attachmentUrl) {
		super(question, helpText, required, attachmentUrl);
	}

	@Override
	public void validate(String value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
