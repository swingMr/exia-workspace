package com.excellence.iamp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.dao.ChatMsgDao;
import com.excellence.iamp.dao.ChatSessionDao;
import com.excellence.iamp.dao.RobotDefDao;
import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iamp.robot.vo.ChatSession;
import com.excellence.iamp.robot.vo.RobotDef;
import com.excellence.iamp.service.RobotChatMngService;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.Task;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RobotChatMngServiceImpl implements RobotChatMngService {
	
	@Autowired
	private ChatMsgDao chatMsgDao;
	
	@Autowired
	private RobotDefDao robotDefDao;
	
	@Autowired
	private ChatSessionDao chatSessionDao;

	public ChatMsg findChatMsgById(String chatMsgId) {
		return chatMsgDao.findById(chatMsgId);
	}
	
	public ChatSession findChatSessionById(String chatSessionId) {
		return chatSessionDao.findById(chatSessionId);
	}
	
	public RobotDef findRobotDefById(String robotDefId) {
		return robotDefDao.findById(robotDefId);
	}
	
	@Override
	public ChatMsg createChatMsg(ChatMsg chatMsg) {
		if(StringUtils.isEmpty(chatMsg.getMsgId())) {
			chatMsg.setMsgId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		chatMsgDao.insert(chatMsg);
		return chatMsg;
	}

	@Override
	public ChatSession createChatSession(ChatSession chatSession) {
		if(StringUtils.isEmpty(chatSession.getSessionId())) {
			chatSession.setSessionId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		chatSessionDao.insert(chatSession);
		return chatSession;
	}

	@Override
	public RobotDef createExRobot(RobotDef robotDef) {
		if(StringUtils.isEmpty(robotDef.getRobotId())) {
			robotDef.setRobotId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		robotDefDao.insert(robotDef);
		return robotDef;
	}

	@Override
	public void deleteChatMsg(String msgId) {
		chatMsgDao.delete(msgId);
	}

	@Override
	public void deleteChatSession(String sessionId) {
		chatSessionDao.delete(sessionId);
	}

	@Override
	public void deleteExRobot(String robotId) {
		robotDefDao.delete(robotId);
		
	}

	@Override
	public List<ChatMsg> queryChatMsgs(Map conditions) {
		return chatMsgDao.getByCondition(conditions);
	}

	@Override
	public List<ChatSession> queryChatSession(Map conditions) {
		return chatSessionDao.getByCondition(conditions);
	}

	@Override
	public List<RobotDef> queryRobotDef(Map conditions) {
		return robotDefDao.getByCondition(conditions);
	}

	@Override
	public ChatMsg updateChatMsg(ChatMsg chatMsg) {
		chatMsgDao.update(chatMsg);
		return chatMsg;
	}

	@Override
	public ChatSession updateChatSession(ChatSession chatSession) {
		chatSessionDao.update(chatSession);
		return chatSession;
	}

	@Override
	public RobotDef updateRobotDef(RobotDef robotDef) {
		robotDefDao.update(robotDef);
		return robotDef;
	}
	
	public PageInfo<ChatMsg> pageChatMsg(Map paramMap, int pageNo, int pageSize){
		int count = chatMsgDao.countByCondition(paramMap);
		PageHelper.startPage(pageNo, pageSize,false);
		List<ChatMsg> list = chatMsgDao.getByCondition(paramMap);
		PageInfo<ChatMsg> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}
	
	public PageInfo<ChatSession> pageChatSession(Map paramMap, int pageNo, int pageSize){
		int count = chatSessionDao.countByCondition(paramMap);
		PageHelper.startPage(pageNo, pageSize,false);
		List<ChatSession> list = chatSessionDao.getByCondition(paramMap);
		PageInfo<ChatSession> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}
	
	public PageInfo<RobotDef> pageRobotDef(Map paramMap, int pageNo, int pageSize){
		int count = robotDefDao.countByCondition(paramMap);
		PageHelper.startPage(pageNo, pageSize,false);
		List<RobotDef> list = robotDefDao.getByCondition(paramMap);
		PageInfo<RobotDef> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}
	
}
