package com.rose.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
@ComponentScan 
@Component
public class SelectQuestionTest {

	@Test
	public void 戻り値は要素の数だけ返す(){
		SelectQuestion select=new SelectQuestion();
		Springboot6Application app=new Springboot6Application();
		app.main(null);
//		System.out.println(select.toString());
		ArrayList<Integer> list=null;
		try {
			list = select.select(10);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		int expected=340;
		int actual=list.size();
		assertThat(actual,is(expected));
	}
}
