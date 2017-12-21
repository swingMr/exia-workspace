package com.excellence.iaserver.robot;

import org.json.JSONObject;

import com.excellence.iamp.robot.vo.ChatSession;
import com.excellence.iamp.robot.vo.RobotDef;

/**
 * ����������
 * @author liup
 *
 */
public class ChatContext {
	
	/**
	 * ����������Ϣ��
	 *   "id": "��ǰid",
     *   "account": "��ǰ�û��˺�",
     *   "ip": "��ǰIP��ַ",
     *   "name": "��ǰ�û�����",
     *   "exploreName": "���������",
	 *	 "exploreVersion": "������汾",
	 *	 "osName": "����ϵͳ����",
	 *	 "osVersion": "����ϵͳ�汾"
	 */
	private JSONObject user;
	
	/**
	 * ����������Ϣ��
	 *   "unitName": "���ڵ�λ����",
     *   "unitCode": "���ڵ�λ��֯����",
     *   "superiorUnitName": "�ϼ���λ����"
	 */
	private JSONObject org;
	
	/**
	 *  ����������Ϣ��
	 *   "countryName": "���ڹ��ҵ�������",
     *   "provinceName": "����ʡ��������",
     *   "cityName": "���ڳ�������"
	 */
	private JSONObject location;
	
	/**
	 *  ����������Ϣ��
	 *	 "year": "��ǰ���",
     *   "month": "��ǰ���£���ʽ��yyyy-MM",
     *   "date": "��ǰ���ڣ���ʽ��yyyy-MM-dd"
	 */
	private JSONObject time;
	
	/**
	 * ��ǰ�Ի�
	 */
	private ChatSession chatSession;
	
	/**
	 * ��ǰ�Ի���Ӧ�Ļ�����
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
