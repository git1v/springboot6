package com.rose.web;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RequestCalcuration {
	@Autowired
	AnswerLogEntityRepository logRepostitory;
	int cnt=0;
	double rate=(double)0.0;
	public ArrayList<ResultBean> getRequestResultBeans(
			int studentno, ArrayList<QuestionData>list,ArrayList<String>inputList,int COUNT_OF_QUESTION){
		
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		
		ArrayList<ResultBean> resultBeans=new ArrayList<>();
		
		int log=0;cnt=0;
		for(int index=0;index<COUNT_OF_QUESTION;index++){
			QuestionData data=list.get(index);
			String input=inputList.get(index);
			String result="";
			
			if(data.getAnswer().equals(input)){
				result="○";
				log=1;
				cnt++;
			}else{
				result="×";
				log=0;
			}
		
			resultBeans.add(new ResultBean(data.getId(), data.getStage(),data.getJapanese(),data.getQuestion(),	data.getSentence(),
											data.getAnswer(), input,result));
			AnswerLogEntity logData
				=new AnswerLogEntity((Integer)studentno,(Integer)data.getId(), (Integer)log, (Integer)data.getStage(), timestamp );
			logRepostitory.saveAndFlush(logData);		
			
		}
		System.out.println("cnt= "+cnt);
		rate=((double)cnt/(double)COUNT_OF_QUESTION)*100;
	
		return resultBeans;
	}
	public double getRate(){
		return rate;
	}
}
