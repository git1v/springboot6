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
	@Column
	private Integer student_id;
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
	public ProgressLogEntity(Integer student_id, Integer stage, Integer task, Integer testdone, Integer testresult,
			Timestamp timestamp) {
		super();
		this.student_id = student_id;
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
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
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
