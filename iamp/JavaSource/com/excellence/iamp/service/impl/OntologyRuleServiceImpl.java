package com.excellence.iamp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.dao.OntologyRuleDao;
import com.excellence.iamp.service.OntologyRuleService;
import com.excellence.iamp.vo.OntologyRule;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class OntologyRuleServiceImpl implements OntologyRuleService{
	@Autowired
	private OntologyRuleDao ontologyRuleDao;
	
	public PageInfo getOntologyRules(int currentPage,int pageSize){
		 PageHelper.startPage(currentPage, pageSize,false);
		 PageInfo pageInfo = new PageInfo<OntologyRule>(ontologyRuleDao.getList());
		 return pageInfo;
	};
	
	public OntologyRule insert(OntologyRule ontologyRule){
		if(StringUtils.isEmpty(ontologyRule.getRuleId())){
			ontologyRule.setRuleId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		
		ontologyRuleDao.insert(ontologyRule);
		return ontologyRule;
	};
	
	public void delById(String ruleId){
		ontologyRuleDao.delById(ruleId);
	};
	
	public void update(OntologyRule ontologyRule){
		ontologyRuleDao.update(ontologyRule);
	}

	
	public List getOntologyRuleListByCondition(Map condition) {
		// TODO Auto-generated method stub
		return ontologyRuleDao.getOntologyRuleListByCondition(condition);
	}
}
