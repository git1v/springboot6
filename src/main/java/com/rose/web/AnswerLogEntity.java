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

	
	@Column(name="student_id")
	private Integer studentid; 
	
	@Column
	private Integer qnum; 
	 
	@Column
	private Integer log;
	
	@Column
	private Integer stage; 
	 
	@Column
	private Timestamp timestamp;
	 
	public AnswerLogEntity(){}



	public AnswerLogEntity(Integer studentid, Integer qnum, Integer log, Integer stage,
			Timestamp timestamp) {
		super();

		this.studentid = studentid;
		this.qnum = qnum;
		this.log = log;
		this.stage = stage;
		this.timestamp = timestamp;
	}



	public Integer getNo() {
		return no;
	}



	public void setNo(Integer no) {
		this.no = no;
	}



	public Integer getStudentid() {
		return studentid;
	}



	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}



	public Integer getQnum() {
		return qnum;
	}



	public void setQnum(Integer qnum) {
		this.qnum = qnum;
	}



	public Integer getLog() {
		return log;
	}



	public void setLog(Integer log) {
		this.log = log;
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
