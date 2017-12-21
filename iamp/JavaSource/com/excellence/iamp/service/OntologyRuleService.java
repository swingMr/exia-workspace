package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.OntologyRule;
import com.github.pagehelper.PageInfo;


public interface OntologyRuleService {
	/**
	 * 获取本体规则的分页对象
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageInfo getOntologyRules(int currentPage,int pageSize);
	
	/**
	 * 条件查询获取本体规则list
	 * @param condition
	 * @return
	 */
	public List getOntologyRuleListByCondition(Map condition);
	
	/**
	 * 插入本体规则记录
	 * @param ontologyRule
	 */
	public OntologyRule insert(OntologyRule ontologyRule);
	
	/**
	 * 根据规则删除本体规则
	 * @param ruleaId
	 */
	public void delById(String ruleId);
	
	/**
	 * 更新本体规则记录
	 * @param ontologyRule
	 */
	public void update(OntologyRule ontologyRule);
}
