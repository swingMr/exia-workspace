package com.excellence.iaserver.robot.part;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iaserver.common.util.SpringFactoryUtil;
import com.excellence.iaserver.enums.EMsgType;
import com.excellence.iaserver.robot.AnswerInfo;
import com.excellence.iaserver.robot.ChatContext;
import com.excellence.iaserver.service.SearchKnowledgeService;

/**
 * 职责：相关实体检索部件实现类
 * @author liup
 */
public class FindRelateConceptAnswerPart extends LocalFindAnswerPart {
	private SearchKnowledgeService searchKnowledgeService = (SearchKnowledgeService) SpringFactoryUtil.getObject("searchKnowledgeService");
	
	public FindRelateConceptAnswerPart() {
		
	}
	
	public List<AnswerInfo> findAnswers(ChatContext context, ChatMsg msg) throws Exception {
		List<AnswerInfo> list = new ArrayList<AnswerInfo>();
		String partRule = this.getPartRule();
		String text = msg.getTextContent();
		List<String> relaList = new ArrayList<String>();
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
									relaList.add(attrName);
									break;
								}
							}
						}
					}
				}
			}
			if(relaList.size()>0) {
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
									if(concept!=null) {
//										JSONArray names = new JSONArray();
//										names.put(relaList.get(0));
//										JSONArray relaArr = searchKnowledgeService.getRelationDefinitionsByNames(names.toString());
//										System.out.println(relaArr);
										for (int j = 0; j < relaList.size(); j++) {
											JSONObject json = new JSONObject();
											json.put("elementType","relation");
											json.put("direction","outbound");
											json.put("content", relaList.get(j));
											JSONArray conditions = new JSONArray();
											conditions.put(json);
											JSONArray resArr = searchKnowledgeService.getOntologies(concept.getString("id"), conditions.toString());
											if(resArr!=null && resArr.length()>0) {
												AnswerInfo answerInfo = new AnswerInfo();
												answerInfo.setType(EMsgType.text.name());
												StringBuilder title = new StringBuilder();
												title.append(concept.getString("content")+relaList.get(j)+"：");
												for (int k = 0; k < resArr.length(); k++) {
													JSONArray conArr = resArr.getJSONObject(k).getJSONArray("concepts");
													if(conArr!=null && conArr.length()==2) {
														JSONObject con = conArr.getJSONObject(1);
														if(con!=null && con.has("content")) {
															title.append(con.getString("content")+" ");
														}
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
		
		return list;
	}
}
