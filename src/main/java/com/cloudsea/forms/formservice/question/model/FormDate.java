package com.cloudsea.forms.formservice.question.model;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName(value = "date")
public class FormDate extends Element {

    private String format;

    public FormDate() {
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public ValidationResult validate(String value) throws IllegalArgumentException {
        return null;
    }

    //TODO Add date format validations by making format as ENUM
    @Override
    public void validateElements() throws IllegalArgumentException {


    }
}
