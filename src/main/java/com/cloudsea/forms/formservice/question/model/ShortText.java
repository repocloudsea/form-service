package com.cloudsea.forms.formservice.question.model;

import org.apache.commons.lang3.StringUtils;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "shortText")
public class ShortText extends Element {

    private int maxLength = 100;

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public ShortText() {
    }

    public ShortText(String refId, String question, String helpText, boolean required, String attachmentUrl, int maxLength) {
        super(refId, question, helpText, required, attachmentUrl);
        this.maxLength = maxLength;
    }

    @Override
    public ValidationResult validate(String value) throws IllegalArgumentException {

        if (isRequired() && StringUtils.isBlank(value))
            return new ValidationResult(getRefId(), "Cannot be empty");

        if (value.length() > maxLength)
            return new ValidationResult(getRefId(), String.format("Maximum %d charecters allowed ", this.maxLength));

        return null;

    }

    @Override
    public void validateElements() throws IllegalArgumentException {
        if (this.maxLength > 100)
            throw new IllegalArgumentException("Max length can only 100");
    }
}
