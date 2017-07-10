package com.rose.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class SelectQuestion {
	@Autowired
	QuestionRepository repository;
	
	public ArrayList<Integer> select(int COUNT_OF_QUESTION){
		ArrayList<Integer> allIds=new ArrayList<>();
		System.out.println(" repository was called");
		List<QuestionData> data=repository.findAll();
		for(int index=0;index<data.size();index++){
			allIds.add(data.get(index).getId());
		}
		System.out.println("----- count of Ids: "+allIds.size());
		
		Collections.shuffle(allIds);
		ArrayList<Integer> selectedIds=new ArrayList<>();
		for(int cnt=0;cnt<COUNT_OF_QUESTION;cnt++){
			selectedIds.add(allIds.get(cnt));
			System.out.print(" : "+allIds.get(cnt));
		}
		return selectedIds;
	}
}
