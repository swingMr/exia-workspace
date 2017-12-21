package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.robot.vo.ChatSession;

@Repository
public interface ChatSessionDao {
	/**
	 * 新增
	 * @param chatSession
	 */
	public void insert(ChatSession chatSession);
	/**
	 * 删除
	 * @param sessionId
	 */
	public void delete(String sessionId);
	/**
	 * 更新
	 * @param chatSession
	 */
	public void update(ChatSession chatSession);
	
	public ChatSession findById(String sessionId);
	
	/**
	 * 通过条件统计总数用于分页
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int countByCondition(Map paramMap);
	
	/**
	 * 通过条件查询
	 * @param paramMap
	 * @return
	 */
	public List<ChatSession> getByCondition(Map paramMap);
}
