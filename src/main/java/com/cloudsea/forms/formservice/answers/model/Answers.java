package com.cloudsea.forms.formservice.answers.model;

public class Answers {

	private String type;
	private String answer;
	private String question;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Answers(String type, String answer, String question) {
		this.type = type;
		this.answer = answer;
		this.question = question;
	}
	
	

}
