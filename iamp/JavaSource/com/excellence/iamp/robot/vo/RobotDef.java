package com.excellence.iamp.robot.vo;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * ְ����������˶�����Ϣʵ����
 *
 */
public class RobotDef {
	//������Id
	private String robotId;
	//�����˱��
	private String robotCode;
	//�������ǳ�
	private String robotName;
	//App���
	private String appCode;
	//����ӭ��
	private String serviceGreeting;
	//����ʱ���  ȱʡֵ'00:00-24:00'
	private String serviceTime;
	//����ʱ��
	private Date createDate;
	//��ע
	private String remark;
	//App���� ����Ӧ��
	private String appName;
	
	public String getStartTime(){
		if(StringUtils.isNotBlank(serviceTime)){
			String start = serviceTime.substring(0, 2);
			return start;	
		}
		return "";
	}
	
	public String getEndTime(){
		if(StringUtils.isNotBlank(serviceTime)){
			String end = serviceTime.substring(8, 10);
			return end;	
		}
		return "";
	}
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getRobotId() {
		return robotId;
	}
	public void setRobotId(String robotId) {
		this.robotId = robotId;
	}
	public String getRobotCode() {
		return robotCode;
	}
	public void setRobotCode(String robotCode) {
		this.robotCode = robotCode;
	}
	public String getRobotName() {
		return robotName;
	}
	public void setRobotName(String robotName) {
		this.robotName = robotName;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getServiceGreeting() {
		return serviceGreeting;
	}
	public void setServiceGreeting(String serviceGreeting) {
		this.serviceGreeting = serviceGreeting;
	}
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
