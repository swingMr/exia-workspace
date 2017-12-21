package com.excellence.iaserver.robot;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 聊天机器人回复的消息内容
 * @author liup
 *
 */
public class ReplyChatMsg {
	/**
	 *  {
	 *      "msgId":聊天消息ID,
	 *      "msgType":消息类：text | image | voice | location | link
	 *      "textContent":消息内容的纯文本    
	 *      "createTime":对话创建时间，格式：yyyy-MM-dd HH:mm
     *  }
	 */
	private JSONObject sendMsg;
	
	/**
	 * [
	 *   {
	 *      "msgId":聊天消息ID,
	 *      "msgType":消息类：text | image | voice | location | link
	 *      "textContent":消息内容的纯文本,
	 *      "originalContent":消息原始内容,    
	 *      "createTime":对话创建时间，格式：yyyy-MM-dd HH:mm
	 *   },
	 *   {
	 *      "msgId":聊天消息ID,
	 *      "msgType":消息类：text | image | voice | location | link
	 *      "textContent":消息内容的纯文本,
	 *      "originalContent":消息原始内容,       
	 *      "createTime":对话创建时间，格式：yyyy-MM-dd HH:mm
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
