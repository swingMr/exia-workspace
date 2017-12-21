package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.TaskLog;

@Repository
public interface TaskLogDao {
    int delete(String id);

    int insert(TaskLog taskLog);

    int insertSelective(TaskLog taskLog);

    TaskLog findById(String id);

    int update(TaskLog taskLog);

    int updateByPrimaryKey(TaskLog taskLog);
    
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
   	public List<TaskLog> getByCondition(Map paramMap);
}