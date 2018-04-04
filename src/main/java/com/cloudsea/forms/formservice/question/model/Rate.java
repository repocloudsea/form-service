package com.cloudsea.forms.formservice.question.model;

import org.apache.commons.lang3.StringUtils;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "rate")
public class Rate extends Element {

	private Integer rates = 5;

	public Integer getRates() {
		return rates;
	}

	public void setRates(Integer rates) {
		this.rates = rates;
	}

	public Rate() {
	}

	public Rate(String refId, String question, String helpText, boolean required, String attachmentUrl, Integer rates) {
		super(refId, question, helpText, required, attachmentUrl);
		this.rates = rates;
	}

	@Override
	public ValidationResult validate(String value) throws IllegalArgumentException {
		
		if (isRequired() && StringUtils.isBlank(value))
			return new ValidationResult(getRefId(), "Cannot be empty");

		if (!StringUtils.isNumeric(value))
			return new ValidationResult(getRefId(), String.format("Only numerice values allowed"));

		if (Integer.parseInt(value) > rates)
			return new ValidationResult(getRefId(), String.format("Maximum %d charecters allowed ", this.rates));

		return null;
	}

	@Override
	public void validateElements() throws IllegalArgumentException {
		if(this.rates >10)
			throw new IllegalArgumentException("Only 10 rating are allowed");
		
	}

}
