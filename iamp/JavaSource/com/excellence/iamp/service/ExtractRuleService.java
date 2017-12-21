package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.App;
import com.excellence.iamp.vo.AppMember;
import com.excellence.iamp.vo.ExtractRule;
import com.github.pagehelper.PageInfo;

/**
 * ��ȡ����service
 * @author liup
 *
 */
public interface ExtractRuleService {
	
	/**
	 * ��������
	 * @param app
	 * @return
	 */
	public ExtractRule createExtractRule(ExtractRule extractRule);
	
	/**
	 * ͨ��ruleId����
	 * @param ruleId
	 * @return
	 */
	public ExtractRule findById(String ruleId);
	
	/**
	 * ����extractRule
	 * @param extractRule
	 * @return
	 */
	public ExtractRule updateExtractRule(ExtractRule extractRule);
	
	/**
	 * ɾ��extractRule
	 * @param extractRule
	 */
	public void deleteExtractRule(ExtractRule extractRule);
	
	/**
	 * ����ɾ��extractRule
	 * @param ruleId
	 */
	public void psyDeleteExtractRule(String ruleId);
	
	/**
	 * ͨ����ú��ļ����ͻ�ȡapp
	 * @param genre
	 * @param fileType
	 * @return
	 */
	public ExtractRule findByGenreAndFiletype(String genre, String fileType);
	
	public List<ExtractRule> getByCondition(Map paramMap);
}
