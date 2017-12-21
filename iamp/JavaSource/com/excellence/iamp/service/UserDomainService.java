package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.UserDomain;
import com.github.pagehelper.PageInfo;

/**
 * 会员管理service
 * @author wangjg
 *
 */
public interface UserDomainService {
	
	public Integer create(UserDomain UserDomain);
	
	public UserDomain findById(String userDomainId);
	
	public void update(UserDomain UserDomain);
	
	public Integer delete(String userDomainId);
	
	public Integer deleteByRoleId(String roleId);
	
	@SuppressWarnings("rawtypes")
	public List<UserDomain> getByCondition(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public PageInfo<UserDomain> page(Map paramMap, int pageNo, int pageSize);
	
	
}
