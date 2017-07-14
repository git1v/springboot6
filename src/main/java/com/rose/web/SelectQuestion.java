package com.rose.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
	final double OKNG_VRITERION=(double)0.8;
	public ArrayList<Integer> select(int COUNT_OF_QUESTION){
		ArrayList<Integer> allIds=new ArrayList<>();

		List<QuestionData>data=repository.findAll();
		List<UserList>userlist=userlistRepository.findAll();
		System.out.println("userlist size= "+userlist.size());
		
		//student_idを指定してanslogテーブルから解答状況を取得する
		HashMap<Integer, AnswerStatusBean> done=new HashMap<>();
		
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
		
		//基準に応じてOK問題とNG問題に分ける
		HashMap<Integer, AnswerStatusBean> OKMap=new HashMap<>();
		HashMap<Integer, AnswerStatusBean> NGMap=new HashMap<>();
		for (Entry<Integer, AnswerStatusBean> entry : done.entrySet()) {
            double rate = (double)entry.getValue().getRate();
            if(rate<OKNG_VRITERION){
            	OKMap.put((Integer)entry.getKey(), entry.getValue());
            }else{
            	NGMap.put((Integer)entry.getKey(), entry.getValue());
            }
        }
		List<StageLog>stageloglist=stagelogRepository.findByStudentid((Integer)studentid);
		System.out.println("student stage= "+stageloglist.get(0).getStage());
		System.out.println("OK設問数= "+OKMap.size());
		System.out.println("NG設問数= "+NGMap.size());
		
		
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
