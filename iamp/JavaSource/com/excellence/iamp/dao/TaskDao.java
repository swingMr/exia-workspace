package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.Task;

@Repository
public interface TaskDao {
    int delete(String taskId);

    int insert(Task task);

    int insertSelective(Task task);

    Task findById(String taskId);

    int updateByPrimaryKeySelective(Task task);

    int update(Task task);
    
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
	public List<Task> getByCondition(Map paramMap);
}