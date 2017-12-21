package com.excellence.iamp.mongodb.service;

import java.util.List;

import com.excellence.iamp.mongodb.vo.ResourceCatalog;

public interface ResourceCatalogService {
	
	public List<ResourceCatalog> getCatalogsByParentNumAndLibNum(String parentNum, String libNum);
	
	public List<ResourceCatalog> getCatalogsByParentIdAndLibNum(String parentId, String libNum);
	
	public ResourceCatalog findById(String id);
	
	public ResourceCatalog create(ResourceCatalog resourceCatalog);
	
	public void update(ResourceCatalog resourceCatalog);
	
	public void delete(ResourceCatalog resourceCatalog);
	
	public void getCatalogInfos(String parentId,List<String> catalogIds,List<String> catalogNames);
	
	public List<ResourceCatalog> findByCatelogNameAndCollectionName(String catelogName,String libNum,String collectionName);
	
}
