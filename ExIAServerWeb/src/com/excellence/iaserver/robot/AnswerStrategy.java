package com.excellence.iaserver.robot;

import java.util.List;

import com.excellence.iamp.robot.vo.ChatMsg;

/**
 * 职责：问答策略，问题判断是否可以解决以及定位答案寻找部件
 * @author liup
 */
public interface AnswerStrategy {
	
	/**
	 * 寻找匹配的部件
	 * @param context
	 * @param msg
	 * @return
	 */
	public List<FindAnswerPart> match(ChatContext context, ChatMsg msg) throws Exception;
	
	/**
	 * 寻找策略所有的部件
	 * @param context
	 * @param msg
	 * @return
	 */
	public List<FindAnswerPart> findAllParts() throws Exception;
}
