package com.rose.web;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="qlist")
public class QuestionData {
	@Id
	private Integer id;
	
	@Column
	private String japanese;
	@Column
	private String question;
	@Column
	private String sentence;
	@Column
	private String answer;

	
	public QuestionData(){}


	public QuestionData(Integer id, String japanese, String question, String sentence, String answer) {
		super();
		this.id = id;
		this.japanese = japanese;
		this.question = question;
		this.sentence = sentence;
		this.answer = answer;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getJapanese() {
		return japanese;
	}


	public void setJapanese(String japanese) {
		this.japanese = japanese;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getSentence() {
		return sentence;
	}


	public void setSentence(String sentence) {
		this.sentence = sentence;
	}


	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
