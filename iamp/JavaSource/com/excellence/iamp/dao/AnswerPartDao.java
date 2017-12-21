package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.excellence.iamp.robot.vo.AnswerPart;

/**
 * dao
 * @author liup
 *
 */
@Repository
public interface AnswerPartDao {
	/**
	 * 新增
	 * @param answerPart
	 */
	public void insert(AnswerPart answerPart);
	/**
	 * 删除
	 * @param partId
	 */
	public void delete(String partId);
	/**
	 * 更新
	 * @param answerPart
	 */
	public void update(AnswerPart answerPart);
	
	public AnswerPart findById(String partId);
	
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
	public List<AnswerPart> getByCondition(Map paramMap);
}
