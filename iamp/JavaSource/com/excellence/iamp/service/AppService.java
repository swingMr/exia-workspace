package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.App;
import com.github.pagehelper.PageInfo;
/**
 * 应用Service
 * @author huangjinyuan
 *
 */
public interface AppService {
	/**
	 * 创建app
	 * @param app
	 * @return
	 */
	public App createApp(App app);
	
	/**
	 * 通过appId查找
	 * @param appId
	 * @return
	 */
	public App findById(String appId);
	
	/**
	 * 通过appCode查找
	 * @param appCode
	 * @return
	 */
	public App findByAppCode(String appCode);
	
	/**
	 * 更新app
	 * @param app
	 * @return
	 */
	public App updateApp(App app);
	
	/**
	 * 删除app
	 * @param app
	 */
	public void deleteApp(App app);
	
	/**
	 * 物理删除app
	 * @param app
	 */
	public void psyDeleteApp(String appId);
	
	/**
	 * 通过app代码和密码获取app
	 * @param appCode
	 * @param appPwd
	 * @return
	 */
	public App getAppByAppCodeAndPwd(String appCode, String appPwd);
	
	
	public List<App> getByCondition(Map paramMap);
	/**
	 * 分页
	 * @param paramMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<App> page(Map paramMap, int pageNo, int pageSize);
}
