package com.excellence.iaserver.robot.part;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iaserver.common.util.Constant;
import com.excellence.iaserver.common.util.ElasticSearchUtil;
import com.excellence.iaserver.enums.EMsgType;
import com.excellence.iaserver.robot.AnswerInfo;
import com.excellence.iaserver.robot.ChatContext;

/**
 * 职责：根据标题检索综合知识部件实现类
 * @author liup
 */
public class FindResourceAnswerPart extends LocalFindAnswerPart {
	
	public FindResourceAnswerPart() {
		
	}

	public List<AnswerInfo> findAnswers(ChatContext context, ChatMsg msg) throws Exception {
		List<AnswerInfo> list = new ArrayList<AnswerInfo>();
		String text = msg.getTextContent();
		Map conditions = new HashMap();
		conditions.put("text", text);
		conditions.put("page", 1);
		conditions.put("pageSize", 5);
		String sql = "select * from "+ Constant.ELESTICSEARCH_DATABASE_NAME +" where title='"+text+"' limit 0,1";
		JSONObject res = ElasticSearchUtil.searchBySql(sql, 1, 1);
		
		if(res!=null && res.getInt("num")>0) {
			JSONArray arr = res.getJSONArray("informations");
			JSONObject a = arr.getJSONObject(0);
			if(a.getString("title").equals(text)) {
				AnswerInfo answerInfo = new AnswerInfo();
				answerInfo.setTitle(text+"【摘要】："+a.getJSONObject("attributes").getString("abstract"));
				answerInfo.setType(EMsgType.info.name());
				answerInfo.setUrl("http://<host>/exck#id="+a.getString("id")+"&libNum="+a.getJSONObject("attributes").getString("belong"));
				list.add(answerInfo);
			}
		}
		return list;
	}
}
