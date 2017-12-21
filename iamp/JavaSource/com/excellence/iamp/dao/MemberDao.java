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
	 * ͨ��id����
	 * @param memberId
	 * @return
	 */
	public Member findById(String memberId);
	
	/**
	 * ����
	 * @param iaMember
	 */
	public Integer insert(Member iaMember);
	
	/**
	 * ��������
	 * @param app
	 */
	public void batchInsert(List<Member> list);
	/**
	 * ɾ��
	 * @param memberId
	 */
	public Integer delete(String memberId);
	/**
	 * ����
	 * @param serviceType
	 */
	public void update(Member iaMember);
	
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
	public List<Member> getByCondition(Map paramMap);
	
	public void createNewTable();
	
	/**
	 * ��ҳȥ��
	 * @param paramMap
	 * @return
	 */
	public List<Member> getListExcludeAppId(Map paramMap);
	
	public int countExcludeAppId(Map paramMap);
	
}