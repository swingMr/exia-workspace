package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.OntologyRule;
import com.excellence.iamp.vo.ServiceDefinition;
import com.excellence.iamp.vo.ServiceType;


@Repository
public interface OntologyRuleDao {	
	List<OntologyRule> getList();
	
	/**
	 * 新增
	 * @param ontologyRule
	 */
    void insert(OntologyRule ontologyRule);
	
	/**
	 * 删除
	 * @param ruleId
	 */
	void delById(String ruleId);
	/**
	 * 更新
	 * @param ontologyRule
	 */
	void update(OntologyRule serviceDefinition);

	/**
	 * 根据条件查询规则
	 * @param condition
	 * @return
	 */
	List getOntologyRuleListByCondition(Map condition);
	
	/**
	 * 统计总数
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
}
