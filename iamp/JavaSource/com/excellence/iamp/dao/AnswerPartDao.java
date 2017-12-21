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
	 * ����
	 * @param answerPart
	 */
	public void insert(AnswerPart answerPart);
	/**
	 * ɾ��
	 * @param partId
	 */
	public void delete(String partId);
	/**
	 * ����
	 * @param answerPart
	 */
	public void update(AnswerPart answerPart);
	
	public AnswerPart findById(String partId);
	
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
	public List<AnswerPart> getByCondition(Map paramMap);
}
