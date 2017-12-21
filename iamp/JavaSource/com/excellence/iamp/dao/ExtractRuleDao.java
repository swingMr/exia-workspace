package com.excellence.iamp.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.ExtractRule;

/**
 * dao
 * @author liup
 *
 */
@Repository
public interface ExtractRuleDao {
	/**
	 * 通过id查找
	 * @param appId
	 * @return
	 */
	public ExtractRule findById(String ruleId);
	
	/**
	 * 通过体裁和文件类型查找
	 * @param appCode
	 * @param appPwd
	 * @return
	 */
	public List<ExtractRule> findByGenreAndFiletype(Map paramMap);
	
	/**
	 * 新增
	 * @param app
	 */
	public void insert(ExtractRule extractRule);
	/**
	 * 删除
	 * @param appId
	 */
	public void delete(String ruleId);
	/**
	 * 更新
	 * @param serviceType
	 */
	public void update(ExtractRule extractRule);
	
	/**
	 * 通过条件查询
	 * @param paramMap
	 * @return
	 */
	public List<ExtractRule> getByCondition(Map paramMap);
	
	public void createNewTable();
	
}
