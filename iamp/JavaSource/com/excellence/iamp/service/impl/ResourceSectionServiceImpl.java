package com.excellence.iamp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.excellence.iamp.mongodb.dao.ResourceSectionDao;
import com.excellence.iamp.mongodb.vo.ResourceSection;
import com.excellence.iamp.service.ResourceSectionService;
import com.excellence.iamp.util.Page;
import com.mongodb.WriteResult;
@Service
public class ResourceSectionServiceImpl implements ResourceSectionService{
	@Autowired
	private ResourceSectionDao resourceSectionDao;
	
	public List<String>  getResourceIdsByCreatorId (int currentPage,int pageSize,String creatorId){
		return resourceSectionDao.getResourceIdsByCreatorId(currentPage, pageSize,creatorId);
	}
	
	public List<ResourceSection> getListByResourceSign(String id){
		Query query = new Query();
		query.addCriteria(Criteria.where("resourceSign").is(id));
		return resourceSectionDao.find(query);
	};
	
	public List<ResourceSection> getListByResourceSignAndUserId(String id,String creatorId){
		Query query = new Query();
		query.addCriteria(Criteria.where("resourceSign").is(id));
		query.addCriteria(Criteria.where("creatorId").is(creatorId));
		return resourceSectionDao.find(query);
	}
	
	public ResourceSection finOne(String sectionId){
		return resourceSectionDao.findById(sectionId);
	}

	public ResourceSection save(ResourceSection resourceSection){
		return resourceSectionDao.save(resourceSection);
	};
	
	public WriteResult update(ResourceSection resourceSection){
		return resourceSectionDao.update(resourceSection);
	};
	
	public void del(String sectionId){
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(sectionId));
		resourceSectionDao.remove(query);
	};
}
