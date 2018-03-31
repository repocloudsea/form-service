package com.cloudsea.forms.formservice.question.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

enum Operation {

    REMOVE, UPDATE;
}


public class UpdateForm {

    @NotNull
    private Operation operation;
    @NotNull
    private String path;
    private String value;

    @JsonProperty("op")
    public Operation getOperation() {
        return operation;
    }

    public String getPath() {
        return path;
    }

    public String getValue() {
        return value;
    }

    public UpdateForm(Operation operation, String path, String value) {
        this.operation = operation;
        this.path = path;
        this.value = value;
    }

    public UpdateForm() {
    }

    @Override
    public String toString() {
        return "UpdateForm{" +
                "operation=" + operation +
                ", path='" + path + '\'' +
                ", value=" + value +
                '}';
    }
}
