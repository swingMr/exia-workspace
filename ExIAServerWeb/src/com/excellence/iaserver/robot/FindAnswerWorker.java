package com.excellence.iaserver.robot;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.robot.vo.ChatMsg;

/**
 * 职责：发现问题答案的线程工作者，继承Thread类
 * @author liup
 *
 */
public class FindAnswerWorker extends Thread{
	private FindAnswerPart part = null;
	private ChatContext context;
	private ChatMsg msg;
	private boolean isFininshed = false;
	
	private List<AnswerInfo> answers = null;
	public List<AnswerInfo> getAnswers() {
		return answers;
	}

	public boolean isFininshed() {
		return isFininshed;
	}

	public void run() {
		try {
			answers = part.findAnswers(context, msg);
			isFininshed = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isFininshed = true;
		}
	}
	
	private Map<String, Boolean> globaState;
	public FindAnswerWorker(FindAnswerPart part,ChatContext context, ChatMsg msg)
	{
		this.part = part;
		this.context = context;
		this.msg = msg;
	}
}
