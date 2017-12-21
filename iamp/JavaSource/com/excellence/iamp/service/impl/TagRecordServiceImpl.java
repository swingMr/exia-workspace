package com.excellence.iamp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.dao.TagRecordDao;
import com.excellence.iamp.service.TagRecordService;
import com.excellence.iamp.vo.TagRecord;

/**
 * 用户行为痕迹数据
 * @author chenghaiqian
 *
 */
@Service
public class TagRecordServiceImpl implements TagRecordService {

	@Autowired
	private TagRecordDao tagRecordDao;

	public TagRecord createTagRecord(TagRecord tagRecord){
		if(StringUtils.isEmpty(tagRecord.getRecordId())){
			tagRecord.setRecordId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		tagRecordDao.insert(tagRecord);
		return tagRecord;
	};
	
	public List<String> getAllTagTypes(){
		return tagRecordDao.getAllTagTypes();
	}
	
	public TagRecord getTagRecord(String recordId){
		return tagRecordDao.get(recordId);
	};
	public void updateTagRecord(TagRecord tagRecord){
		tagRecordDao.update(tagRecord);
	};
	public Boolean deleteTagRecord(String recordId){
		return tagRecordDao.delete(recordId);
	}

	public List<TagRecord> getTagRecordByCondition(Map conditon) {
		return tagRecordDao.getTagRecordByCondition(conditon);
	}

	public List<String> getDistinctSubId(){
		return tagRecordDao.getDistinctSubId();
	}
	
	public List<TagRecord> getTagRecordListBySubIdAndType(String subId, String tagType){
		return tagRecordDao.getTagRecordListBySubIdAndType(subId, tagType);
	}

	public List<TagRecord> getRankRecords(String subjectId) {
		return tagRecordDao.getRankRecords(subjectId);
	}
	
	public List<TagRecord> getRankRecords(String subjectId, int count) {
		return tagRecordDao.getRankRecords(subjectId, count);
	}
	
	public List<TagRecord> getTagRecordLimit() {
		return tagRecordDao.getTagRecordLimit();
	}
	
}
