package com.rose.web;


public class ResultBean {
	private Integer id;
	private String japanese;
	private String question;
	private String sentence;
	private String answer;
	private String input;
	private String result;
	
	
	public ResultBean(){}
	public ResultBean(Integer id, String japanese, String question, String sentence, String answer, String input,
			String result) {
		super();
		this.id = id;
		this.japanese = japanese;
		this.question = question;
		this.sentence = sentence;
		this.answer = answer;
		this.input = input;
		this.result = result;
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
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
