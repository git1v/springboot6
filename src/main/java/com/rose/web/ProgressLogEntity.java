package com.rose.web;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="progresslog")
public class ProgressLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer no;
	@Column(name="student_id")
	private Integer studentno;
	@Column
	private Integer stage;
	@Column
	private Integer task;
	@Column
	private Integer testdone;
	@Column
	private Integer testresult;
	@Column
	private Timestamp timestamp;
	public ProgressLogEntity(){}
	public ProgressLogEntity(Integer studentno, Integer stage, Integer task, Integer testdone, Integer testresult,
			Timestamp timestamp) {
		super();
		this.studentno = studentno;
		this.stage = stage;
		this.task = task;
		this.testdone = testdone;
		this.testresult = testresult;
		this.timestamp = timestamp;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Integer getStudentno() {
		return studentno;
	}
	public void setStudentno(Integer studentno) {
		this.studentno = studentno;
	}
	public Integer getStage() {
		return stage;
	}
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	public Integer getTask() {
		return task;
	}
	public void setTask(Integer task) {
		this.task = task;
	}
	public Integer getTestdone() {
		return testdone;
	}
	public void setTestdone(Integer testdone) {
		this.testdone = testdone;
	}
	public Integer getTestresult() {
		return testresult;
	}
	public void setTestresult(Integer testresult) {
		this.testresult = testresult;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
