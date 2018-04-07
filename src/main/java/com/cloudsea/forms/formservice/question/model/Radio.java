package com.cloudsea.forms.formservice.question.model;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName(value = "radio")
public class Radio extends Element {

    @Override
    public ValidationResult validate(String value) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void validateElements() throws IllegalArgumentException {

    }
}
