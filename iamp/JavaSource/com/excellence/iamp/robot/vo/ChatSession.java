package com.excellence.iamp.robot.vo;

import java.util.Date;

/**
 * 职责：聊天对话基本信息实体类
 *
 */
public class ChatSession {
	//对话Id
	private String sessionId;
	//应用编号
	private String appCode;
	//机器人编号
	private String robotCode;
	//会员ID
	private String memberId;
	//会员账号
	private String memberAccount;
	//会员姓名
	private String memberName;
	//客户端IP
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
	//创建时间
	private Date createDate;
	//有效时间
	private Date effectiveTime;
	//对话状态
	private int sessionStatus;
}
