package com.excellence.iamp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.dao.MemberDao;
import com.excellence.iamp.service.MemberService;
import com.excellence.iamp.vo.Member;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao iaMemberDao;
	
	/**
	 * ����
	 */
	public Integer create(Member iaMember) {
		if(StringUtils.isEmpty(iaMember.getMemberId())) {
			iaMember.setMemberId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		Integer num = iaMemberDao.insert(iaMember);
		return num;
	}
	
	/**
	 * ��������
	 */
	public void batchInsert(List<Member> list) {
		iaMemberDao.batchInsert(list);
	}
	
	/**
	 * ͨ��id����
	 */
	public Member findById(String memberId) {
		return iaMemberDao.findById(memberId);
	}
	
	/**
	 * ����
	 */
	public void update(Member iaMember) {
		iaMemberDao.update(iaMember);
	}
	
	/**
	 * ɾ��
	 */
	public Integer delete(String memberId) {
		Integer num = iaMemberDao.delete(memberId);
		return num;
	}
	
	/**
	 * ͨ��������ѯ
	 */
	@SuppressWarnings("rawtypes")
	public List<Member> getByCondition(Map paramMap) {
		return iaMemberDao.getByCondition(paramMap);
	}
	
	/**
	 * ��ҳ
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo<Member> page(Map paramMap, int pageNo, int pageSize) {
		int count = iaMemberDao.countByCondition(paramMap);
		String order = (String) paramMap.get("orderBy")+" ";
		order += paramMap.get("orderDirection");
		PageHelper.startPage(pageNo, pageSize, order);
		List<Member> list = iaMemberDao.getByCondition(paramMap);
		PageInfo<Member> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}
	
	/**
	 * �����ҳ
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo<Member> pageExcludeAppId(Map paramMap, int pageNo, int pageSize) {
		int count = iaMemberDao.countExcludeAppId(paramMap);
		PageHelper.startPage(pageNo, pageSize,false);
		List<Member> list = iaMemberDao.getListExcludeAppId(paramMap);
		PageInfo<Member> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}

}
