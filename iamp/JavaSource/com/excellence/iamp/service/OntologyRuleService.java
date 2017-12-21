package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.OntologyRule;
import com.github.pagehelper.PageInfo;


public interface OntologyRuleService {
	/**
	 * ��ȡ�������ķ�ҳ����
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageInfo getOntologyRules(int currentPage,int pageSize);
	
	/**
	 * ������ѯ��ȡ�������list
	 * @param condition
	 * @return
	 */
	public List getOntologyRuleListByCondition(Map condition);
	
	/**
	 * ���뱾������¼
	 * @param ontologyRule
	 */
	public OntologyRule insert(OntologyRule ontologyRule);
	
	/**
	 * ���ݹ���ɾ���������
	 * @param ruleaId
	 */
	public void delById(String ruleId);
	
	/**
	 * ���±�������¼
	 * @param ontologyRule
	 */
	public void update(OntologyRule ontologyRule);
}
