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
 * ְ��֪����������ʵ����
 * @author liup
 */
public class FindZhiHuAnswerPart extends LocalFindAnswerPart {

	public FindZhiHuAnswerPart() {
		
	}
	
	public List<AnswerInfo> findAnswers(ChatContext context, ChatMsg msg) throws Exception {
		List<AnswerInfo> list = new ArrayList<AnswerInfo>();
		//����������
		
		//
		return list;
	}
}
