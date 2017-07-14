package com.rose.web;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@ComponentScan 
@Repository
public interface QuestionRepository extends JpaRepository<QuestionData, Integer> {
	public QuestionData findById(Integer id);
	public List<QuestionData> findByStageBetween(int start, int end);
	
	public static final String FIND_ID="select id from qlist";
	@Query(value=FIND_ID, nativeQuery=true)
	public List<Integer>findOnlyIds();
	
	public static final String FIND_ID_BETWEEN="select id from qlist where stage between ?1 and ?2";
	@Query(value=FIND_ID_BETWEEN, nativeQuery=true)
	public List<Integer>findIdsBetween(int start, int end);
	
}
