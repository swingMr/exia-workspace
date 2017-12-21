package com.excellence.iaserver.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iacommon.common.util.T;
import com.excellence.iamp.robot.vo.AnswerPart;
import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iamp.robot.vo.ChatSession;
import com.excellence.iamp.robot.vo.RobotDef;
import com.excellence.iamp.service.RobotChatMngService;
import com.excellence.iamp.vo.enums.TaskStatus;
import com.excellence.iaserver.common.util.Constant;
import com.excellence.iaserver.common.util.DateHandlerUtil;
import com.excellence.iaserver.common.vo.enums.ESessionStatus;
import com.excellence.iaserver.enums.EMsgType;
import com.excellence.iaserver.enums.ESpeakerType;
import com.excellence.iaserver.robot.AnswerInfo;
import com.excellence.iaserver.robot.AnswerStrategy;
import com.excellence.iaserver.robot.ChatContext;
import com.excellence.iaserver.robot.ExactAnswerStrategy;
import com.excellence.iaserver.robot.FindAnswerPart;
import com.excellence.iaserver.robot.FindAnswerWorker;
import com.excellence.iaserver.robot.ReplyChatMsg;
import com.excellence.iaserver.service.ChatSessionService;
import com.github.pagehelper.PageInfo;
import com.excellence.iaserver.service.RecogniseOntologyService;

@Service("IaServerChatSessionService")
public class ChatSessionServiceImpl implements ChatSessionService{
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private RobotChatMngService robotChatMngService;
	
	@Autowired
	private RecogniseOntologyService recogniseOntologyService;
	
	private static List<AnswerStrategy> ANSWERSTRATEGYS;
	
