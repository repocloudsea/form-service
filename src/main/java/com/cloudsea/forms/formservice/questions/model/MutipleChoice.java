package com.cloudsea.forms.formservice.questions.model;

import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

}
