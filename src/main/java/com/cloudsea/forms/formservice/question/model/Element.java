package com.cloudsea.forms.formservice.question.model;

import java.util.UUID;

import com.cloudsea.forms.formservice.validate.Validate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import io.swagger.annotations.ApiModelProperty;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = ShortText.class), @JsonSubTypes.Type(value = LongText.class),
        @JsonSubTypes.Type(value = DropDown.class), @JsonSubTypes.Type(value = Number.class),
        @JsonSubTypes.Type(value = Rate.class), @JsonSubTypes.Type(value = MutipleChoice.class),
        @JsonSubTypes.Type(value = Email.class), @JsonSubTypes.Type(value = PhoneNumber.class),
        @JsonSubTypes.Type(value = Radio.class), @JsonSubTypes.Type(value = FormDate.class), @JsonSubTypes.Type(value = YesNo.class)

})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Element implements Validate {

    @ApiModelProperty(notes = "Auto-generated element id")
    private String refId = UUID.randomUUID().toString();

    @ApiModelProperty(notes = "Primary text to be displayed for any input field")
    private String question;

    @ApiModelProperty(notes = "Help text to be displayed for any input field")
    private String helpText;

    @ApiModelProperty(notes = "Required or not")
    private boolean required;

    @ApiModelProperty(notes = "Link to any image or file that should be displayed on input field")
    private String attachmentUrl;

    @ApiModelProperty(notes = "Order in which the element should be displayed in UI")
    private Integer order;

    public Element(String refId, String question, String helpText, boolean required, String attachmentUrl) {
        this.refId = refId;
        this.question = question;
        this.helpText = helpText;
        this.required = required;
        this.attachmentUrl = attachmentUrl;
    }

    public Element() {
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Element{" +
                "refId='" + refId + '\'' +
                ", question='" + question + '\'' +
                ", helpText='" + helpText + '\'' +
                ", required=" + required +
                ", attachmentUrl='" + attachmentUrl + '\'' +
                ", order=" + order +
                '}';
    }
}
