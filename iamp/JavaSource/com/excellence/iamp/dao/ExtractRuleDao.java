package com.excellence.iamp.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.ExtractRule;

/**
 * dao
 * @author liup
 *
 */
@Repository
public interface ExtractRuleDao {
	/**
	 * ͨ��id����
	 * @param appId
	 * @return
	 */
	public ExtractRule findById(String ruleId);
	
	/**
	 * ͨ����ú��ļ����Ͳ���
	 * @param appCode
	 * @param appPwd
	 * @return
	 */
	public List<ExtractRule> findByGenreAndFiletype(Map paramMap);
	
	/**
	 * ����
	 * @param app
	 */
	public void insert(ExtractRule extractRule);
	/**
	 * ɾ��
	 * @param appId
	 */
	public void delete(String ruleId);
	/**
	 * ����
	 * @param serviceType
	 */
	public void update(ExtractRule extractRule);
	
	/**
	 * ͨ��������ѯ
	 * @param paramMap
	 * @return
	 */
	public List<ExtractRule> getByCondition(Map paramMap);
	
	public void createNewTable();
	
}
