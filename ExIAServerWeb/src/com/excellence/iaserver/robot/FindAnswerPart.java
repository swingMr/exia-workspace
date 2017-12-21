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
 * 寻找答案抽象类
 * @author liup
 */
public abstract class FindAnswerPart {
	
	/*部件编号*/
	private String partCode;
	/*部件对应策略*/
	private String partStrategy;
	/*部件对应策略类*/
	private String answerImplClass;
	/*部件名称*/
	private String partTitle;
	/*归属分类*/
	private String partCategory;
	/*匹配规则*/
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
