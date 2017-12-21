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
	public List<Task> getByCondition(Map paramMap);
}