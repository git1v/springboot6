package com.rose.web;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerLogEntityRepository extends JpaRepository<AnswerLogEntity, Integer> {
	public List<AnswerLogEntity> findByStudentid(Integer studentid);
}
