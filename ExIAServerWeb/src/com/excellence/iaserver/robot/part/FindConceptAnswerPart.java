package com.excellence.iaserver.robot.part;

import java.util.ArrayList;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iaserver.common.util.SpringFactoryUtil;
import com.excellence.iaserver.enums.EMsgType;
import com.excellence.iaserver.robot.AnswerInfo;
import com.excellence.iaserver.robot.ChatContext;
import com.excellence.iaserver.service.SearchKnowledgeService;

/**
 * 职责：概念/实体信息检索部件实现类
 * @author liup
 */
public class FindConceptAnswerPart extends LocalFindAnswerPart {

	private SearchKnowledgeService searchKnowledgeService = (SearchKnowledgeService) SpringFactoryUtil.getObject("searchKnowledgeService");
	
	public FindConceptAnswerPart() {
		
	}
	
	public List<AnswerInfo> findAnswers(ChatContext context, ChatMsg msg) throws Exception {
		List<AnswerInfo> list = new ArrayList<AnswerInfo>();
		String text = msg.getTextContent();
		try {
				JSONObject obj = searchKnowledgeService.getConceptsByNames("",new String[]{text});
				if(obj!=null && obj.getInt("status")==1) {
					JSONArray arr = obj.getJSONArray("data");
					if(arr!=null && arr.length()>0) {
						for(int i=0; i<arr.length(); i++) {
							JSONObject o = arr.getJSONObject(i);
							JSONArray parArr = o.getJSONArray("parentNames");
							String parentNames = parArr.join(";");
							if(parentNames.indexOf("自然人")!=-1) {
								AnswerInfo answerInfo = new AnswerInfo();
								answerInfo.setTitle(o.getString("content"));
								answerInfo.setType(EMsgType.touxiang.name());
								answerInfo.setUrl("/exEyeWap/resource.do?action=getPersonPic&conceptId="+o.getString("id"));
								list.add(answerInfo);
								
								AnswerInfo answerInfo1 = new AnswerInfo();
								answerInfo1.setTitle(o.getString("content")+"："+o.getString("definition"));
								answerInfo1.setType(EMsgType.person.name());
								answerInfo1.setUrl(o.getString("id"));
								list.add(answerInfo1);
							} else if(parentNames.indexOf("法人")!=-1) {
								AnswerInfo answerInfo1 = new AnswerInfo();
								answerInfo1.setTitle(o.getString("content")+"："+o.getString("definition"));
								answerInfo1.setType(EMsgType.org.name());
								answerInfo1.setUrl(o.getString("id"));
								list.add(answerInfo1);
							}
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
