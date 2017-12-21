package com.excellence.iaserver.service;

import java.util.List;

import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iamp.robot.vo.ChatSession;
import com.excellence.iamp.robot.vo.RobotDef;
import com.excellence.iaserver.robot.ChatContext;
import com.excellence.iaserver.robot.ReplyChatMsg;
import com.github.pagehelper.PageInfo;

/**
 * 职责：聊天对话及问答服务
 * @author liup
 *
 */
public interface ChatSessionService {
	
	/**
	 * 请求问题并返回答案
	 * @param chatMsg
	 * @param chatContext 
	 * @return
	 * @throws Exception 
	 */
	public ReplyChatMsg askQuestion(ChatMsg chatMsg, ChatContext chatContext) throws Exception;
	
	/**
	 * 关闭会话
	 * @param chatSession
	 */
	public void closeChatSession(ChatSession chatSession);
	
	/**
	 * 新建会话
	 * @param robot
	 * @param context
	 * @return
	 */
	public ChatSession createChatSession(RobotDef robot, ChatContext context);
	
	public PageInfo<ChatMsg> getChatMsgsOfSession(ChatSession chatSession,int pageNo, int pageSize);
	
	public List<ChatSession> queryChatSession(ChatContext context);
	/**
	 * 查询某条消息
	 * @param id
	 * @return
	 */
	public ChatSession queryChatSession(String id);
}
