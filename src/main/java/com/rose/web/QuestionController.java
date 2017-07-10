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
		ArrayList<QuestionData>list=new ArrayList<>();
		for(int i=0;i<ids.size();i++){
			QuestionData data=repository.findById((Integer)ids.get(i));
			list.add(data);
		}
		mav.addObject("data",list);
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView send(
			@ModelAttribute("formModel")ArrayList<String> list
			,ModelAndView mav){
		
		mav.addObject("title", list.get(0));
		mav.setViewName("index");
		return mav;
	}
}

