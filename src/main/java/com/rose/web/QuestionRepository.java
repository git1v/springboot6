package com.rose.web;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionData, Integer> {
	public QuestionData findById(Integer id);
}
