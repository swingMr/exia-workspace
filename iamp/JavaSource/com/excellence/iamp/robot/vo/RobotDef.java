package com.excellence.iamp.robot.vo;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 职责：聊天机器人定义信息实体类
 *
 */
public class RobotDef {
	//机器人Id
	private String robotId;
	//机器人编号
	private String robotCode;
	//机器人昵称
	private String robotName;
	//App编号
	private String appCode;
	//服务欢迎词
	private String serviceGreeting;
	//服务时间段  缺省值'00:00-24:00'
	private String serviceTime;
	//创建时间
	private Date createDate;
	//备注
	private String remark;
	//App名称 归属应用
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
