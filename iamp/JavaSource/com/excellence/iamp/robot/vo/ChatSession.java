package com.excellence.iamp.robot.vo;

import java.util.Date;

/**
 * ְ������Ի�������Ϣʵ����
 *
 */
public class ChatSession {
	//�Ի�Id
	private String sessionId;
	//Ӧ�ñ��
	private String appCode;
	//�����˱��
	private String robotCode;
	//��ԱID
	private String memberId;
	//��Ա�˺�
	private String memberAccount;
	//��Ա����
	private String memberName;
	//�ͻ���IP
	private String clientIp;
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getRobotCode() {
		return robotCode;
	}
	public void setRobotCode(String robotCode) {
		this.robotCode = robotCode;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberAccount() {
		return memberAccount;
	}
	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public int getSessionStatus() {
		return sessionStatus;
	}
	public void setSessionStatus(int sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
	//����ʱ��
	private Date createDate;
	//��Чʱ��
	private Date effectiveTime;
	//�Ի�״̬
	private int sessionStatus;
}
