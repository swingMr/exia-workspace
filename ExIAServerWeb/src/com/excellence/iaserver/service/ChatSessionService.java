package com.excellence.iaserver.service;

import java.util.List;

import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iamp.robot.vo.ChatSession;
import com.excellence.iamp.robot.vo.RobotDef;
import com.excellence.iaserver.robot.ChatContext;
import com.excellence.iaserver.robot.ReplyChatMsg;
import com.github.pagehelper.PageInfo;

/**
 * ְ������Ի����ʴ����
 * @author liup
 *
 */
public interface ChatSessionService {
	
	/**
	 * �������Ⲣ���ش�
	 * @param chatMsg
	 * @param chatContext 
	 * @return
	 * @throws Exception 
	 */
	public ReplyChatMsg askQuestion(ChatMsg chatMsg, ChatContext chatContext) throws Exception;
	
	/**
	 * �رջỰ
	 * @param chatSession
	 */
	public void closeChatSession(ChatSession chatSession);
	
	/**
	 * �½��Ự
	 * @param robot
	 * @param context
	 * @return
	 */
	public ChatSession createChatSession(RobotDef robot, ChatContext context);
	
	public PageInfo<ChatMsg> getChatMsgsOfSession(ChatSession chatSession,int pageNo, int pageSize);
	
	public List<ChatSession> queryChatSession(ChatContext context);
	/**
	 * ��ѯĳ����Ϣ
	 * @param id
	 * @return
	 */
	public ChatSession queryChatSession(String id);
}
