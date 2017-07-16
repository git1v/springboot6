package com.rose.web;

import java.math.BigDecimal;

public class PastScoreBean {
	private int qnum;
	private int stage;
	private String sentence;
	private int cnt;
	private BigDecimal rate;
	private String result;
	public PastScoreBean(){}
	

	public PastScoreBean(int qnum, int stage, String sentence, int cnt, BigDecimal rate, String result) {
		super();
		this.qnum = qnum;
		this.stage = stage;
		this.sentence = sentence;
		this.cnt = cnt;
		this.rate = rate;
		this.result = result;
	}


	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
	}
	
	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}


	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
