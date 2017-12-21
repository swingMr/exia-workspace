package com.excellence.iaserver.robot.part;

import java.util.List;

import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iaserver.robot.AnswerInfo;
import com.excellence.iaserver.robot.ChatContext;
import com.excellence.iaserver.robot.FindAnswerPart;

/**
 * 职责：本体部件寻找答案抽象类
 * @author liup
 */
public abstract class LocalFindAnswerPart extends FindAnswerPart {

	@Override
	public abstract List<AnswerInfo> findAnswers(ChatContext context, ChatMsg msg) throws Exception;

}
