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
	 * 新增
	 * @param robotDef
	 */
	public void insert(RobotDef robotDef);
	/**
	 * 删除
	 * @param robotId
	 */
	public void delete(String robotId);
	/**
	 * 更新
	 * @param robotDef
	 */
	public void update(RobotDef robotDef);
	
	public RobotDef findById(String robotId);
	
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
	public List<RobotDef> getByCondition(Map paramMap);
}
