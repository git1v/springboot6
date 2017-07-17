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
public class QuetionType {
	final static double OKNG_CRITERION=(double)0.8;
	final static double STAGE_CRITERION=(double)0.85;
	final int COUNT_OK=3;
	final int COUNT_NG=7;
	@Autowired
	AnswerRate answerRate;
	/*
	 * note: stageIDListについて
	 * List<Integer>stageIDList=repository.findIdsBetween(start, end);
	 * 設問データの中から、指定のステージに該当する全設問のID(=qnum)のみをリストにして取得したもの
	 * 
	 * note:doneについて
	 * 既に解いた各設問において正答率を計算して<qnum, Bean>の形で格納したもの
	 * see @AnswerRate
	 */
	public HashMap<String, ArrayList<Integer>> getQuestionTypeList(HashMap<Integer, AnswerStatusBean> done){
		
		//基準に応じてOK問題とNG問題に分ける
		HashMap<Integer, AnswerStatusBean> OKMap=new HashMap<>();
		HashMap<Integer, AnswerStatusBean> NGMap=new HashMap<>();
		
		for (Entry<Integer, AnswerStatusBean> entry : done.entrySet()) {
            double rate = (double)entry.getValue().getRate();
            if((double)rate>=(double)OKNG_CRITERION){
            	OKMap.put((Integer)entry.getKey(), entry.getValue());
            }else{
            	NGMap.put((Integer)entry.getKey(), entry.getValue());
            }
        }

		System.out.println("OK設問数= "+OKMap.size());
		System.out.println("NG設問数= "+NGMap.size());
		
		
		Set<Integer> OKSet=OKMap.keySet();
		Set<Integer> NGSet=NGMap.keySet();
		ArrayList<Integer> OKList=new ArrayList<Integer>(OKSet);
		ArrayList<Integer> NGList=new ArrayList<Integer>(NGSet);
		Collections.shuffle(OKList);
		Collections.shuffle(NGList);
	
		System.out.println("OKList= "+OKList);
		System.out.println("NGList= "+NGList);
		HashMap<String, ArrayList<Integer>>listMap=new HashMap<>();
		listMap.put("OKList", OKList);
		listMap.put("NGList", NGList);
		
		return listMap;
		
	}
	//studentnoとステージの区間を指定してOK問題とNG問題のListを取得
	public HashMap<String, ArrayList<Integer>> getQuestionTypeList(int studentno, int start, int end){
		HashMap<Integer, AnswerStatusBean>done=answerRate.getRateMap(studentno, start, end);
		return this.getQuestionTypeList(done);
	}
	

	public ArrayList<Integer> getSelectedIds(HashMap<Integer, AnswerStatusBean> done,
			List<Integer>stageIDList,int COUNT_OF_QUESTION){		
		
		HashMap<String, ArrayList<Integer>>listMap
			=this.getQuestionTypeList(done);
		
		ArrayList<Integer> selectedIds=new ArrayList<>();
		
		ArrayList<Integer> OKList=listMap.get("OKList");
		ArrayList<Integer> NGList=listMap.get("NGList");
		
		for(int cnt=0;cnt<COUNT_OK && cnt<OKList.size();cnt++){
			selectedIds.add(OKList.get(cnt));
		}
		
		for(int cnt=0;cnt<COUNT_NG && cnt<NGList.size();cnt++){
			selectedIds.add(NGList.get(cnt));
		}
		
		/*
		 * OK問題やNG問題の組み合わせだけでは規定の設問数に満たない場合、
		 * ステージ内の全設問の中から足りない分だけ新規に出題
		 */
		
		//全てOK問題となった場合を考慮してバックアップを取っておく
		List<Integer>backup_stageIDList=new ArrayList<>(stageIDList);
		
		if(selectedIds.size() < COUNT_OF_QUESTION){
			int count_notYet=COUNT_OF_QUESTION-(selectedIds.size());
			System.out.println("count_notYet= "+count_notYet);
			stageIDList.removeAll(OKList);
			stageIDList.removeAll(NGList);
			Collections.shuffle(stageIDList);
			
			
			int addition=0;
			if(count_notYet>=stageIDList.size()){
				addition=stageIDList.size();
			}else{
				addition=count_notYet;
			}
			

			for(int cnt=0; cnt<addition;cnt++){
				selectedIds.add(stageIDList.get(cnt));
			}
		}
		
		/*
		 * 全てOK問題となった場合など現状のselectedIdsをステージ内全設問リストから排除
		 * その後ランダムに足りない分だけ出題
		 */
		if(selectedIds.size() < COUNT_OF_QUESTION){
			int count_notYet=COUNT_OF_QUESTION-(selectedIds.size());
			backup_stageIDList.removeAll(selectedIds);
			Collections.shuffle(backup_stageIDList);
			for(int cnt=0;cnt<count_notYet;cnt++){
				selectedIds.add(backup_stageIDList.get(cnt));
			}
		}
		
		System.out.println("selectedIds= "+selectedIds);
		Collections.shuffle(selectedIds);
		return selectedIds;
	}
	
	
}
