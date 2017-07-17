package com.rose.web;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProgressLogEntityRepostitory extends JpaRepository<ProgressLogEntity, Integer> {
	public ArrayList<ProgressLogEntity> findByStudentno(Integer studentno);
	
	public static final String FIND_STUDENT_AND_STAGE_BETWEEN="select max(stage) from progresslog where student_id=?";
	@Query(value=FIND_STUDENT_AND_STAGE_BETWEEN, nativeQuery=true)
	public Integer findCurrentStage(int studentno);
	
	public ArrayList<ProgressLogEntity> findByStage(Integer stage);
}
