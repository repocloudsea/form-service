package com.cloudsea.forms.formservice.questions.model;

import org.apache.commons.lang3.StringUtils;

public class Number extends Element {

	private Integer minValue;

	private Integer maxValue;

	public Integer getMinValue() {
		return minValue;
	}

	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	public Number() {
		super();
	}

	public Number(String question, String helpText, boolean required, String attachmentUrl) {
		super(question, helpText, required, attachmentUrl);
	}

	@Override
	public void validate(String value) throws IllegalArgumentException {
		
		if (isRequired() && StringUtils.isBlank(value))
			throw new IllegalArgumentException("Cannot be empty");

		if (!StringUtils.isNumeric(value))
			throw new IllegalArgumentException("Only numeric value allowed");

		int num = Integer.parseInt(value);

		if (num < minValue || num > maxValue)
			throw new IllegalArgumentException(String.format("Value should be between %d - %d ", minValue, maxValue));

	}

}
