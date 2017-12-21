package com.excellence.iamp.service;

import java.util.List;

import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;

import com.excellence.iamp.mongodb.vo.Resource;
import com.excellence.iamp.mongodb.vo.ResourceSection;
import com.excellence.iamp.util.Page;
import com.mongodb.WriteResult;

public interface ResourceSectionService {

	List<ResourceSection> getListByResourceSign(String id);
	List<ResourceSection> getListByResourceSignAndUserId(String id,String creatorId);
	ResourceSection save(ResourceSection resourceSection);
	WriteResult update(ResourceSection resourceSection);
	void del(String sectionId);
	ResourceSection finOne(String sectionId);
	List<String>  getResourceIdsByCreatorId(int currentPage,int pageSize,String creatorId);
}
