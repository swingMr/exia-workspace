package com.excellence.iaserver.robot;

import org.json.JSONObject;

import com.excellence.iamp.robot.vo.ChatSession;
import com.excellence.iamp.robot.vo.RobotDef;

/**
 * 聊天上下文
 * @author liup
 *
 */
public class ChatContext {
	
	/**
	 * 包含以下信息：
	 *   "id": "当前id",
     *   "account": "当前用户账号",
     *   "ip": "当前IP地址",
     *   "name": "当前用户姓名",
     *   "exploreName": "浏览器名称",
	 *	 "exploreVersion": "浏览器版本",
	 *	 "osName": "操作系统名称",
	 *	 "osVersion": "操作系统版本"
	 */
	private JSONObject user;
	
	/**
	 * 包含以下信息：
	 *   "unitName": "所在单位名称",
     *   "unitCode": "所在单位组织代码",
     *   "superiorUnitName": "上级单位名称"
	 */
	private JSONObject org;
	
	/**
	 *  包含以下信息：
	 *   "countryName": "所在国家地区名称",
     *   "provinceName": "所在省份洲名称",
     *   "cityName": "所在城市名称"
	 */
	private JSONObject location;
	
	/**
	 *  包含以下信息：
	 *	 "year": "当前年份",
     *   "month": "当前年月，格式：yyyy-MM",
     *   "date": "当前日期，格式：yyyy-MM-dd"
	 */
	private JSONObject time;
	
	/**
	 * 当前对话
	 */
	private ChatSession chatSession;
	
	/**
	 * 当前对话对应的机器人
	 */
	private RobotDef robot;
	
	public ChatContext(JSONObject context) {
		if(context.has("user")) {
			this.user = context.getJSONObject("user");
		}
		if(context.has("org")) {
			this.org = context.getJSONObject("org");
		}
		if(context.has("location")) {
			this.location = context.getJSONObject("location");
		}
		if(context.has("time")) {
			this.time = context.getJSONObject("time");
		}
	}

	public JSONObject getUser() {
		return user;
	}

	public void setUser(JSONObject user) {
		this.user = user;
	}

	public JSONObject getOrg() {
		return org;
	}

	public void setOrg(JSONObject org) {
		this.org = org;
	}

	public JSONObject getLocation() {
		return location;
	}

	public void setLocation(JSONObject location) {
		this.location = location;
	}

	public JSONObject getTime() {
		return time;
	}

	public void setTime(JSONObject time) {
		this.time = time;
	}

	public ChatSession getChatSession() {
		return chatSession;
	}

	public void setChatSession(ChatSession chatSession) {
		this.chatSession = chatSession;
	}

	public RobotDef getRobot() {
		return robot;
	}

	public void setRobot(RobotDef robot) {
		this.robot = robot;
	}
	
}
