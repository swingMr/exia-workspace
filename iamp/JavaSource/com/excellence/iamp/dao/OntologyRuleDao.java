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
	 * ����
	 * @param ontologyRule
	 */
    void insert(OntologyRule ontologyRule);
	
	/**
	 * ɾ��
	 * @param ruleId
	 */
	void delById(String ruleId);
	/**
	 * ����
	 * @param ontologyRule
	 */
	void update(OntologyRule serviceDefinition);

	/**
	 * ����������ѯ����
	 * @param condition
	 * @return
	 */
	List getOntologyRuleListByCondition(Map condition);
	
	/**
	 * ͳ������
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
}
