package com.excellence.iaserver.robot;

import java.util.List;

import com.excellence.iamp.robot.vo.ChatMsg;

/**
 * ְ���ʴ���ԣ������ж��Ƿ���Խ���Լ���λ��Ѱ�Ҳ���
 * @author liup
 */
public interface AnswerStrategy {
	
	/**
	 * Ѱ��ƥ��Ĳ���
	 * @param context
	 * @param msg
	 * @return
	 */
	public List<FindAnswerPart> match(ChatContext context, ChatMsg msg) throws Exception;
	
	/**
	 * Ѱ�Ҳ������еĲ���
	 * @param context
	 * @param msg
	 * @return
	 */
	public List<FindAnswerPart> findAllParts() throws Exception;
}
