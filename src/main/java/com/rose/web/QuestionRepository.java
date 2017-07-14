package com.rose.web;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@ComponentScan 
@Repository
public interface QuestionRepository extends JpaRepository<QuestionData, Integer> {
	public QuestionData findById(Integer id);
	public List<QuestionData> findAll();
}
