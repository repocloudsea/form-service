package com.cloudsea.forms.formservice.question.model;

import org.apache.commons.lang3.StringUtils;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "phoneNumber")
public class PhoneNumber extends Element {

	private String phoneNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public PhoneNumber() {
		super();
	}

	public PhoneNumber(String refId, String question, String helpText, boolean required, String attachmentUrl, String phoneNumber) {
		super(refId, question, helpText, required, attachmentUrl);
		this.phoneNumber = phoneNumber;
	}

	@Override
	public ValidationResult validate(String value) throws IllegalArgumentException {
		if (isRequired() && StringUtils.isBlank(value))
			return new ValidationResult(getRefId(), "Cannot be empty");

		String number = value.substring(1);

		if (!StringUtils.isNumeric(number))
			return new ValidationResult(getRefId(), "Only numeric value allowed");

		return null;
	}

	@Override
	public void validateElements() throws IllegalArgumentException {		
	}

}
