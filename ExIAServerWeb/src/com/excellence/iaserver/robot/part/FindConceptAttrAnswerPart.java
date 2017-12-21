package com.excellence.iaserver.robot.part;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.excellence.exke.common.vo.enums.EDataType;
import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iaserver.common.util.SpringFactoryUtil;
import com.excellence.iaserver.enums.EMsgType;
import com.excellence.iaserver.robot.AnswerInfo;
import com.excellence.iaserver.robot.ChatContext;
import com.excellence.iaserver.service.SearchKnowledgeService;

/**
 * 职责：实体属性检索部件实现类
 * @author liup
 */
public class FindConceptAttrAnswerPart extends LocalFindAnswerPart {
	
	private SearchKnowledgeService searchKnowledgeService;
	
	
	public FindConceptAttrAnswerPart() {
		searchKnowledgeService = (SearchKnowledgeService) SpringFactoryUtil.getObject("searchKnowledgeService");
	}
	
	public List<AnswerInfo> findAnswers(ChatContext context, ChatMsg msg) throws Exception {
		
		List<AnswerInfo> list = new ArrayList<AnswerInfo>();
		String partRule = this.getPartRule();
		String text = msg.getTextContent();
		List<String> attrList = new ArrayList<String>();
		if(StringUtils.isNotEmpty(partRule)) {
			JSONArray ruleArr = new JSONArray(partRule);
			if(ruleArr!=null && ruleArr.length()>0) {
				for(int i=0; i<ruleArr.length(); i++) {
					JSONObject obj = ruleArr.getJSONObject(i);
					if(obj!=null && obj.get("ruleArr") instanceof JSONArray) {
						String attrName = obj.getString("name");
						JSONArray arr = obj.getJSONArray("ruleArr");
						if(arr!=null && arr.length()>0) {
							for(int j=0; j<arr.length(); j++) {
								String rule = arr.getString(j);
								if(text.matches(rule)) {
									attrList.add(attrName);
									break;
								}
							}
						}
					}
				}
			}
			if(attrList.size()>0) {
				String ontoInfo = msg.getOntoInfo();
				if(StringUtils.isNotEmpty(ontoInfo)) {
					JSONObject ontoObj = new JSONObject(ontoInfo);
					for (Iterator iterator = ontoObj.keys(); iterator.hasNext();) {
						String key = (String) iterator.next();
						if(!"keywords".equals(key)) {
							JSONArray arr = ontoObj.getJSONArray(key);
							if(arr!=null && arr.length()>0) {
								for (int i = 0; i < arr.length(); i++) {
									JSONObject obj = arr.getJSONObject(i);
									JSONObject concept = searchKnowledgeService.getConcept(obj.getString("id"));
									if(concept!=null && concept.has("attributes") && concept.get("attributes") instanceof JSONArray) {
										JSONArray attrArr = concept.getJSONArray("attributes");
										if(attrArr!=null && attrArr.length()>0) {
											for (int j = 0; j < attrArr.length(); j++) {
												JSONObject attrObj = attrArr.getJSONObject(j);
												for (int k = 0; k < attrList.size(); k++) {
													if(attrList.get(k).equals(attrObj.getString("name"))) {
														String dataType = attrObj.getString("dataType");
														if(EDataType.string.name().equals(dataType)) {
															if(StringUtils.isNotEmpty(attrObj.getString("value"))) {
																AnswerInfo answerInfo = new AnswerInfo();
																answerInfo.setType(EMsgType.text.name());
																answerInfo.setTitle(concept.getString("content")+"的"+attrList.get(k)+"："+attrObj.getString("value"));
																answerInfo.setUrl(concept.getString("id"));
																list.add(answerInfo);
															}
														} else if(EDataType.concept.name().equals(dataType)) {
															JSONObject o = attrObj.getJSONObject("value");
															if(o!=null && o.has("content") && StringUtils.isNotEmpty(o.getString("content"))) {
																AnswerInfo answerInfo = new AnswerInfo();
																answerInfo.setType(EMsgType.text.name());
																answerInfo.setTitle(concept.getString("content")+"的"+attrList.get(k)+"："+o.getString("content"));
																answerInfo.setUrl(o.getString("id"));
																list.add(answerInfo);
															}
														} else if(EDataType.date.name().equals(dataType)) {
															if(StringUtils.isNotEmpty(attrObj.get("value").toString())) {
																AnswerInfo answerInfo = new AnswerInfo();
																answerInfo.setType(EMsgType.text.name());
																answerInfo.setTitle(concept.getString("content")+"的"+attrList.get(k)+"："+attrObj.get("value").toString());
																answerInfo.setUrl(concept.getString("id"));
																list.add(answerInfo);
															}
														} else if(EDataType.array.name().equals(dataType)&& (attrObj.get("value") instanceof JSONArray)
																&& ((JSONArray)attrObj.get("value")).length()>0) {
															JSONArray valueArr = attrObj.getJSONArray("value");
															if(valueArr!=null && valueArr.length()>0) {
																AnswerInfo answerInfo = new AnswerInfo();
																answerInfo.setType(EMsgType.text.name());
																StringBuilder title = new StringBuilder();
																title.append(concept.getString("content")+"的"+attrList.get(k)+"：");
																for(int m = 0 ; m < valueArr.length() ; m++){
																	Object element = valueArr.get(m);
																	if(element instanceof JSONObject){ //数组中的元素为“概念”
																		title.append(((JSONObject)element).getString("content")+" ");
																	}else{
																		title.append(element.toString()+" ");
																	}
																}
																answerInfo.setTitle(title.toString());
																answerInfo.setUrl("");
																list.add(answerInfo);
															}
															
														}
													}
												}
											}
										}
									}
								}
								
							}
						}
					}
				}
			}
			
		}
		
		return list;
	}

	
}
