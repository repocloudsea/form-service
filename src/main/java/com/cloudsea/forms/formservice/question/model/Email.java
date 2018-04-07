package com.cloudsea.forms.formservice.question.model;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "email")
public class Email extends Element {
    public Email() {
    }

    public Email(String refId, String question, String helpText, boolean required, String attachmentUrl) {
        super(refId, question, helpText, required, attachmentUrl);
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
