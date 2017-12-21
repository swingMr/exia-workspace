package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.Member;
/**
 * IaAppMemberDao
 * @author wangjg
 *
 */
@Repository
public interface MemberDao {
	/**
	 * 通过id查找
	 * @param memberId
	 * @return
	 */
	public Member findById(String memberId);
	
	/**
	 * 新增
	 * @param iaMember
	 */
	public Integer insert(Member iaMember);
	
	/**
	 * 批量插入
	 * @param app
	 */
	public void batchInsert(List<Member> list);
	/**
	 * 删除
	 * @param memberId
	 */
	public Integer delete(String memberId);
	/**
	 * 更新
	 * @param serviceType
	 */
	public void update(Member iaMember);
	
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
	public List<Member> getByCondition(Map paramMap);
	
	public void createNewTable();
	
	/**
	 * 分页去重
	 * @param paramMap
	 * @return
	 */
	public List<Member> getListExcludeAppId(Map paramMap);
	
	public int countExcludeAppId(Map paramMap);
	
}