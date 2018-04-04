package com.cloudsea.forms.formservice.question.dto;

import java.io.Serializable;

public class UserForm implements Serializable {

    private static final long serialVersionUID = 2176908284959284963L;

    private String title;
    private String status;
    private String id;

    public String getTitle() {
        return title;
    }

    public UserForm(String title, String status, String id) {
        this.title = title;
        this.status = status;
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
