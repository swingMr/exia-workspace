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
	 * 创建
	 * @param serviceType
	 */
	public Integer insert(AppMember iaAppMember);
	/**
	 * 更新
	 * @param serviceType
	 */
	public void update(AppMember iaAppMember);
	/**
	 * 删除
	 * @param iaAppMemberId
	 */
	public Integer delete(String iaAppMemberId);
	
	/**
	 * 根据应用Id和会员id删除
	 * @param iaAppMemberId
	 */
	public Integer deleteByDoubleId(@Param("appId")String appId,@Param("memberId")String memberId);
	
	/**
	 * 通过条件统计总数用于分页
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int countByCondition(Map paramMap);
	
	/**
	 * 通过条件查询
	 * @param paramMap
	 * @return
	 */
	public List<AppMember> getByCondition(Map paramMap);
	
	public void createNewTable();
}