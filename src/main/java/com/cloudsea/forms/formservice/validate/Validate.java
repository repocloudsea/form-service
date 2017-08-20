package com.cloudsea.forms.formservice.validate;

public interface Validate {

	ValidationResult validate(String value) throws IllegalArgumentException;

}
