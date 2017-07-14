package com.rose.web;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StageLogRepository extends JpaRepository<StageLog, Integer> {
	public List<StageLog> findByStudentid(Integer student_id);
}
