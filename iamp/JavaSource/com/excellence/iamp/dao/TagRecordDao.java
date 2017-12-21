package com.excellence.iamp.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.common.util.Page;
import com.excellence.iamp.vo.TagRecord;

/**
 * ְ��:�־û��û���Ϊ�ۼ�����
 * �־û���:ia_Tag_Record
 * @author haiqian
 *
 */
@Repository
public interface TagRecordDao{
	
	/**
	 * ����
	 * @param tagRecord
	 * @return
	 * @author haiqian
	 */
	public void insert(TagRecord tagRecord);
	
	/**
	 * ����
	 * @param tagRecordVo
	 * @return
	 * @author haiqian
	 */
	public void update(TagRecord tagRecordVo);
	
	public boolean delete(String id);
	
	public TagRecord get(String id);

	public List<TagRecord> getTagRecordByCondition(Map condition);

	public Page page(Map map,int pageNo, int pageSize);
	
	public List<String>  getDistinctSubId();
	
	public List<TagRecord> getTagRecordListBySubIdAndType(String subjectId,String tagType);
	
	public List<TagRecord> getRankRecords(String subjectId);
	
	public List<TagRecord> getRankRecords(String subjectId, int count);
	
	public List<String> getAllTagTypes();
	
	public List<TagRecord> getTagRecordLimit();
	
}