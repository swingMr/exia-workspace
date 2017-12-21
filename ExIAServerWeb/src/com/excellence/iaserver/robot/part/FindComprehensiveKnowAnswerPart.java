package com.excellence.iaserver.robot.part;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iaserver.common.util.Constant;
import com.excellence.iaserver.common.util.SpringFactoryUtil;
import com.excellence.iaserver.enums.EMsgType;
import com.excellence.iaserver.robot.AnswerInfo;
import com.excellence.iaserver.robot.ChatContext;
import com.excellence.iaserver.service.RecogniseOntologyService;
import com.excellence.iaserver.service.ResouceSearchService;
import com.excellence.iaserver.service.SearchKnowledgeService;
import com.excellence.iaserver.service.impl.RecogniseOntologyServiceImpl;

/**
 * 职责：综合知识库检索部件实现类
 * @author liup
 */
public class FindComprehensiveKnowAnswerPart extends LocalFindAnswerPart {
	private RecogniseOntologyService recogniseOntologyService;
	private ResouceSearchService resouceSearchService;

	public FindComprehensiveKnowAnswerPart() {
		resouceSearchService = (ResouceSearchService) SpringFactoryUtil.getObject("resouceSearchService");
		recogniseOntologyService = (RecogniseOntologyService) SpringFactoryUtil.getObject("recogniseOntologyService");
	}
	
	public List<AnswerInfo> findAnswers(ChatContext context, ChatMsg msg) throws Exception {
		List<AnswerInfo> list = new ArrayList<AnswerInfo>();
		String partRule = this.getPartRule();
		String text = msg.getTextContent();
		List<String> libNameList = new ArrayList<String>();
		StringBuilder noText = new StringBuilder();
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
									libNameList.add(attrName);
									noText.append(rule+",");
									break;
								}
							}
						}
					}
				}
			}
			Map<String, String> libMap = new HashMap<String, String>();
			String[] names = Constant.TRSZYKNAMES;
			String[] codes = Constant.TRSZYKCODES;
			for (int i = 0; i < codes.length; i++) {
				libMap.put(names[i], codes[i]);
			}
			if(libNameList.size()>0) {
				String[] infoSourceCodes = null;
				if(!libNameList.contains("*")) {
					infoSourceCodes = new String[libNameList.size()];
					for (int i = 0; i < libNameList.size(); i++) {
						infoSourceCodes[0] = libMap.get(libNameList.get(i));
					}
				}
				String ontoInfo = msg.getOntoInfo();
				if(StringUtils.isNotEmpty(ontoInfo)) {
					System.out.println(ontoInfo);
					JSONObject obj = new JSONObject(ontoInfo);
					JSONObject noObj = recogniseOntologyService.recognizeConceptsOfTextSec("", "", noText.toString(), null, null, null);
					if(noObj!=null) {
						for (Iterator i1 = noObj.keys(); i1.hasNext();) {
							String key = (String) i1.next();
							if(!noObj.isNull(key) ) {
								JSONArray arr = noObj.getJSONArray(key);
								if(arr.length()>0) {
									for (int i = 0; i < arr.length(); i++) {
										JSONObject o = arr.getJSONObject(i);
										String content1 = o.getString("content");
										for (Iterator i2 = obj.keys(); i2.hasNext();) {
											String key2 = (String) i2.next();
											if(!obj.isNull(key2) ) {
												JSONArray arr2 = obj.getJSONArray(key2);
												if(arr2.length()>0) {
													for (int j = 0; j < arr2.length(); j++) {
														JSONObject o2 = arr2.getJSONObject(j);
														if(StringUtils.equals(o.getString("content"), o2.getString("content"))) {
															obj.getJSONArray(key2).remove(j);
															break;
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
					System.out.println(obj.toString());
					JSONObject resObj = resouceSearchService.searchByOntologies(null, obj, infoSourceCodes, null, 1, 5);
					if(resObj!=null && resObj.has("num") && resObj.getInt("num")>0) {
						JSONArray arr = resObj.getJSONArray("data");
						if(arr!=null && arr.length()>0) {
							for (int i = 0; i < arr.length(); i++) {
								AnswerInfo answerInfo = new AnswerInfo();
								answerInfo.setTitle(arr.getJSONObject(i).getString("title"));
								answerInfo.setType(EMsgType.info.name());
								answerInfo.setUrl("http://<host>/exck#id="+arr.getJSONObject(i).getString("id")+"&libNum="+arr.getJSONObject(i).getString("belong"));
								list.add(answerInfo);
							}
						}
						
					}
				}
			}
		}
		
		return list;
	}
}
