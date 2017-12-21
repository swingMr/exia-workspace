package com.excellence.iamp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.dao.AppMemberDao;
import com.excellence.iamp.service.AppMemberService;
import com.excellence.iamp.vo.AppMember;
import com.excellence.iamp.vo.Member;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AppMemberServiceImpl implements AppMemberService {
	
	@Autowired
	private AppMemberDao iaAppMemberDao;
	
	/**
	 * ����
	 */
	public Integer create(AppMember iaAppMember) {
		if(StringUtils.isEmpty(iaAppMember.getId())) {
			iaAppMember.setId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		iaAppMemberDao.insert(iaAppMember);
		return 0;
	}
	
	/**
	 * ����
	 */
	public void update(AppMember iaAppMember) {
		iaAppMemberDao.update(iaAppMember);
	}
	
	/**
	 * ɾ��
	 */
	public void delete(String id) {
		iaAppMemberDao.delete(id);
	}
	
	/**
	 * ����Ӧ��id�ͻ�Աidɾ��
	 */
	public Integer deleteByDoubleId(String appId,String memberId) {
		Integer num = iaAppMemberDao.deleteByDoubleId(appId, memberId);
		return num;
	}
	
	/**
	 * ͨ��������ѯ
	 */
	@SuppressWarnings("rawtypes")
	public List<AppMember> getByCondition(Map paramMap) {
		return iaAppMemberDao.getByCondition(paramMap);
	}
	
	/**
	 * ��ҳ
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo<AppMember> page(Map paramMap, int pageNo, int pageSize) {
		int count = iaAppMemberDao.countByCondition(paramMap);
		PageHelper.startPage(pageNo, pageSize,false);
		List<AppMember> list = iaAppMemberDao.getByCondition(paramMap);
		PageInfo<AppMember> pageInfo = new PageInfo(list);
		int pageSizes = Page.getPageCount(count, pageSize);
		pageInfo.setPages(pageSizes);
		pageInfo.setTotal(count);
		return pageInfo;
	}

}
