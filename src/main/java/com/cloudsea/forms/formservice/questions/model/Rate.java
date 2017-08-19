package com.cloudsea.forms.formservice.questions.model;

import org.apache.commons.lang3.StringUtils;

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

		if (isRequired() && StringUtils.isBlank(value))
			throw new IllegalArgumentException("Cannot be empty");

		if (!StringUtils.isNumeric(value))
			throw new IllegalArgumentException(String.format("Only numerice values allowed"));

		if (Integer.parseInt(value) > rates)
			throw new IllegalArgumentException(String.format("Maximum %d charecters allowed ", this.rates));
	}

}
