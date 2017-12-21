package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.App;
import com.excellence.iamp.vo.AppMember;
import com.excellence.iamp.vo.ExtractRule;
import com.github.pagehelper.PageInfo;

/**
 * 抽取规则service
 * @author liup
 *
 */
public interface ExtractRuleService {
	
	/**
	 * 创建规则
	 * @param app
	 * @return
	 */
	public ExtractRule createExtractRule(ExtractRule extractRule);
	
	/**
	 * 通过ruleId查找
	 * @param ruleId
	 * @return
	 */
	public ExtractRule findById(String ruleId);
	
	/**
	 * 更新extractRule
	 * @param extractRule
	 * @return
	 */
	public ExtractRule updateExtractRule(ExtractRule extractRule);
	
	/**
	 * 删除extractRule
	 * @param extractRule
	 */
	public void deleteExtractRule(ExtractRule extractRule);
	
	/**
	 * 物理删除extractRule
	 * @param ruleId
	 */
	public void psyDeleteExtractRule(String ruleId);
	
	/**
	 * 通过体裁和文件类型获取app
	 * @param genre
	 * @param fileType
	 * @return
	 */
	public ExtractRule findByGenreAndFiletype(String genre, String fileType);
	
	public List<ExtractRule> getByCondition(Map paramMap);
}
