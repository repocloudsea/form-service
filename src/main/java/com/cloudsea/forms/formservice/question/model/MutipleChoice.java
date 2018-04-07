package com.cloudsea.forms.formservice.question.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cloudsea.forms.formservice.validate.ValidationResult;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(value = "multipleChoice")
public class MutipleChoice extends Element {

    private List<String> choices;
    private Integer numberOfAllowedAnswers;

    public MutipleChoice(String refId, String question, String helpText, boolean required, String attachmentUrl, List<String> choices, Integer numberOfAllowedAnswers) {
        super(refId, question, helpText, required, attachmentUrl);
        this.choices = choices;
        this.numberOfAllowedAnswers = numberOfAllowedAnswers;
    }

    public MutipleChoice() {
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public Integer getNumberOfAllowedAnswers() {
        return numberOfAllowedAnswers;
    }

    public void setNumberOfAllowedAnswers(Integer numberOfAllowedAnswers) {
        this.numberOfAllowedAnswers = numberOfAllowedAnswers;
    }

    @Override
    public ValidationResult validate(String value) throws IllegalArgumentException {
        if (isRequired() && StringUtils.isBlank(value))
            return new ValidationResult(getRefId(), "Cannot be empty");

//        if (mutipleAllowed) {
//            List<String> answers = Arrays.asList(value.split(","));
//
//            if (answers.size() > choices.size())
//                return new ValidationResult(getRefId(), "Mismatch found");
//
//            if (Collections.disjoint(answers, choices))
//                return new ValidationResult(getRefId(), "Mismatch found");
//
//        } else {
//            if (!choices.contains(value))
//                return new ValidationResult(getRefId(), "Mismatch found");
//        }
        return null;
    }

    @Override
    public void validateElements() throws IllegalArgumentException {
        if (this.choices == null || this.choices.size() < 2)
            throw new IllegalArgumentException("There must be atleast two choices");
    }

}
