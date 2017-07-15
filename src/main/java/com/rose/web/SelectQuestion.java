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
	@Autowired
	AnswerRate answerRate;
	@Autowired
	QuetionType qustionType;
	
	int studentid=101;

	public ArrayList<Integer> select(int COUNT_OF_QUESTION){

		ArrayList<Integer> selectedIds=new ArrayList<>();
		//このユーザデータは別に今は必要ない
		List<UserList>userlist=userlistRepository.findAll();
		System.out.println("userlist size= "+userlist.size());
		
		List<StageLog>stageloglist=stagelogRepository.findByStudentid((Integer)studentid);
		int stage=stageloglist.get(0).getStage();
		System.out.println("student stage= "+stage);
		
		List<Integer> IDList=repository.findOnlyIds();
		System.out.println("Total IDList size= "+IDList.size());
		
		final int start=1;
		int end=stageloglist.get(0).getStage();
		
//		List<QuestionData>stageData=repository.findByStageBetween(start, end);
		List<Integer>stageIDList=repository.findIdsBetween(start, end);
		
//		List<AnswerLogEntity> anslogList=anslogRepository.findByStudentid((Integer)studentid);
		List<AnswerLogEntity> anslogList=anslogRepository.findByStudentidAndStage(studentid, start, end);
		System.out.println("anslogList= "+anslogList.size());
		
		if(anslogList.size()!=0 && anslogList !=null){
			HashMap<Integer, AnswerStatusBean> done=answerRate.getRateMap(anslogList);
			selectedIds=qustionType.getSelectedIds(done, stageIDList, COUNT_OF_QUESTION);
			return selectedIds;
		}else{
			//anslogテーブルにまだそのユーザの情報が登録されていない場合は、該当ステージ内より任意に出題される
			Collections.shuffle(stageIDList);
			for(int index=0; index<COUNT_OF_QUESTION;index++){
				selectedIds.add(stageIDList.get(index));
			}
			return selectedIds;
		}
		
	}

}
