package com.cloudsea.forms.formservice.questions.model;

import java.util.UUID;

import com.cloudsea.forms.formservice.validate.Validate;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = ShortText.class, name = "short_text"),
		@JsonSubTypes.Type(value = LongText.class, name = "long_text"),
		@JsonSubTypes.Type(value = DropDown.class, name = "dropdown"),
		@JsonSubTypes.Type(value = Number.class, name = "number"),
		@JsonSubTypes.Type(value = Rate.class, name = "rate"),
		@JsonSubTypes.Type(value = MutipleChoice.class, name = "mutiple_choice"),
		@JsonSubTypes.Type(value = Email.class, name = "email") })

public abstract class Element implements Validate {

	private String refId = UUID.randomUUID().toString();

	private String question;

	private String helpText;

	private boolean required;

	private String attachmentUrl;

	public String getRefId() {
		return refId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getHelpText() {
		return helpText;
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public Element(String question, String helpText, boolean required, String attachmentUrl) {
		this.question = question;
		this.helpText = helpText;
		this.required = required;
		this.attachmentUrl = attachmentUrl;
	}

	public Element() {
	}

}
