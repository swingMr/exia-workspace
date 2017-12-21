package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.robot.vo.ChatSession;

@Repository
public interface ChatSessionDao {
	/**
	 * ����
	 * @param chatSession
	 */
	public void insert(ChatSession chatSession);
	/**
	 * ɾ��
	 * @param sessionId
	 */
	public void delete(String sessionId);
	/**
	 * ����
	 * @param chatSession
	 */
	public void update(ChatSession chatSession);
	
	public ChatSession findById(String sessionId);
	
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
	public List<ChatSession> getByCondition(Map paramMap);
}
