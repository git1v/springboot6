package com.rose.web;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionData, Integer> {
	public QuestionData findById(Integer id);
	public List<QuestionData> findAll();
}
