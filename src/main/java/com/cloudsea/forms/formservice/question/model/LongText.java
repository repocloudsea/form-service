package com.cloudsea.forms.formservice.question.model;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "longText")
public class LongText extends Element {

    @ApiModelProperty(notes = "Maximum length to be allowed")
    private int maxLength = Integer.MAX_VALUE;

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public LongText() {
    }

    public LongText(String refId, String question, String helpText, boolean required, String attachmentUrl, int maxLength) {
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
        if (this.maxLength > Integer.MAX_VALUE)
            throw new IllegalArgumentException(String.format("Max length allowed is %d", Integer.MAX_VALUE));

    }
}
