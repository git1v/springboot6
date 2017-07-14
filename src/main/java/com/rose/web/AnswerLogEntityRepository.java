package com.rose.web;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerLogEntityRepository extends JpaRepository<AnswerLogEntity, Integer> {
	public List<AnswerLogEntity> findByStudentid(Integer studentid);
	
	
	public static final String FIND_STUDENT_AND_STAGE_BETWEEN="select * from anslog where student_id=?1 and stage between ?2 and ?3";
	@Query(value=FIND_STUDENT_AND_STAGE_BETWEEN, nativeQuery=true)
	public List<AnswerLogEntity> findByStudentidAndStage(int studentid, int stage, int end);
}
