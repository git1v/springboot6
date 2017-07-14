package com.rose.web;

import org.springframework.context.annotation.Bean;


public class AnswerStatusBean {
	private int qnum;
	private int sum;
	private int count;
	private double rate;
	public AnswerStatusBean(int qnum, int sum, int count, double rate) {
		super();
		this.qnum = qnum;
		this.sum = sum;
		this.count = count;
		this.rate = rate;
	}
	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
}
