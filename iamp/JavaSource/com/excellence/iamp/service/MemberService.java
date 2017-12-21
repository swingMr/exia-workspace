package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.Member;
import com.github.pagehelper.PageInfo;

/**
 * 会员管理service
 * @author wangjg
 *
 */
public interface MemberService {
	
	public Integer create(Member iaMember);
	
	public void batchInsert(List<Member> list);
	
	public Member findById(String memberId);
	
	public void update(Member iaMember);
	
	public Integer delete(String memberId);
	
	@SuppressWarnings("rawtypes")
	public List<Member> getByCondition(Map paramMap);
	
	@SuppressWarnings("rawtypes")
	public PageInfo<Member> page(Map paramMap, int pageNo, int pageSize);

	@SuppressWarnings("rawtypes")
	public PageInfo<Member> pageExcludeAppId(Map paramMap, int pageNo, int pageSize);
	
	
}
