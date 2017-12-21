package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.robot.vo.ChatMsg;

/**
 * dao
 * @author Liuzh
 *
 */
@Repository
public interface ChatMsgDao {
	/**
	 * ����
	 * @param chatMsg
	 */
	public void insert(ChatMsg chatMsg);
	/**
	 * ɾ��
	 * @param msgId
	 */
	public void delete(String msgId);
	/**
	 * ����
	 * @param chatMsg
	 */
	public void update(ChatMsg chatMsg);
	
	public ChatMsg findById(String msgId);
	
	/**
	 * ͨ������ͳ���������ڷ�ҳ
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int countByCondition(Map paramMap);
	
	/**
	 * ͨ��������ѯ
	 * @param paramMap
	 * @return
	 */
	public List<ChatMsg> getByCondition(Map paramMap);
}
