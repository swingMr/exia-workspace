package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.AppMember;
import com.github.pagehelper.PageInfo;

/**
 * app会员管理service
 * @author wangjg
 *
 */
public interface AppMemberService {
	
	public Integer create(AppMember iaAppMember);
	
	public void update(AppMember iaAppMember);
	
	public void delete(String id);
	
	public Integer deleteByDoubleId(String appId,String memberId);
	
	@SuppressWarnings("rawtypes")
	public List<AppMember> getByCondition(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public PageInfo<AppMember> page(Map paramMap, int pageNo, int pageSize);
	
	
}
