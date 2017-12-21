package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iamp.robot.vo.ChatSession;
import com.excellence.iamp.robot.vo.RobotDef;
import com.excellence.iamp.vo.Task;
import com.github.pagehelper.PageInfo;

/**
 * 职责：机器人问答管控接口
 * @author liup
 *
 */
public interface RobotChatMngService {
	
	public ChatMsg findChatMsgById(String chatMsgId);
	
	public ChatSession findChatSessionById(String chatSessionId);
	
	public RobotDef findRobotDefById(String robotDefId);
	
	public ChatMsg createChatMsg(ChatMsg chatMsg);
	
	public ChatSession createChatSession(ChatSession chatSession);
	
	/**
	 * 定义一个新的机器人信息
	 * @param robotDef
	 * @return
	 */
	public RobotDef createExRobot(RobotDef robotDef);
	
	public void deleteChatMsg(String msgId);
	
	public void deleteChatSession(String sessionId);
	
	public void deleteExRobot(String robotId);
	
	public List<ChatMsg> queryChatMsgs(Map conditions);
	
	public List<ChatSession> queryChatSession(Map conditions);
	
	public List<RobotDef> queryRobotDef(Map conditions);
	
	public ChatMsg updateChatMsg(ChatMsg chatMsg);
	
	public ChatSession updateChatSession(ChatSession chatSession);
	
	public RobotDef updateRobotDef(RobotDef robotDef);
	
	@SuppressWarnings("rawtypes")
	public PageInfo<ChatMsg> pageChatMsg(Map paramMap, int pageNo, int pageSize);
	
	@SuppressWarnings("rawtypes")
	public PageInfo<ChatSession> pageChatSession(Map paramMap, int pageNo, int pageSize);
	
	@SuppressWarnings("rawtypes")
	public PageInfo<RobotDef> pageRobotDef(Map paramMap, int pageNo, int pageSize);
	
}
