package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.robot.vo.AnswerPart;
import com.github.pagehelper.PageInfo;

public interface AnswerPartService {
	
	public PageInfo<AnswerPart> pageAnswerPart(Map paramMap, int pageNo, int pageSize);
	
	public void create(AnswerPart answerPart);
	
	public AnswerPart findById(String id);
	
	public void update(AnswerPart answerPart);
	
	void delete(String id);
	
	/**
	 * 根据策略类型获取对应的答案部件
	 * @param strategy
	 * @return
	 */
	public List<AnswerPart> getAnswerPartsByStrategy(String strategy);
}
