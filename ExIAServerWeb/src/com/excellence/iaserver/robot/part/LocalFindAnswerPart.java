package com.excellence.iaserver.robot.part;

import java.util.List;

import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iaserver.robot.AnswerInfo;
import com.excellence.iaserver.robot.ChatContext;
import com.excellence.iaserver.robot.FindAnswerPart;

/**
 * ְ�𣺱��岿��Ѱ�Ҵ𰸳�����
 * @author liup
 */
public abstract class LocalFindAnswerPart extends FindAnswerPart {

	@Override
	public abstract List<AnswerInfo> findAnswers(ChatContext context, ChatMsg msg) throws Exception;

}
