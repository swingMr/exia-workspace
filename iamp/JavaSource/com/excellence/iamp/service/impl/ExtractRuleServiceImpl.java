package com.excellence.iamp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.dao.ExtractRuleDao;
import com.excellence.iamp.service.ExtractRuleService;
import com.excellence.iamp.vo.ExtractRule;
import com.excellence.iamp.vo.enums.EStatus;
/**
 * ExtractRuleServiceImpl
 * @author liup
 *
 */
@Service
public class ExtractRuleServiceImpl implements ExtractRuleService {
	
	@Autowired
	private ExtractRuleDao extractRuleDao;
	
	public ExtractRule createExtractRule(ExtractRule extractRule) {
		if(StringUtils.isEmpty(extractRule.getRuleId())) {
			extractRule.setRuleId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		extractRuleDao.insert(extractRule);
		return extractRule;
	}

	public ExtractRule updateExtractRule(ExtractRule extractRule) {
		extractRuleDao.update(extractRule);
		return extractRule;
	}

	public void deleteExtractRule(ExtractRule extractRule) {
		extractRule.setStatus(EStatus.delete.getIndex());
		updateExtractRule(extractRule);
	}

	public void psyDeleteExtractRule(String extractRuleId) {
		extractRuleDao.delete(extractRuleId);
		
	}

	public ExtractRule getExtractRuleByExtractRuleCodeAndPwd(String extractRuleCode, String extractRulePwd) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("extractRuleCode", extractRuleCode);
		paramMap.put("extractRulePwd", extractRulePwd);
		List<ExtractRule> list = extractRuleDao.getByCondition(paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public ExtractRule findById(String extractRuleId) {
		return extractRuleDao.findById(extractRuleId);
	}
	

	@SuppressWarnings("rawtypes")
	public List<ExtractRule> getByCondition(Map paramMap) {
		return extractRuleDao.getByCondition(paramMap);
	}

	@Override
	public ExtractRule findByGenreAndFiletype(String genre, String fileType) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("genre", genre);
		paramMap.put("fileType", fileType);
		List<ExtractRule> list = extractRuleDao.getByCondition(paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	

}
