package com.rose.web;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="anslog")
public class AnswerLogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer no;

	
	@Column
	private Integer student_id; 
	
	@Column
	private Integer qnum; 
	 
	@Column
	private Integer log;
	
	@Column
	private Integer stage; 
	 
	@Column
	private Timestamp timestamp;
	 
	public AnswerLogEntity(){}



	public AnswerLogEntity(Integer student_id, Integer qnum, Integer log, Integer stage,
			Timestamp timestamp) {
		super();

		this.student_id = student_id;
		this.qnum = qnum;
		this.log = log;
		this.stage = stage;
		this.timestamp = timestamp;
	}




	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public Integer getQnum() {
		return qnum;
	}
	public void setQnum(Integer qnum) {
		this.qnum = qnum;
	}
	public Integer getStage() {
		return stage;
	}
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
