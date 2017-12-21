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
	public List<UserDomain> getByCondition(Map paramMap);
}