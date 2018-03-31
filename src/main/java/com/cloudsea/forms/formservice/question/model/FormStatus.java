package com.cloudsea.forms.formservice.question.model;

public enum FormStatus {
    DORMANT("dormant"), OPEN("open"), CLOSED("closed");

    private final String formStatus;


    FormStatus(String formStatus) {
        this.formStatus = formStatus;
    }
}
