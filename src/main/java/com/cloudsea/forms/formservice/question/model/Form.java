package com.cloudsea.forms.formservice.question.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Form {

	@Id
	private String id;
	@NotNull
	private String title;
	@NotNull
	private String userId;
	private String webHook;
	@NotNull
	private FormStatus status = FormStatus.DORMANT;
	private List<Element> elements;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWebHook() {
		return webHook;
	}

	public void setWebHook(String webHook) {
		this.webHook = webHook;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	public FormStatus getStatus() {
		return status;
	}

	public void setStatus(FormStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Form [id=" + id + ", title=" + title + ", userId=" + userId + ", webHook=" + webHook + ", elements="
				+ elements + "]";
	}

}
