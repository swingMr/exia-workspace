package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.TextCorpus;
import com.github.pagehelper.PageInfo;

/**
 * 资源处理工作项service
 * @author huangjinyuan
 *
 */
public interface TextCorpusService {
	
	public void create(TextCorpus textCorpus);
	
	public void batchInsert(List<TextCorpus> list);
	
	public TextCorpus findById(String corpusId);
	
	public void update(TextCorpus textCorpus);
	
	public void delete(TextCorpus textCorpus);
	
	public void psyDelete(String corpusId);
	
	@SuppressWarnings("rawtypes")
	public List<TextCorpus> getByCondition(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public PageInfo<TextCorpus> page(Map paramMap, int pageNo, int pageSize);
	
	
}
