package com.excellence.iaserver.robot;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * ��������˻ظ�����Ϣ����
 * @author liup
 *
 */
public class ReplyChatMsg {
	/**
	 *  {
	 *      "msgId":������ϢID,
	 *      "msgType":��Ϣ�ࣺtext | image | voice | location | link
	 *      "textContent":��Ϣ���ݵĴ��ı�    
	 *      "createTime":�Ի�����ʱ�䣬��ʽ��yyyy-MM-dd HH:mm
     *  }
	 */
	private JSONObject sendMsg;
	
	/**
	 * [
	 *   {
	 *      "msgId":������ϢID,
	 *      "msgType":��Ϣ�ࣺtext | image | voice | location | link
	 *      "textContent":��Ϣ���ݵĴ��ı�,
	 *      "originalContent":��Ϣԭʼ����,    
	 *      "createTime":�Ի�����ʱ�䣬��ʽ��yyyy-MM-dd HH:mm
	 *   },
	 *   {
	 *      "msgId":������ϢID,
	 *      "msgType":��Ϣ�ࣺtext | image | voice | location | link
	 *      "textContent":��Ϣ���ݵĴ��ı�,
	 *      "originalContent":��Ϣԭʼ����,       
	 *      "createTime":�Ի�����ʱ�䣬��ʽ��yyyy-MM-dd HH:mm
	 *   }   
	 * ]
	 */
	private JSONArray replyMsg;

	public JSONObject getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(JSONObject sendMsg) {
		this.sendMsg = sendMsg;
	}

	public JSONArray getReplyMsg() {
		return replyMsg;
	}

	public void setReplyMsg(JSONArray replyMsg) {
		this.replyMsg = replyMsg;
	}
	
}
