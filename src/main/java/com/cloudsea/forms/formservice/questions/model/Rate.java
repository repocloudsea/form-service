package com.cloudsea.forms.formservice.questions.model;

public class Rate extends Element {

	private Integer rates;

	public Integer getRates() {
		return rates;
	}

	public void setRates(Integer rates) {
		this.rates = rates;
	}

	public Rate() {
	}

	public Rate(String question, String helpText, boolean required, String attachmentUrl) {
		super(question, helpText, required, attachmentUrl);
	}

	@Override
	public void validate(String value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
