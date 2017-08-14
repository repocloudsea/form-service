package com.cloudsea.forms.formservice.model;

import java.util.List;

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
	
	


}
