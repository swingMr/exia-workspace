package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.robot.vo.RobotDef;


/**
 * dao
 * @author Liuzh
 *
 */
@Repository
public interface RobotDefDao {
	/**
	 * ����
	 * @param robotDef
	 */
	public void insert(RobotDef robotDef);
	/**
	 * ɾ��
	 * @param robotId
	 */
	public void delete(String robotId);
	/**
	 * ����
	 * @param robotDef
	 */
	public void update(RobotDef robotDef);
	
	public RobotDef findById(String robotId);
	
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
	public List<RobotDef> getByCondition(Map paramMap);
}
