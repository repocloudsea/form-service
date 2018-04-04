package com.cloudsea.forms.formservice.question.model;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "email")
public class Email extends Element {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Email() {
    }

    public Email(String refId, String question, String helpText, boolean required, String attachmentUrl, String email) {
        super(refId, question, helpText, required, attachmentUrl);
        this.email = email;
    }

    @Override
    public ValidationResult validate(String value) throws IllegalArgumentException {

        if (!value.contains("@"))
            return new ValidationResult(getRefId(), "Email invalid");

        return null;

    }

    @Override
    public void validateElements() throws IllegalArgumentException {

    }

}
