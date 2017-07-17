package com.rose.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StatusSummary {
	@Autowired
	ProgressLogEntityRepostitory progressRepository;
	@Autowired
	QuestionRepository repository;
	@Autowired
	QuetionType questionType;
	
	
	
	public void checkRate(int studentno){

		int currentStage=progressRepository.findCurrentStage(studentno);
		
		HashMap<String, Integer> sizeMap=this.getEachQuestionSize(studentno, currentStage);
		
		int restQuestionSize=sizeMap.get("restQuestionSize");
		
		if(restQuestionSize==0){
			this.updateStatusSummary(studentno, currentStage);
		}
	}
	
	
	
	public void updateStatusSummary(int studentno, int currentStage){
		
		
		ArrayList<ProgressLogEntity>progressListByStage=(ArrayList<ProgressLogEntity>)progressRepository.findByStage(currentStage);
		
		int task=progressListByStage.get(0).getTask();
		int testdone=progressListByStage.get(0).getTestdone();
		int testresult=progressListByStage.get(0).getTestresult();
		
		
		if(progressListByStage.size()==1 && task==0 && testdone==0 && testresult==0){
			Timestamp timestamp=new Timestamp(System.currentTimeMillis());
			ProgressLogEntity progressEntity=new ProgressLogEntity((Integer)studentno, (Integer)currentStage, 1, 0, 0, timestamp);
			progressRepository.saveAndFlush(progressEntity);
		}

	}
	
	
	
	public Set<StatusSummaryBean> getAllStagesSummary(int studentno){
		
		Set<StatusSummaryBean> statusSummaryBeanList=new LinkedHashSet<>();
		
		int currentStage=progressRepository.findCurrentStage(studentno);
		
		
		for(int stage=1;stage<=currentStage;stage++){
			
			ArrayList<ProgressLogEntity>progressListByStage=(ArrayList<ProgressLogEntity>)progressRepository.findByStage(stage);
			List<Timestamp>timestampList=new ArrayList<>();
			HashMap<Timestamp, ProgressLogEntity>timestampMap=new HashMap<>();
			
			
			for(ProgressLogEntity log:progressListByStage){
				Timestamp timestamp=log.getTimestamp();
				timestampMap.put(timestamp, log);
				timestampList.add(timestamp);
			}
			
			
			Collections.sort(timestampList,Comparator.reverseOrder());
			ProgressLogEntity latestLog=timestampMap.get(timestampList.get(0));
			
			statusSummaryBeanList.add(this.getEveryStageSummary(studentno, stage,latestLog));
		}
		return statusSummaryBeanList;
	}
	
	
	
	public HashMap<String, Integer> getEachQuestionSize(int studentno, int stage){
		
		HashMap<String, Integer> sizeMap=new HashMap<>();
		
		List<Integer>Ids=repository.findIdsBetween(stage,stage);
		int totalQuestionSize=Ids.size();
		
		
		HashMap<String, ArrayList<Integer>>listMap=questionType.getQuestionTypeList(studentno,stage,stage);
		ArrayList<Integer> OKList=listMap.get("OKList");
		ArrayList<Integer> NGList=listMap.get("NGList");
		
		
		int doneQuestionSize=OKList.size()+NGList.size();
		int okQuestionSize=OKList.size();
		
		
		double missNum=totalQuestionSize*(1-QuetionType.STAGE_CRITERION);
		BigDecimal bd = new BigDecimal(missNum);
        BigDecimal bd1 = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
        int enableMissNum=bd1.intValue();
        
        int calNum=totalQuestionSize-enableMissNum-okQuestionSize;
        int restQuestionSize=0;
        if(calNum>0){
        	restQuestionSize=calNum;
        }else{
        	restQuestionSize=0;
        }
        sizeMap.put("totalQuestionSize", totalQuestionSize);
        sizeMap.put("doneQuestionSize", doneQuestionSize);
        sizeMap.put("okQuestionSize", okQuestionSize);
        sizeMap.put("restQuestionSize", restQuestionSize);

        return sizeMap;
	}
	
	
	
	public StatusSummaryBean getEveryStageSummary(int studentno, int stage, ProgressLogEntity progressLog){
		//int stage=progressLog.getStage();

		HashMap<String, Integer> sizeMap=this.getEachQuestionSize(studentno, stage);
		
		int totalQuestionSize=sizeMap.get("totalQuestionSize");
		int doneQuestionSize=sizeMap.get("doneQuestionSize");
		int okQuestionSize=sizeMap.get("okQuestionSize");
		int restQuestionSize=sizeMap.get("restQuestionSize");
		
        String status=this.getStatus(progressLog);
        
        StatusSummaryBean statusSummaryBean=new StatusSummaryBean(stage, totalQuestionSize, doneQuestionSize, okQuestionSize,
    			restQuestionSize, status) ;
        
        return statusSummaryBean;
        
	}
	
	
	
	private String getStatus(ProgressLogEntity progressLog){
		int task=progressLog.getTask();
		int testdone=progressLog.getTestdone();
		int testresult=progressLog.getTestresult();
		String status="";
		if(task==0 && testdone==0 && testresult==0){
			status="課題未完了";
		}else if(task==1 && testdone==0 && testresult==0){
			status="未受験";
		}else if(task==1 && testdone==1 && testresult==0){
			status="不合格";
		}else if(task==1 && testdone==1 && testresult==1){
			status="合格";
		}else{
			System.out.println("this case shouldn't appear!!");
		}
		return status;
	}
}
