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
	 * ͨ��id����
	 * @param corpusId
	 * @return
	 */
	public TextCorpus findById(String corpusId);
	
	
	
	/**
	 * ����
	 * @param app
	 */
	public void insert(TextCorpus textCorpus);
	
	/**
	 * ��������
	 * @param app
	 */
	public void batchInsert(List<TextCorpus> list);
	
	/**
	 * ɾ��
	 * @param corpusId
	 */
	public void delete(String corpusId);
	/**
	 * ����
	 * @param textCorpus
	 */
	public void update(TextCorpus textCorpus);
	
	/**
	 * ͨ������ͳ��
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int countByCondition(Map paramMap);
	
	/**
	 * ͨ��������ѯ
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<TextCorpus> getByCondition(Map paramMap);
	
	
	public void createNewTable();
	
}
