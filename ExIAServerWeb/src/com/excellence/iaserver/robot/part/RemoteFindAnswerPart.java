package com.excellence.iaserver.robot.part;

import java.util.List;

import com.excellence.iamp.robot.vo.ChatMsg;
import com.excellence.iaserver.robot.AnswerInfo;
import com.excellence.iaserver.robot.ChatContext;
import com.excellence.iaserver.robot.FindAnswerPart;

/**
 * 职责：远程部件寻找答案
 * @author liup
 */
public class RemoteFindAnswerPart extends FindAnswerPart {
	private String remoteUrl;
	
	private int timeout;
	
	public RemoteFindAnswerPart() {
		
	}
	
	@Override
	public List<AnswerInfo> findAnswers(ChatContext context, ChatMsg msg) throws Exception {
		return null;
	}

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
