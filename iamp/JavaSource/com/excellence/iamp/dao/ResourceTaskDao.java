package com.excellence.iamp.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.ResourceTask;

/**
 * dao
 * @author chenghq
 *
 */
@Repository
public interface ResourceTaskDao {
	/**
	 * 通过id查找
	 * @param resWorkItemId
	 * @return
	 */
	public ResourceTask findById(String resTaskId);
	
	
	
	/**
	 * 新增
	 * @param app
	 */
	public void insert(ResourceTask resourceTask);
	
	/**
	 * 删除
	 * @param resWorkItemId
	 */
	public void delete(String resTaskId);
	/**
	 * 更新
	 * @param ResourceTask
	 */
	public void update(ResourceTask resourceTask);
	
	/**
	 * 通过条件查询
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<ResourceTask> getByCondition(Map paramMap);
	
	
	public void createNewTable();
	
	/**
	 * 统计总数
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
	
}
