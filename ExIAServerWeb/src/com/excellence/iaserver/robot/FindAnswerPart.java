package com.excellence.iaserver.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iaserver.common.util.Constant;
import com.excellence.iaserver.common.util.ElasticSearchUtil;
import com.excellence.iaserver.common.util.SpringFactoryUtil;
import com.excellence.iaserver.service.SearchKnowledgeService;

/**
 * Ѱ�Ҵ𰸳�����
 * @author liup
 */
public abstract class FindAnswerPart {
	
	/*�������*/
	private String partCode;
	/*������Ӧ����*/
	private String partStrategy;
	/*������Ӧ������*/
	private String answerImplClass;
	/*��������*/
	private String partTitle;
	/*��������*/
	private String partCategory;
	/*ƥ�����*/
	private String partRule;
	
	public abstract List<AnswerInfo> findAnswers(ChatContext context, ChatMsg msg) throws Exception;

	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public String getPartStrategy() {
		return partStrategy;
	}

	public void setPartStrategy(String partStrategy) {
		this.partStrategy = partStrategy;
	}

	public String getAnswerImplClass() {
		return answerImplClass;
	}

	public void setAnswerImplClass(String answerImplClass) {
		this.answerImplClass = answerImplClass;
	}

	public String getPartTitle() {
		return partTitle;
	}

	public void setPartTitle(String partTitle) {
		this.partTitle = partTitle;
	}

	public String getPartCategory() {
		return partCategory;
	}

	public void setPartCategory(String partCategory) {
		this.partCategory = partCategory;
	}

	public String getPartRule() {
		return partRule;
	}

	public void setPartRule(String partRule) {
		this.partRule = partRule;
	}

}
