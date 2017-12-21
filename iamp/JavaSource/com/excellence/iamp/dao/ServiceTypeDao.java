package com.excellence.iamp.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.excellence.iamp.vo.ServiceType;

/**
 * dao
 * @author huangjinyuan
 *
 */
@Repository
public interface ServiceTypeDao {
	
	List<ServiceType> getList(Map paramMap);
	List<ServiceType> getList();
	/**
	 * 通过id查找
	 * @param typeId
	 * @return
	 */
	ServiceType findById(String typeId);
	/**
	 * 新增
	 * @param serviceType
	 */
	int insert(ServiceType serviceType);
	/**
	 * 删除
	 * @param typeId
	 */
	void delete(String typeId);
	/**
	 * 更新
	 * @param serviceType
	 */
	void update(ServiceType serviceType);
	
	/**
	 * 统计总数
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
}
