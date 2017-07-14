package com.rose.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SelectQuestion {
	
	@Autowired
	QuestionRepository repository;
	@Autowired
	UserListRepository userlistRepository;
	@Autowired
	StageLogRepository stagelogRepository;
	public ArrayList<Integer> select(int COUNT_OF_QUESTION){
		ArrayList<Integer> allIds=new ArrayList<>();

		List<QuestionData>data=repository.findAll();
		List<UserList>userlist=userlistRepository.findAll();
		System.out.println("userlist size= "+userlist.size());
		
		List<StageLog>stageloglist=stagelogRepository.findByStudentid((Integer)101);
		System.out.println("student stage= "+stageloglist.get(0).getStage());
		
		
		for(int index=0;index<data.size();index++){
			allIds.add(data.get(index).getId());
		}
		
		Collections.shuffle(allIds);
		ArrayList<Integer> selectedIds=new ArrayList<>();
		for(int cnt=0;cnt<COUNT_OF_QUESTION;cnt++){
			selectedIds.add(allIds.get(cnt));
		}
		return selectedIds;
	}

}
