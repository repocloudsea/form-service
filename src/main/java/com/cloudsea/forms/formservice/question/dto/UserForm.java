package com.cloudsea.forms.formservice.question.dto;

import java.io.Serializable;

public class UserForm implements Serializable {

	private static final long serialVersionUID = 2176908284959284963L;
	
	private String title;
	private String status;

	public String getTitle() {
		return title;
	}

	public UserForm(String title, String status) {
		this.title = title;
		this.status = status;
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

}
