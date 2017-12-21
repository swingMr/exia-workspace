package com.excellence.iamp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.dao.UserDomainDao;
import com.excellence.iamp.service.UserDomainService;
import com.excellence.iamp.vo.UserDomain;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserDomainServiceImpl implements UserDomainService {
	
	@Autowired
	private UserDomainDao userDomainDao;
	
	/**
	 * 创建
	 */
	public Integer create(UserDomain userDomain) {
		if(StringUtils.isEmpty(userDomain.getUserDomainId())) {
			userDomain.setUserDomainId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		Integer num = userDomainDao.insert(userDomain);
		return num;
	}
	
	/**
	 * 通过id查找
	 */
	public UserDomain findById(String userDomainId) {
		return userDomainDao.findById(userDomainId);
	}
	
	/**
	 * 更新
	 */
	public void update(UserDomain userDomain) {
		userDomainDao.update(userDomain);
	}
	
	/**
	 * 删除
	 */
	public Integer delete(String userDomainId) {
		Integer num = userDomainDao.delete(userDomainId);
		return num;
	}
	
	/**
	 * 根据roleId删除
	 */
	public Integer deleteByRoleId(String roleId) {
		Integer num = userDomainDao.deleteByRoleId(roleId);
		return num;
	}
	
	/**
	 * 通过条件查询
	 */
	@SuppressWarnings("rawtypes")
	public List<UserDomain> getByCondition(Map paramMap) {
		return userDomainDao.getByCondition(paramMap);
	}
	
	/**
	 * 分页
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo<UserDomain> page(Map paramMap, int pageNo, int pageSize) {
		int count = userDomainDao.countByCondition(paramMap);
		PageHelper.startPage(pageNo, pageSize,false);
		List<UserDomain> list = userDomainDao.getByCondition(paramMap);
		PageInfo<UserDomain> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}
	

}
