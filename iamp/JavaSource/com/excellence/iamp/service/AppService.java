package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.App;
import com.github.pagehelper.PageInfo;
/**
 * Ӧ��Service
 * @author huangjinyuan
 *
 */
public interface AppService {
	/**
	 * ����app
	 * @param app
	 * @return
	 */
	public App createApp(App app);
	
	/**
	 * ͨ��appId����
	 * @param appId
	 * @return
	 */
	public App findById(String appId);
	
	/**
	 * ͨ��appCode����
	 * @param appCode
	 * @return
	 */
	public App findByAppCode(String appCode);
	
	/**
	 * ����app
	 * @param app
	 * @return
	 */
	public App updateApp(App app);
	
	/**
	 * ɾ��app
	 * @param app
	 */
	public void deleteApp(App app);
	
	/**
	 * ����ɾ��app
	 * @param app
	 */
	public void psyDeleteApp(String appId);
	
	/**
	 * ͨ��app����������ȡapp
	 * @param appCode
	 * @param appPwd
	 * @return
	 */
	public App getAppByAppCodeAndPwd(String appCode, String appPwd);
	
	
	public List<App> getByCondition(Map paramMap);
	/**
	 * ��ҳ
	 * @param paramMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<App> page(Map paramMap, int pageNo, int pageSize);
}
