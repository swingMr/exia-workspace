package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.TagRecord;


/**
 * 用户行为痕迹数据
 * @author chenghaiqian
 *
 */
public interface TagRecordService {
	public TagRecord createTagRecord(TagRecord tagRecord);
	
	public TagRecord getTagRecord(String recordId);
	
	public void updateTagRecord(TagRecord tagRecord);
	
	public Boolean deleteTagRecord(String recordId);
	
	public List<TagRecord> getTagRecordByCondition(Map conditon);
	
	public List<String> getDistinctSubId();
	
	public List<String> getAllTagTypes();
	
	public List<TagRecord> getTagRecordListBySubIdAndType(String subId, String tagType);
	
	public List<TagRecord> getRankRecords(String subjectId);
	
	public List<TagRecord> getRankRecords(String subjectId, int count);
	
	public List<TagRecord> getTagRecordLimit();
	
}
