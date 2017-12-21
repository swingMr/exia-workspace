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
	 * 新增
	 * @param chatMsg
	 */
	public void insert(ChatMsg chatMsg);
	/**
	 * 删除
	 * @param msgId
	 */
	public void delete(String msgId);
	/**
	 * 更新
	 * @param chatMsg
	 */
	public void update(ChatMsg chatMsg);
	
	public ChatMsg findById(String msgId);
	
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
	public List<ChatMsg> getByCondition(Map paramMap);
}
