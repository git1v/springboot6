package com.rose.web;

public class StatusSummaryBean {
	private int stage;
	private int totalQuestionSize;
	private int doneQuestionSize;
	private int okQuestionSize;
	private int restQuestionSize;
	private String status;
	
	public StatusSummaryBean(){}
	public StatusSummaryBean(int stage, int totalQuestionSize, int doneQuestionSize, int okQuestionSize,
			int restQuestionSize, String status) {
		super();
		this.stage = stage;
		this.totalQuestionSize = totalQuestionSize;
		this.doneQuestionSize = doneQuestionSize;
		this.okQuestionSize = okQuestionSize;
		this.restQuestionSize = restQuestionSize;
		this.status = status;
	}
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
	public int getTotalQuestionSize() {
		return totalQuestionSize;
	}
	public void setTotalQuestionSize(int totalQuestionSize) {
		this.totalQuestionSize = totalQuestionSize;
	}
	public int getDoneQuestionSize() {
		return doneQuestionSize;
	}
	public void setDoneQuestionSize(int doneQuestionSize) {
		this.doneQuestionSize = doneQuestionSize;
	}
	public int getOkQuestionSize() {
		return okQuestionSize;
	}
	public void setOkQuestionSize(int okQuestionSize) {
		this.okQuestionSize = okQuestionSize;
	}
	public int getRestQuestionSize() {
		return restQuestionSize;
	}
	public void setRestQuestionSize(int restQuestionSize) {
		this.restQuestionSize = restQuestionSize;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
