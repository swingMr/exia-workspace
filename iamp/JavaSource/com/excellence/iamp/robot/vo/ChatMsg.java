package com.excellence.iamp.robot.vo;

import java.util.Date;

/**
 * 职责：聊天信息基础类
 *
 */
public class ChatMsg {
	//消息ID
	private String msgId;
	//应用编号
	private String appCode;
	//对话ID
	private String sessionId;
	//发言者类型
	private int speakerType;
	//发言者Id;
	private String speakerId;
	//发言者昵称
	private String speakerName;
	//发言时间
	private Date createTime;
	//消息类型
	private String msgType;
	//文本内容
	private String textContent;
	//原始内容
	private String originalContent;
	//根据文本识别的本体
	private String ontoInfo;
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public int getSpeakerType() {
		return speakerType;
	}
	public void setSpeakerType(int speakerType) {
		this.speakerType = speakerType;
	}
	public String getSpeakerId() {
		return speakerId;
	}
	public void setSpeakerId(String speakerId) {
		this.speakerId = speakerId;
	}
	public String getSpeakerName() {
		return speakerName;
	}
	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public String getOriginalContent() {
		return originalContent;
	}
	public void setOriginalContent(String originalContent) {
		this.originalContent = originalContent;
	}
	public String getOntoInfo() {
		return ontoInfo;
	}
	public void setOntoInfo(String ontoInfo) {
		this.ontoInfo = ontoInfo;
	}
}
