package com.rose.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

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
	final int COUNT_OK=3;
	final int COUNT_NG=7;
	
	public ArrayList<Integer> select(int COUNT_OF_QUESTION){
		ArrayList<Integer> selectedIds=new ArrayList<>();
		
		List<UserList>userlist=userlistRepository.findAll();
		System.out.println("userlist size= "+userlist.size());
		
		//student_idを指定してanslogテーブルから解答状況を取得する
		HashMap<Integer, AnswerStatusBean> done=new HashMap<>();
		
		List<StageLog>stageloglist=stagelogRepository.findByStudentid((Integer)studentid);
		int stage=stageloglist.get(0).getStage();
		System.out.println("student stage= "+stage);
		
		List<Integer> IDList=repository.findOnlyIds();
		System.out.println("Total IDList size= "+IDList.size());
		
		final int start=1;
		int end=stageloglist.get(0).getStage();
		
		List<QuestionData>stageData=repository.findByStageBetween(start, end);
		List<Integer>stageIDList=repository.findIdsBetween(start, end);
		
//		List<AnswerLogEntity> anslogList=anslogRepository.findByStudentid((Integer)studentid);
		List<AnswerLogEntity> anslogList=anslogRepository.findByStudentidAndStage(studentid, start, end);
		System.out.println("anslogList= "+anslogList.size());
		
		if(anslogList.size()!=0 && anslogList !=null){
			for(AnswerLogEntity anslog:anslogList){
				int qnum=anslog.getQnum();
				System.out.print(" : "+qnum);
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
			System.out.println();
		}else{
			//anslogテーブルにまだそのユーザの情報が登録されていない場合は、該当ステージ内より任意に出題される
			Collections.shuffle(stageIDList);
			for(int index=0; index<COUNT_OF_QUESTION;index++){
				selectedIds.add(stageIDList.get(index));
			}
			return selectedIds;
		}
		System.out.println("既に解答した設問数= "+done.size());
		
		//基準に応じてOK問題とNG問題に分ける
		HashMap<Integer, AnswerStatusBean> OKMap=new HashMap<>();
		HashMap<Integer, AnswerStatusBean> NGMap=new HashMap<>();
		for (Entry<Integer, AnswerStatusBean> entry : done.entrySet()) {
            double rate = (double)entry.getValue().getRate();
            if((double)rate>=(double)OKNG_VRITERION){
            	OKMap.put((Integer)entry.getKey(), entry.getValue());
            }else{
            	NGMap.put((Integer)entry.getKey(), entry.getValue());
            }
        }

		System.out.println("OK設問数= "+OKMap.size());
		System.out.println("NG設問数= "+NGMap.size());
		
		//
		Set<Integer> OKSet=OKMap.keySet();
		Set<Integer> NGSet=NGMap.keySet();
		ArrayList<Integer> OKList=new ArrayList<Integer>(OKSet);
		ArrayList<Integer> NGList=new ArrayList<Integer>(NGSet);
		Collections.shuffle(OKList);
		Collections.shuffle(NGList);
			
		for(int cnt=0;cnt<COUNT_OK && cnt<OKList.size();cnt++){
			selectedIds.add(OKList.get(cnt));
		}
		for(int cnt=0;cnt<COUNT_NG && cnt<NGList.size();cnt++){
			selectedIds.add(NGList.get(cnt));
		}
		if(selectedIds.size() < COUNT_OF_QUESTION){
			int count_notYet=COUNT_OF_QUESTION-(selectedIds.size() );
			stageIDList.removeAll(OKList);
			stageIDList.removeAll(NGList);
			Collections.shuffle(stageIDList);
			for(int cnt=0;cnt<count_notYet;cnt++){
				selectedIds.add(stageIDList.get(cnt));
			}
		}
		
		System.out.println("OKList= "+OKList);
		System.out.println("NGList= "+NGList);
		System.out.println("selectedIds= "+selectedIds);
		Collections.shuffle(selectedIds);

		return selectedIds;
	}

}
