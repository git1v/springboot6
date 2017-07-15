package com.rose.web;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {
	@Autowired
	QuestionRepository repository;
	@Autowired
	SelectQuestion selection;
	@Autowired
	AnswerLogEntityRepository logRepostitory;
	final int COUNT_OF_QUESTION=10;
	ArrayList<QuestionData>list;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav){
		
		list=new ArrayList<>();
		mav.addObject("title", "EnglishPractice");
			
		ArrayList<Integer> ids=selection.select(COUNT_OF_QUESTION);

		//ランダムに選ばれた数字をキーとして該当する設問を抜き出す
		for(int index=0;index<ids.size();index++){
			QuestionData data=repository.findById((Integer)ids.get(index));
			String dataName="data"+(index+1);
			list.add(data);
            mav.addObject(dataName,data);
		}
		
		mav.setViewName("index");
		return mav;
	}

	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView send(
			@RequestParam("input1")String input1,
			@RequestParam("input2")String input2,
			@RequestParam("input3")String input3,
			@RequestParam("input4")String input4,
			@RequestParam("input5")String input5,
			@RequestParam("input6")String input6,
			@RequestParam("input7")String input7,
			@RequestParam("input8")String input8,
			@RequestParam("input9")String input9,
			@RequestParam("input10")String input10,
			ModelAndView mav){
		ArrayList<String>inputList=new ArrayList<>();
			inputList.add(input1);
			inputList.add(input2);
			inputList.add(input3);
			inputList.add(input4);
			inputList.add(input5);
			inputList.add(input6);
			inputList.add(input7);
			inputList.add(input8);
			inputList.add(input9);
			inputList.add(input10);

		int student_no=101;

		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		ArrayList<ResultBean> resultBeans=new ArrayList<>();
		int cnt=0, log=0;
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
			
			resultBeans.add(new ResultBean(data.getId(),
											data.getJapanese(),
											data.getQuestion(),
											data.getSentence(),
											data.getAnswer(), 
											input,
											result));
			AnswerLogEntity logData
				=new AnswerLogEntity((Integer)student_no,(Integer)data.getId(), (Integer)log, (Integer)data.getStage(), timestamp );
			logRepostitory.saveAndFlush(logData);		
			
		}
		System.out.println("cnt= "+cnt);
		double rate=((double)cnt/(double)COUNT_OF_QUESTION)*100;
		String resultTitle="Result: 正答率= "+rate+" %";
		mav.addObject("title", resultTitle);
		mav.addObject("dataList", resultBeans);
		mav.setViewName("result");
		return mav;
	}
}

