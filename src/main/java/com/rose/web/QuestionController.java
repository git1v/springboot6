package com.rose.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {
	@Autowired
	QuestionRepository repository;
	
	SelectQuestion selection;
	final int COUNT_OF_QUESTION=10;
	ArrayList<Integer> ids;
	ArrayList<QuestionData>list;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav){
		//selection=new SelectQuestion();
		
		ids=new ArrayList<>();
		list=new ArrayList<>();
		mav.addObject("title", "EnglishPractice");
		
ArrayList<Integer> allIds=new ArrayList<>();
		
		List<QuestionData> datas=repository.findAll();
		
		for(int index=0;index<datas.size();index++){
			allIds.add(datas.get(index).getId());
		}
		System.out.println("----- count of Ids: "+allIds.size());
		
		Collections.shuffle(allIds);
		ArrayList<Integer> selectedIds=new ArrayList<>();
		for(int cnt=0;cnt<COUNT_OF_QUESTION;cnt++){
			selectedIds.add(allIds.get(cnt));
			System.out.print(" : "+allIds.get(cnt));
		}
		
		ids=selectedIds;
		
		
		//ids=selection.select(COUNT_OF_QUESTION);
		
		
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

		ArrayList<ResultBean> resultBeans=new ArrayList<>();
		int cnt=0;
		for(int index=0;index<COUNT_OF_QUESTION;index++){
			QuestionData data=list.get(index);
			String input=inputList.get(index);
			String result="";
			
			if(data.getAnswer().equals(input)){
				result="○";
				cnt++;
			}else{
				result="×";
			}
			
			resultBeans.add(new ResultBean(data.getId(),
											data.getJapanese(),
											data.getQuestion(),
											data.getSentence(),
											data.getAnswer(), 
											input,
											result));
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

