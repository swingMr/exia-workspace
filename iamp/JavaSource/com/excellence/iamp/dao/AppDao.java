package com.excellence.iamp.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.App;

/**
 * dao
 * @author huangjinyuan
 *
 */
@Repository
public interface AppDao {
	/**
	 * ͨ��id����
	 * @param appId
	 * @return
	 */
	public App findById(String appId);
	
	/**
	 * ͨ��appCode����
	 * @param appCode
	 * @return
	 */
	public List<App> findByAppCode(String appCode);
	
	/**
	 * ͨ��������������
	 * @param appCode
	 * @param appPwd
	 * @return
	 */
	public List<App> findByCodeAndPwd(Map paramMap);
	
	/**
	 * ����
	 * @param app
	 */
	public void insert(App app);
	/**
	 * ɾ��
	 * @param appId
	 */
	public void delete(String appId);
	/**
	 * ����
	 * @param serviceType
	 */
	public void update(App app);
	
	/**
	 * ͨ��������ѯ
	 * @param paramMap
	 * @return
	 */
	public List<App> getByCondition(Map paramMap);
	
	public void createNewTable();
	
	/**
	 * ͳ������
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
	
}
