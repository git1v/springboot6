package com.rose.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {
	@Autowired
	QuestionRepository repository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav){
		mav.addObject("title", "EnglishPractice");

		ArrayList<QuestionData> data=new ArrayList<>();
		ArrayList<Integer> ids=new ArrayList<>();
		ids.add(new Integer(1));
		ids.add(new Integer(2));
		ids.add(new Integer(3));
		ids.add(new Integer(4));
		for(Integer id : ids){
			data.add(repository.findById((Integer)id));
		}
		mav.addObject("data",data);
		mav.setViewName("index");
		return mav;
	}
	

}

