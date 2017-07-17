package com.rose.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AnswerRate {
	/* 
	 * note: anslogListについて
	 * List<AnswerLogEntity> anslogList=anslogRepository.findByStudentidAndStage(studentid, start, end);
	 * 指定したユーザIDの、指定したステージに該当する全ての解答状況を取得したもの
	 */
	public HashMap<Integer, AnswerStatusBean> getRateMap(List<AnswerLogEntity> anslogList){
		//student_idを指定してanslogテーブルから解答状況を取得する
		HashMap<Integer, AnswerStatusBean> done=new HashMap<>();
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
		System.out.println("\n既に解答した設問数[@AnswerRate]= "+done.size());
		return done;
	}
	
	//指定したステージ間の正答率を取得
	public HashMap<Integer, AnswerStatusBean> getRateMap(int studentid, int start, int end){
		List<AnswerLogEntity> anslogList=anslogRepository.findByStudentidAndStage(studentid, start, end);
		System.out.println("anslogList[@SelectQuestion]= "+anslogList.size());
		return this.getRateMap(anslogList);
	}
	
	//現在のステージまでの全設問の正答率を取得
	@Autowired
	StageLogRepository stagelogRepository;
	@Autowired
	AnswerLogEntityRepository anslogRepository;
	public HashMap<Integer, AnswerStatusBean> getRateMap(int studentid){
		int start=1;
		List<StageLog>stageloglist=stagelogRepository.findByStudentid((Integer)studentid);
		int end=stageloglist.get(0).getStage();
		return this.getRateMap(studentid, start, end);
	}
	

}

