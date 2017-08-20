package com.cloudsea.forms.formservice.validate;

public class ValidationResult {

	private String fieldId;
	private String errorMessage;

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ValidationResult(String fieldId, String errorMessage) {
		this.fieldId = fieldId;
		this.errorMessage = errorMessage;
	}

}
