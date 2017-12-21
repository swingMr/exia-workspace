package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.UserDomain;

public interface UserDomainDao {
    int delete(String userDomainId);
    
    int deleteByRoleId(String roleId);

    int insert(UserDomain userDomain);

    int insertSelective(UserDomain userDomain);

    UserDomain findById(String userDomainId);

    int update(UserDomain userDomain);

    int updateByPrimaryKey(UserDomain userDomain);
    
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
	public List<UserDomain> getByCondition(Map paramMap);
}