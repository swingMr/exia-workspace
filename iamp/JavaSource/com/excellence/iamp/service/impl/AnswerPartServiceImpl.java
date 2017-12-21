package com.excellence.iamp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.iamp.dao.AnswerPartDao;
import com.excellence.iamp.robot.vo.AnswerPart;
import com.excellence.iamp.service.AnswerPartService;
import com.excellence.iamp.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service("answerPartService")
public class AnswerPartServiceImpl implements AnswerPartService {
	@Autowired
	private AnswerPartDao answerPartDao;
	
	public PageInfo<AnswerPart> pageAnswerPart(Map paramMap, int pageNo, int pageSize){
			int count = answerPartDao.countByCondition(paramMap);
			PageHelper.startPage(pageNo, pageSize,false);
			List<AnswerPart> list = answerPartDao.getByCondition(paramMap);
			PageInfo<AnswerPart> pageInfo = new PageInfo(list);
			int pageSizes = Page.getPageCount(count, pageSize);
			pageInfo.setPages(pageSizes);
			pageInfo.setTotal(count);
			return pageInfo;	
	}
	
	public void create(AnswerPart answerPart){
		answerPartDao.insert(answerPart);
	}
	
	public AnswerPart findById(String id){
		return answerPartDao.findById(id);
	}
	
	public void update(AnswerPart answerPart){
		answerPartDao.update(answerPart);
	}
	
	public void delete(String id){
		answerPartDao.delete(id);
	}

	@Override
	public List<AnswerPart> getAnswerPartsByStrategy(String strategy) {
		Map paramMap = new HashMap();
		paramMap.put("partStrategy", strategy);
		List<AnswerPart> list = answerPartDao.getByCondition(paramMap);
		return list;
	}
}
