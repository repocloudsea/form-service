package com.cloudsea.forms.formservice.question.model;

import org.apache.commons.lang3.StringUtils;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "number")
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
	public ValidationResult validate(String value) throws IllegalArgumentException {

		if (isRequired() && StringUtils.isBlank(value))
			return new ValidationResult(getRefId(), "Cannot be empty");

		if (!StringUtils.isNumeric(value))
			return new ValidationResult(getRefId(), "Only numeric value allowed");

		int num = Integer.parseInt(value);

		if (num < minValue || num > maxValue)
			return new ValidationResult(getRefId(),
					String.format("Value should be between %d - %d ", minValue, maxValue));

		return null;
	}

	@Override
	public void validateElements() throws IllegalArgumentException {

		if (this.maxValue > Integer.MAX_VALUE || this.minValue < Integer.MIN_VALUE)
			throw new IllegalArgumentException(
					String.format("Range limited from %d to %d only ", Integer.MAX_VALUE, Integer.MIN_VALUE));

	}

}
