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
	 * ͨ��id����
	 * @param resWorkItemId
	 * @return
	 */
	public ResourceWorkItem findById(String resWorkItemId);
	
	
	
	/**
	 * ����
	 * @param app
	 */
	public void insert(ResourceWorkItem resourceWorkItem);
	
	/**
	 * ɾ��
	 * @param resWorkItemId
	 */
	public void delete(String resWorkItemId);
	/**
	 * ����
	 * @param resourceWorkItem
	 */
	public void update(ResourceWorkItem resourceWorkItem);
	
	/**
	 * ͨ��������ѯ
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<ResourceWorkItem> getByCondition(Map paramMap);
	
	
	public void createNewTable();
	
	/**
	 * ͳ������
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
	
}
