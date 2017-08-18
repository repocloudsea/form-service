package com.cloudsea.forms.formservice.answers.model;

import java.util.UUID;

public class Answer {

	private String id = UUID.randomUUID().toString();
	private String fieldRefID;
	private String value;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFieldRefID() {
		return fieldRefID;
	}
	public void setFieldRefID(String fieldRefID) {
		this.fieldRefID = fieldRefID;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
