package com.cloudsea.forms.formservice.questions.model;

import java.util.UUID;

import com.cloudsea.forms.formservice.validate.Validate;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = ShortText.class), @JsonSubTypes.Type(value = LongText.class),
		@JsonSubTypes.Type(value = DropDown.class), @JsonSubTypes.Type(value = Number.class),
		@JsonSubTypes.Type(value = Rate.class), @JsonSubTypes.Type(value = MutipleChoice.class),
		@JsonSubTypes.Type(value = Email.class), @JsonSubTypes.Type(value = PhoneNumber.class) })

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
