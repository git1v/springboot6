package com.rose.web;

import java.util.ArrayList;

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
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav){
		mav.addObject("title", "EnglishPractice");

		ArrayList<Integer> ids=new ArrayList<>();
		ids.add(new Integer(1));
		ids.add(new Integer(2));
		ids.add(new Integer(3));
		ids.add(new Integer(4));
		
		for(int i=0;i<ids.size();i++){
			QuestionData data=repository.findById((Integer)ids.get(i));
			String dataName="data"+(i+1);
			mav.addObject(dataName,data);
		}
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView send(
			@RequestParam("input1")String input1
			,ModelAndView mav){
		mav.addObject("title", input1);
		mav.setViewName("index");
		return mav;
	}
}

