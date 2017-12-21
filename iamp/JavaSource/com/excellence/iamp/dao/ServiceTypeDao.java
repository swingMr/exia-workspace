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
	 * ͨ��id����
	 * @param typeId
	 * @return
	 */
	ServiceType findById(String typeId);
	/**
	 * ����
	 * @param serviceType
	 */
	int insert(ServiceType serviceType);
	/**
	 * ɾ��
	 * @param typeId
	 */
	void delete(String typeId);
	/**
	 * ����
	 * @param serviceType
	 */
	void update(ServiceType serviceType);
	
	/**
	 * ͳ������
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
}
