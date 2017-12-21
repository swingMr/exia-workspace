package com.excellence.iamp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.dao.AppDao;
import com.excellence.iamp.service.AppService;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.App;
import com.excellence.iamp.vo.enums.EStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * AppService
 * @author huangjinyuan
 *
 */
@Service
public class AppServiceImpl implements AppService {
	
	@Autowired
	private AppDao appDao;
	
	public App createApp(App app) {
		if(StringUtils.isNotEmpty(app.getAppCode())) {
			App existApp = findByAppCode(app.getAppCode());
			if(existApp != null) throw new RuntimeException("应用代码不能重复");
		}
		if(StringUtils.isEmpty(app.getAppId())) {
			app.setAppId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		appDao.insert(app);
		return app;
	}

	public App updateApp(App app) {
		appDao.update(app);
		return app;
	}

	public void deleteApp(App app) {
		app.setStatus(EStatus.delete.getIndex());
		updateApp(app);
	}

	public void psyDeleteApp(String appId) {
		appDao.delete(appId);
		
	}

	public App getAppByAppCodeAndPwd(String appCode, String appPwd) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("appCode", appCode);
		paramMap.put("appPwd", appPwd);
		List<App> list = appDao.getByCondition(paramMap);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo<App> page(Map paramMap, int pageNo, int pageSize) {
		int count = appDao.countByCondition(paramMap);
		PageHelper.startPage(pageNo, pageSize,false);
		List<App> list = appDao.getByCondition(paramMap);
		PageInfo<App> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}

	public App findById(String appId) {
		return appDao.findById(appId);
	}
	
	public App findByAppCode(String appCode) {
		List<App> list = appDao.findByAppCode(appCode);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public List<App> getByCondition(Map paramMap) {
		return appDao.getByCondition(paramMap);
	}
	

}
