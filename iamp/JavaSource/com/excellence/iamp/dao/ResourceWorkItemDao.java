package com.excellence.iamp.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.ResourceWorkItem;

/**
 * dao
 * @author huangjinyuan
 *
 */
@Repository
public interface ResourceWorkItemDao {
	/**
	 * 通过id查找
	 * @param resWorkItemId
	 * @return
	 */
	public ResourceWorkItem findById(String resWorkItemId);
	
	
	
	/**
	 * 新增
	 * @param app
	 */
	public void insert(ResourceWorkItem resourceWorkItem);
	
	/**
	 * 删除
	 * @param resWorkItemId
	 */
	public void delete(String resWorkItemId);
	/**
	 * 更新
	 * @param resourceWorkItem
	 */
	public void update(ResourceWorkItem resourceWorkItem);
	
	/**
	 * 通过条件查询
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<ResourceWorkItem> getByCondition(Map paramMap);
	
	
	public void createNewTable();
	
	/**
	 * 统计总数
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
	
}
