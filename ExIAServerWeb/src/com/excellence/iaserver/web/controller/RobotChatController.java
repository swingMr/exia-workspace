package com.excellence.iaserver.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iamp.robot.vo.ChatSession;
import com.excellence.iamp.robot.vo.RobotDef;
import com.excellence.iamp.service.RobotChatMngService;
import com.excellence.iamp.vo.enums.EStatus;
import com.excellence.iaserver.common.util.Jwt;
import com.excellence.iaserver.common.util.WebUtil;
import com.excellence.iaserver.common.vo.enums.ESessionStatus;
import com.excellence.iaserver.enums.EMsgType;
import com.excellence.iaserver.enums.ESpeakerType;
import com.excellence.iaserver.robot.ChatContext;
import com.excellence.iaserver.service.ChatSessionService;
import com.excellence.iaserver.robot.ReplyChatMsg;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/services/chat")
public class RobotChatController {
	
	@Autowired
	private RobotChatMngService robotChatMngService;
	
	@Autowired
	private ChatSessionService chatSessionService;
	
	private static final int user_speaker_type = 2;
	private static final int robot_speaker_type = 1;
	
	/*对话状态1--正常，99--已关闭，0--未启用 */
	private static final int session_normal_status = 1;
	private static final int session_closed_status = 99;
	private static final int session_unable_status = 0;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 当前会员与机器人建立对话关系
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/createsession")
	public String createSession(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		JSONObject json = new JSONObject();
		String extoken = MapUtils.getString(map, "extoken","");
		String context = MapUtils.getString(map, "context","");
		String robotId = MapUtils.getString(map, "robotId","");
		int status = 1;
		String msg = "有返回结果";
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		String sessionId = request.getSession().getId();
		if(isSuccess) {
			Map paramMap = new HashMap<String, Object>();
			String resultStr = JSONObject.valueToString(resultMap.get("data"));
			JSONObject resultObj = new JSONObject(resultStr);
			String appCode = resultObj.getString("appCode");
			String appPwd = resultObj.getString("appPwd");
			if(StringUtils.isNotBlank(context)){
				JSONObject obj1 = new JSONObject(context);
				ChatContext context1 = new ChatContext(obj1);
				String userId = "";
				if(context1.getUser() != null){
					userId = context1.getUser().get("id")==null ? "" : (String)context1.getUser().get("id");
				}
				List<ChatSession> chatSessionList = null;
				if(StringUtils.isNotBlank(userId)){
					Map paramMap2 = new HashMap<String, Object>();
					paramMap2.put("appCode", appCode);
					paramMap2.put("memberId", userId);
					paramMap2.put("sessionStatus", ESessionStatus.normal.getIndex());
					chatSessionList = robotChatMngService.queryChatSession(paramMap2);
				}
				RobotDef robotDef = null;
				ChatSession chatSession = null;
				if(chatSessionList != null && chatSessionList.size() > 0){
					chatSession = chatSessionList.get(0);
					String robotCode = chatSession.getRobotCode();
					Map paramMap2 = new HashMap();
					paramMap2.put("robotCode", robotCode);
					paramMap2.put("appCode", appCode);
					List<RobotDef> robotDefList = robotChatMngService.queryRobotDef(paramMap2);
					if(robotDefList != null){
						robotDef = robotDefList.get(0);
					}
					status = -1;
					msg = "已经有存在的正在聊天的对话";
				}else{
					try {
						if(StringUtils.isBlank(robotId)){
							Map paramMap1 = new HashMap();
							paramMap1.put("appCode", appCode);
							List<RobotDef> robotDefList = robotChatMngService.queryRobotDef(paramMap1);
							if(robotDefList != null){
								java.util.Random random=new java.util.Random();
								int result=random.nextInt(robotDefList.size());
								robotDef = robotDefList.get(result);
							}
						}else{
							robotDef = robotChatMngService.findRobotDefById(robotId);
						}
						chatSession = chatSessionService.createChatSession(robotDef, context1);
					} catch (Exception e) {
						status = 0;
						msg = "异常数据";
						e.printStackTrace();
					}
				}
				ChatMsg chatMsg = new ChatMsg();
				chatMsg.setAppCode(chatSession.getAppCode());
				chatMsg.setSessionId(chatSession.getSessionId());
				chatMsg.setMsgType("text");
				chatMsg.setTextContent(robotDef.getServiceGreeting());
				chatMsg.setOriginalContent(robotDef.getServiceGreeting());
				chatMsg.setSpeakerId(robotDef.getRobotId());
				chatMsg.setSpeakerName(robotDef.getRobotName());
				chatMsg.setSpeakerType(2);
				chatMsg.setCreateTime(new Date());
				robotChatMngService.createChatMsg(chatMsg);
				JSONObject obj = new JSONObject();
				obj.put("robotId", robotDef.getRobotId());
				obj.put("robotName", robotDef.getRobotName());
				obj.put("appCode", chatSession.getAppCode());
				obj.put("sessionId", chatSession.getSessionId());
				obj.put("effectiveTime", sdf.format(chatSession.getEffectiveTime()));
				obj.put("memberId", chatSession.getMemberId());
				obj.put("memberAccount", chatSession.getMemberAccount());
				obj.put("memberName", chatSession.getMemberName());
				obj.put("createTime", sdf.format(chatSession.getCreateDate()));
				JSONObject obj2 = new JSONObject();
				obj2.put("msgId", chatMsg.getMsgId());
				obj2.put("msgType", chatMsg.getMsgType());
				obj2.put("textContent", chatMsg.getTextContent());
				obj2.put("createTime", sdf.format(chatMsg.getCreateTime()));
				obj.put("replyMsg", obj2);
				json.put("data", obj);
			}else{
				status = 0;
				msg = "异常数据";
			}
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputHtml(response, json.toString());	
		return null;
	}
	
	/**
	 * 关闭指定聊天或会员自己的聊天对话
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/closesession")
	public String closeSession(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		JSONObject json = new JSONObject();
		String extoken = MapUtils.getString(map, "extoken","");
		String context = MapUtils.getString(map, "context","");
		String sessionId = MapUtils.getString(map, "sessionId","");
		int status = 1;
		String msg = "有返回结果";
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		if(isSuccess) {
			if(StringUtils.isNotBlank(sessionId)){
				try {
					ChatSession chatSession = robotChatMngService.findChatSessionById(sessionId);
					if(chatSession != null){
						chatSessionService.closeChatSession(chatSession);
						JSONObject obj = new JSONObject();
						Map paramMap = new HashMap<String, Object>();
						paramMap.put("robotCode", chatSession.getRobotCode());
						List<RobotDef> robotDefList = robotChatMngService.queryRobotDef(paramMap);
						if(robotDefList != null && robotDefList.size() > 0){
							obj.put("robotId", robotDefList.get(0).getRobotId());
							obj.put("robotName", robotDefList.get(0).getRobotName());
						}
						obj.put("appCode", chatSession.getAppCode());
						obj.put("sessionId", chatSession.getSessionId());
						obj.put("effectiveTime", sdf.format(chatSession.getEffectiveTime()));
						obj.put("memberId", chatSession.getMemberId());
						obj.put("memberAccount", chatSession.getMemberAccount());
						obj.put("memberName", chatSession.getMemberName());
						obj.put("createTime", sdf.format(chatSession.getCreateDate()));
						json.put("data", obj);
					}else{
						status = 999;
						msg = "不存在该对话！";
					}
				} catch (Exception e) {
					status = -1;
					msg = "程序异常！";
					e.printStackTrace();
				}
				
			}
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	/**
	 * 查询当前会员与机器人之间的正在进行中的对话信息
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/querysession")
	public String querySession(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		JSONObject json = new JSONObject();
		String extoken = MapUtils.getString(map, "extoken","");
		String context = MapUtils.getString(map, "context","");
		int status = 1;
		String msg = "有返回结果";
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		if(isSuccess) {
			/*String resultStr = JSONObject.valueToString(resultMap.get("data"));
			JSONObject resultObj = new JSONObject(resultStr);
			String appCode = resultObj.getString("appCode");
			String appPwd = resultObj.getString("appPwd");*/
			if(StringUtils.isNotBlank(context)){
				try {
					JSONObject obj1 = new JSONObject(context);
					ChatContext context1 = new ChatContext(obj1);
					List<ChatSession> chatSessionList = chatSessionService.queryChatSession(context1);
					if(chatSessionList != null && chatSessionList.size() > 0){
						JSONArray arr = new JSONArray();
						for(ChatSession chatSession : chatSessionList){
							JSONObject obj = new JSONObject();
							Map paramMap = new HashMap<String, Object>();
							paramMap.put("robotCode", chatSession.getRobotCode());
							List<RobotDef> robotDefList = robotChatMngService.queryRobotDef(paramMap);
							if(robotDefList != null && robotDefList.size() > 0){
								obj.put("robotId", robotDefList.get(0).getRobotId());
								obj.put("robotName", robotDefList.get(0).getRobotName());
							}
							obj.put("appCode", chatSession.getAppCode());
							obj.put("sessionId", chatSession.getSessionId());
							obj.put("effectiveTime", sdf.format(chatSession.getEffectiveTime()));
							obj.put("memberId", chatSession.getMemberId());
							obj.put("memberAccount", chatSession.getMemberAccount());
							obj.put("memberName", chatSession.getMemberName());
							obj.put("createTime", sdf.format(chatSession.getCreateDate()));
							arr.put(obj);
						}
						json.put("data", arr);
					}else{
						status = -1;
						msg = "无返回数据！";
					}
				} catch (Exception e) {
					status = 0;
					msg = "程序异常！";
					e.printStackTrace();
				}
				
			}
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	/**
	 * 查询指定对话的会员与机器人之间的聊天记录，按发言时间从大到小排序。
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/querymsg")
	public String queryMsg(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		JSONObject json = new JSONObject();
		String extoken = MapUtils.getString(map, "extoken","");
		String context = MapUtils.getString(map, "context","");
		String sessionId = MapUtils.getString(map, "sessionId","");
		int pageNo = MapUtils.getIntValue(map, "page",1);
		int pageSize = MapUtils.getIntValue(map, "pageSize",20);
		int status = 1;
		String msg = "有返回结果！";
		Map<String, Object> resultMap = Jwt.validToken(extoken);
		Boolean isSuccess = Boolean.valueOf(resultMap.get("isSuccess").toString());
		JSONObject obj1 = new JSONObject();
		if(isSuccess) {
			if(StringUtils.isNotBlank(sessionId)){
				try {
					ChatSession chatSession = robotChatMngService.findChatSessionById(sessionId);
					if(chatSession != null){
						PageInfo<ChatMsg>  chatMsgPage= chatSessionService.getChatMsgsOfSession(chatSession, pageNo, pageSize);
						List<ChatMsg> chatMsgList = chatMsgPage.getList();
						if(chatMsgList != null && chatMsgList.size() > 0){
							obj1.put("num", chatMsgPage.getTotal());
							obj1.put("page", chatMsgPage.getPages());
							obj1.put("pageSize", chatMsgPage.getPageSize());
							JSONArray arr = new JSONArray();
							for(ChatMsg chatMsg : chatMsgList){
								JSONObject obj = new JSONObject();
								obj.put("speakerId", chatMsg.getSpeakerId());
								obj.put("speakerName", chatMsg.getSpeakerName());
								obj.put("speakerType", chatMsg.getSpeakerType());
								obj.put("sessionId", chatMsg.getSessionId());
								obj.put("msgId", chatMsg.getMsgId());
								obj.put("memberId", chatSession.getMemberId());
								obj.put("memberAccount", chatSession.getMemberAccount());
								obj.put("memberName", chatSession.getMemberName());
								obj.put("createTime", sdf.format(chatMsg.getCreateTime()));
								obj.put("msgType", chatMsg.getMsgType());
								obj.put("textContent", chatMsg.getTextContent());
								obj.put("originalContent", chatMsg.getOriginalContent());
								arr.put(obj);
							}
							obj1.put("informations", arr);
						}else{
							status = 1;
							msg = "无返回结果！";
						}
					}
				} catch (Exception e) {
					status = 0;
					msg = "程序异常！";
					e.printStackTrace();
				}
				
			}
		}
		json.put("status", status);
		json.put("msg", msg);
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		json.put("data", obj1);
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
	
	/**
	 * 会员给机器人发送消息，机器人对消息内容进行回复。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/ask")
	public String ask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long startTime = System.currentTimeMillis();
		Map map = WebUtil.requestToMap(request);
		JSONObject json = new JSONObject();
		int status = 1;
		String msg = "";
		String extoken = MapUtils.getString(map, "extoken","");
		String context = MapUtils.getString(map, "context","");
		String sessionId = MapUtils.getString(map, "sessionId","");
		String msgType = MapUtils.getString(map, "msgType","text");
		String originalContent = MapUtils.getString(map, "originalContent","");
		if(StringUtils.isNotEmpty(sessionId)) {
			ChatSession chatSession = chatSessionService.queryChatSession(sessionId);
			if(chatSession!=null) {
				if(chatSession.getSessionStatus()==EStatus.normal.getIndex()) {
					ChatContext chatContext = new ChatContext(new JSONObject(context));
					Map map1 = new HashMap();
					map1.put("appCode", chatSession.getAppCode());
					map1.put("robotCode", chatSession.getRobotCode());
					List<RobotDef> robotDefList = robotChatMngService.queryRobotDef(map);
					RobotDef robotDef = null;
					if(robotDefList!=null && robotDefList.size()>0) {
						robotDef = robotDefList.get(0);
					}
					chatContext.setChatSession(chatSession);
					chatContext.setRobot(robotDef);
					if(StringUtils.equals(EMsgType.text.name(), msgType)) {
						ChatMsg chatMsg = new ChatMsg();
						chatMsg.setOriginalContent(originalContent);
						chatMsg.setSessionId(sessionId);
						chatMsg.setSpeakerId(chatContext.getUser().getString("id"));
						chatMsg.setSpeakerName(chatContext.getUser().getString("name"));
						
						chatMsg.setSpeakerType(ESpeakerType.user.getIndex());
						chatMsg.setMsgType(msgType);
						chatMsg.setTextContent(originalContent);
						chatMsg.setAppCode(chatSession.getAppCode());
						chatMsg.setCreateTime(new Date());
						ReplyChatMsg replyChatMsg = chatSessionService.askQuestion(chatMsg,chatContext);
						JSONObject obj = new JSONObject();
						obj.put("sendMsg", replyChatMsg.getSendMsg());
						obj.put("replyMsg", replyChatMsg.getReplyMsg());
						json.put("data", obj);
					}
				} else {
					status = -998;
					msg = "会话已关闭！";
				}
			} else {
				status = -999;
				msg = "不存在该对话！";
			}
		} else {
			status = -999;
			msg = "不存在该对话！";
		}
		json.put("status", status);
		json.put("msg", msg);
		
		json.put("timeCost", System.currentTimeMillis()-startTime+"毫秒");
		
		WebUtil.outputHtml(response, json.toString());
		return null;
	}
}
