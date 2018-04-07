package com.cloudsea.forms.formservice.question.model;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;


@JsonTypeName(value = "radio")
public class Radio extends Element {

    private List<String> choices;

    public Radio(String refId, String question, String helpText, boolean required, String attachmentUrl, List<String> choices) {
        super(refId, question, helpText, required, attachmentUrl);
        this.choices = choices;
    }

    public Radio() {
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    @Override
    public ValidationResult validate(String value) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void validateElements() throws IllegalArgumentException {

    }
}
