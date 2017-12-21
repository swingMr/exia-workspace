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
	 * 通过id查找
	 * @param appId
	 * @return
	 */
	public App findById(String appId);
	
	/**
	 * 通过appCode查找
	 * @param appCode
	 * @return
	 */
	public List<App> findByAppCode(String appCode);
	
	/**
	 * 通过代码和密码查找
	 * @param appCode
	 * @param appPwd
	 * @return
	 */
	public List<App> findByCodeAndPwd(Map paramMap);
	
	/**
	 * 新增
	 * @param app
	 */
	public void insert(App app);
	/**
	 * 删除
	 * @param appId
	 */
	public void delete(String appId);
	/**
	 * 更新
	 * @param serviceType
	 */
	public void update(App app);
	
	/**
	 * 通过条件查询
	 * @param paramMap
	 * @return
	 */
	public List<App> getByCondition(Map paramMap);
	
	public void createNewTable();
	
	/**
	 * 统计总数
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
	
}
