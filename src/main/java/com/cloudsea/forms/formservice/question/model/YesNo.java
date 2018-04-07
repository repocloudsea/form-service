package com.cloudsea.forms.formservice.question.model;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName(value = "yesno")
public class YesNo extends Element {

    private String yesButtonTxt = "YES";
    private String noButtontxt = "NO";

    public YesNo(String refId, String question, String helpText, boolean required, String attachmentUrl, String yesButtonTxt, String noButtontxt) {
        super(refId, question, helpText, required, attachmentUrl);
        this.yesButtonTxt = yesButtonTxt;
        this.noButtontxt = noButtontxt;
    }

    public YesNo() {

    }

    @Override
    public ValidationResult validate(String value) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void validateElements() throws IllegalArgumentException {

        if (yesButtonTxt.length() > 100 || noButtontxt.length() > 100)
            throw new IllegalArgumentException(" Button name cannot be more than 100");
    }
}
