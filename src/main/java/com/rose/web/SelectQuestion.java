package com.rose.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
	@Autowired
	AnswerLogEntityRepository anslogRepository;
	int studentid=101;
	public ArrayList<Integer> select(int COUNT_OF_QUESTION){
		ArrayList<Integer> allIds=new ArrayList<>();

		List<QuestionData>data=repository.findAll();
		List<UserList>userlist=userlistRepository.findAll();
		System.out.println("userlist size= "+userlist.size());
		
		//student_idを指定してanslogテーブルから解答状況を取得する
		HashMap done=new HashMap();
		
		List<AnswerLogEntity> anslogList=anslogRepository.findByStudentid((Integer)studentid);
		for(AnswerLogEntity anslog:anslogList){
			int qnum=anslog.getQnum();
			AnswerStatusBean ansBean;
			if(!done.containsKey(qnum)){
				int sum=anslog.getLog();
				int count=1;
				double rate=((double)sum/(double)count);
				ansBean=new AnswerStatusBean(qnum, sum, count, rate);
				done.put(qnum, ansBean);
			}else{
				ansBean=(AnswerStatusBean)done.get(qnum);
				int log=anslog.getLog();
				int sum=log+ansBean.getSum();
				int count=ansBean.getCount()+1;
				double rate=((double)sum/(double)count);
				ansBean.setSum(sum);
				ansBean.setCount(count);
				ansBean.setRate(rate);
				done.put(qnum, ansBean);
			}
		}
		System.out.println("既に解答した設問数= "+done.size());
		
		
		List<StageLog>stageloglist=stagelogRepository.findByStudentid((Integer)studentid);
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
