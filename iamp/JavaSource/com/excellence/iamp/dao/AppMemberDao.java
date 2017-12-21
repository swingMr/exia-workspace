package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.AppMember;
/**
 * IaAppMemberDao
 * @author wangjg
 *
 */
@Repository
public interface AppMemberDao {
	/**
	 * ����
	 * @param serviceType
	 */
	public Integer insert(AppMember iaAppMember);
	/**
	 * ����
	 * @param serviceType
	 */
	public void update(AppMember iaAppMember);
	/**
	 * ɾ��
	 * @param iaAppMemberId
	 */
	public Integer delete(String iaAppMemberId);
	
	/**
	 * ����Ӧ��Id�ͻ�Աidɾ��
	 * @param iaAppMemberId
	 */
	public Integer deleteByDoubleId(@Param("appId")String appId,@Param("memberId")String memberId);
	
	/**
	 * ͨ������ͳ���������ڷ�ҳ
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int countByCondition(Map paramMap);
	
	/**
	 * ͨ��������ѯ
	 * @param paramMap
	 * @return
	 */
	public List<AppMember> getByCondition(Map paramMap);
	
	public void createNewTable();
}