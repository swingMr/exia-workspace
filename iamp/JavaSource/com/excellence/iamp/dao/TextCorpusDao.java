package com.excellence.iamp.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.TextCorpus;

/**
 * dao
 * @author huangjinyuan
 *
 */
@Repository
public interface TextCorpusDao {
	/**
	 * 通过id查找
	 * @param corpusId
	 * @return
	 */
	public TextCorpus findById(String corpusId);
	
	
	
	/**
	 * 新增
	 * @param app
	 */
	public void insert(TextCorpus textCorpus);
	
	/**
	 * 批量插入
	 * @param app
	 */
	public void batchInsert(List<TextCorpus> list);
	
	/**
	 * 删除
	 * @param corpusId
	 */
	public void delete(String corpusId);
	/**
	 * 更新
	 * @param textCorpus
	 */
	public void update(TextCorpus textCorpus);
	
	/**
	 * 通过条件统计
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int countByCondition(Map paramMap);
	
	/**
	 * 通过条件查询
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<TextCorpus> getByCondition(Map paramMap);
	
	
	public void createNewTable();
	
}
