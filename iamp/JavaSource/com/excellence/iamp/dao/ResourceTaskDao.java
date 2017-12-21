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
	 * ͨ��id����
	 * @param resWorkItemId
	 * @return
	 */
	public ResourceTask findById(String resTaskId);
	
	
	
	/**
	 * ����
	 * @param app
	 */
	public void insert(ResourceTask resourceTask);
	
	/**
	 * ɾ��
	 * @param resWorkItemId
	 */
	public void delete(String resTaskId);
	/**
	 * ����
	 * @param ResourceTask
	 */
	public void update(ResourceTask resourceTask);
	
	/**
	 * ͨ��������ѯ
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<ResourceTask> getByCondition(Map paramMap);
	
	
	public void createNewTable();
	
	/**
	 * ͳ������
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
	
}
