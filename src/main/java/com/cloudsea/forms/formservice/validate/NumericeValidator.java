package com.cloudsea.forms.formservice.validate;

import org.apache.commons.lang3.StringUtils;

public class NumericeValidator implements Validate {

	@Override
	public void validate(String value) throws IllegalArgumentException {
		if (!StringUtils.isNumeric(value))
			throw new IllegalArgumentException("Only numeric value allowed");
	}

}
