package com.excellence.iamp.robot.vo;

import java.util.Date;

/**
 * ְ��������Ϣ������
 *
 */
public class ChatMsg {
	//��ϢID
	private String msgId;
	//Ӧ�ñ��
	private String appCode;
	//�Ի�ID
	private String sessionId;
	//����������
	private int speakerType;
	//������Id;
	private String speakerId;
	//�������ǳ�
	private String speakerName;
	//����ʱ��
	private Date createTime;
	//��Ϣ����
	private String msgType;
	//�ı�����
	private String textContent;
	//ԭʼ����
	private String originalContent;
	//�����ı�ʶ��ı���
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