	static{
		ANSWERSTRATEGYS = new ArrayList<AnswerStrategy>();
		String[] classNames = Constant.ANSWER_STRATEGY_CLASS.split(";");
		AnswerStrategy answerStrategy = null;
		for(String className:classNames){
			try {
				Class<?> clazz=Class.forName(className);
				answerStrategy = (AnswerStrategy)clazz.newInstance();
				ANSWERSTRATEGYS.add(answerStrategy);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public ReplyChatMsg askQuestion(ChatMsg chatMsg, ChatContext chatContext) throws Exception {
		//识别
		chatMsg = recogniseOntologyService.recognizeChatMsg(chatMsg);
		
		//创建消息
		chatMsg = robotChatMngService.createChatMsg(chatMsg);
		
		ReplyChatMsg replyChatMsg = new ReplyChatMsg();
		JSONObject sendMsg = new JSONObject();
		JSONArray replyMsg = new JSONArray();
		sendMsg.put("msgId", chatMsg.getMsgId());
		sendMsg.put("msgType", chatMsg.getMsgType());
		sendMsg.put("textContent", chatMsg.getTextContent());
		sendMsg.put("createTime", sdf.format(new Date()));
		
		/*ChatSession session = robotChatMngService.findChatSessionById(chatMsg.getSessionId());
		Map map = new HashMap();
		map.put("appCode", session.getAppCode());
		map.put("robotCode", session.getRobotCode());
		List<RobotDef> robotDefList = robotChatMngService.queryRobotDef(map);
		RobotDef robotDef = null;
		if(robotDefList!=null && robotDefList.size()>0) {
			robotDef = robotDefList.get(0);
		}*/
		
		//AnswerStrategy怎么用
		List<FindAnswerPart> parts = new ArrayList<FindAnswerPart>();
		if(ANSWERSTRATEGYS.size()>0) {
			for (AnswerStrategy answerStrategy : ANSWERSTRATEGYS) {
				List<FindAnswerPart> list = answerStrategy.match(chatContext, chatMsg);
				if(list!=null && list.size()>0) {
					parts.addAll(list);
				}
			}
		}
		//map
		List<FindAnswerWorker> workList = new ArrayList<FindAnswerWorker>();
		List<AnswerInfo> answerInfoList = new ArrayList<AnswerInfo>();
		if(parts!=null && parts.size()>0) {
			for (FindAnswerPart findAnswerPart : parts) {
				FindAnswerWorker findAnswerWorker = new FindAnswerWorker(findAnswerPart, chatContext, chatMsg);
				workList.add(findAnswerWorker);
				findAnswerWorker.start();
			}
			
			List<FindAnswerWorker> newList = new ArrayList<FindAnswerWorker>();
			int num = 0;
			while(num<500 && newList.size()<workList.size()) {
				for (FindAnswerWorker work : workList) {
					boolean flag = work.isFininshed();
					if(flag && !newList.contains(work)) {
						List<AnswerInfo> answerInfoList1 = work.getAnswers();
						if(answerInfoList1!=null && answerInfoList1.size()>0) {
							answerInfoList.addAll(answerInfoList1);
						}
						newList.add(work);
					}
				}
				Thread.sleep(10);
				num++;
			}
			
		}
		RobotDef robotDef = chatContext.getRobot();
		if(robotDef!=null && answerInfoList!=null && answerInfoList.size()>0) {
			for (AnswerInfo answerInfo : answerInfoList) {
				ChatMsg msg = new ChatMsg();
				msg.setAppCode(chatMsg.getAppCode());
				msg.setMsgType(answerInfo.getType());
				msg.setSessionId(chatMsg.getSessionId());
				msg.setSpeakerId(robotDef.getRobotId());
				msg.setSpeakerName(robotDef.getRobotName());
				msg.setSpeakerType(ESpeakerType.robot.getIndex());
				msg.setOriginalContent(answerInfo.getUrl());
				msg.setTextContent(answerInfo.getTitle());
				msg = robotChatMngService.createChatMsg(msg);
				JSONObject msg1 = new JSONObject();
				msg1.put("msgId", msg.getMsgId());
				msg1.put("msgType", answerInfo.getType());
				msg1.put("textContent", answerInfo.getTitle());
				msg1.put("originalContent", answerInfo.getUrl());
				msg1.put("createTime", sdf.format(new Date()));
				replyMsg.put(msg1);
			}
		}
		if(replyMsg.length()<=0){
			ChatMsg msg = new ChatMsg();
			msg.setAppCode(chatMsg.getAppCode());
			msg.setMsgType(EMsgType.text.name());
			msg.setSessionId(chatMsg.getSessionId());
			msg.setSpeakerId(robotDef.getRobotId());
			msg.setSpeakerName(robotDef.getRobotName());
			msg.setSpeakerType(ESpeakerType.robot.getIndex());
			msg.setOriginalContent("");
			msg.setTextContent("您的问题有点难，我还需要深入学习才行。");
			msg = robotChatMngService.createChatMsg(msg);
			JSONObject msg1 = new JSONObject();
			msg1.put("msgId", msg.getMsgId());
			msg1.put("msgType", EMsgType.text.name());
			msg1.put("textContent", msg.getTextContent());
			msg1.put("originalContent", "");
			msg1.put("createTime", sdf.format(new Date()));
			replyMsg.put(msg1);
		}
		replyChatMsg.setSendMsg(sendMsg);
		replyChatMsg.setReplyMsg(replyMsg);
		return replyChatMsg;
	}

	@Override
	public void closeChatSession(ChatSession chatSession) {
		chatSession.setSessionStatus(ESessionStatus.stop.getIndex());
		robotChatMngService.updateChatSession(chatSession);
	}
	public ChatSession createChatSession(RobotDef robot, ChatContext context) {
		ChatSession chatSession = new ChatSession();
		try {
			if(context.getUser() != null){
				String userId = context.getUser().get("id")==null ? "" : (String)context.getUser().get("id");
				String userAccount = context.getUser().get("account")==null ? "" : (String)context.getUser().get("account");
				String userName = context.getUser().get("name")==null ? "" : (String)context.getUser().get("name");
				String ip = context.getUser().get("ip")==null ? "" : (String)context.getUser().get("ip");
				chatSession.setAppCode(robot.getAppCode());
				chatSession.setRobotCode(robot.getRobotCode());
				chatSession.setClientIp(ip);
				chatSession.setMemberAccount(userAccount);
				chatSession.setMemberId(userId);
				chatSession.setMemberName(userName);
				chatSession.setCreateDate(new Date());
				chatSession.setEffectiveTime(DateHandlerUtil.dateHandler(new Date(), "month", 1, true));
				chatSession.setSessionStatus(ESessionStatus.normal.getIndex());
				robotChatMngService.createChatSession(chatSession);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chatSession;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<ChatMsg> getChatMsgsOfSession(ChatSession chatSession,int pageNo, int pageSize) {
		Map paramMap = new HashMap<String,Object>();
		paramMap.put("sessionId", chatSession.getSessionId());
		paramMap.put("appCode", chatSession.getAppCode());
		PageInfo<ChatMsg> chatMsgPage = robotChatMngService.pageChatMsg(paramMap, pageNo, pageSize);
		return chatMsgPage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatSession> queryChatSession(ChatContext context) {
		List<ChatSession> chatSessionList = null;
		try {
			if(context.getUser() != null){
				String userId = context.getUser().get("id")==null ? "" : (String)context.getUser().get("id");
				String userAccount = context.getUser().get("account")==null ? "" : (String)context.getUser().get("account");
				String userName = context.getUser().get("name")==null ? "" : (String)context.getUser().get("name");
				String ip = context.getUser().get("ip")==null ? "" : (String)context.getUser().get("ip");
				Map paramMap = new HashMap<String,Object>();
				paramMap.put("memberAccount", userAccount);
				paramMap.put("memberName", userName);
				paramMap.put("sessionStatus", ESessionStatus.normal.getIndex());
				if(StringUtils.isNotBlank(userId)){
					paramMap.put("memberId", userId);
				}
				chatSessionList = robotChatMngService.queryChatSession(paramMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chatSessionList;
	}
	
	public ChatSession queryChatSession(String id){
		ChatSession chatSession = robotChatMngService.findChatSessionById(id);
		return chatSession;
	}

}
