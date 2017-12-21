package com.excellence.iamp.mongodb.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.excellence.iamp.mongodb.dao.ResourceCatalogDao;
import com.excellence.iamp.mongodb.service.ResourceCatalogService;
import com.excellence.iamp.mongodb.vo.ResourceCatalog;
import com.excellence.iamp.vo.enums.EStatus;

@Service
public class ResourceCatalogServiceImpl implements ResourceCatalogService {
	
	@Autowired
	private ResourceCatalogDao resourceCatalogDao;
	
	/**
	 * 通过parentNum和libNum查找列表
	 */
	public List<ResourceCatalog> getCatalogsByParentNumAndLibNum(String parentNum, String libNum) {
		return resourceCatalogDao.getCatalogsByParentNumAndLibNum(parentNum,libNum);
	}
	
	/**
	 * 通过id查找
	 */
	public ResourceCatalog findById(String id) {
		return resourceCatalogDao.findById(id);
	}
	
	/**
	 * 创建
	 */
	public ResourceCatalog create(ResourceCatalog resourceCatalog) {
		return resourceCatalogDao.save(resourceCatalog);
	}
	
	/**
	 * 更新
	 */
	public void update(ResourceCatalog resourceCatalog) {
		resourceCatalogDao.update(resourceCatalog);
	}

	public void delete(ResourceCatalog resourceCatalog) {
		resourceCatalog.setStatus(EStatus.delete.getIndex());
		update(resourceCatalog);
	}

	public List<ResourceCatalog> getCatalogsByParentIdAndLibNum(String parentId, String libNum) {
		Query query = new Query(Criteria.where("parentId").is(parentId).and("libNum").is(libNum).and("status").is(EStatus.normal.getIndex()));
		query.with(new Sort(new Order(Direction.ASC, "displayOrder")));
		return resourceCatalogDao.find(query);
	}

	public void getCatalogInfos(String catalogId, List<String> catalogIds, List<String> catalogNames) {
		ResourceCatalog catalog = resourceCatalogDao.findById(catalogId);
		catalogIds.add(catalog.getId());
		catalogNames.add(catalog.getCatelogName());
		while(!catalog.getParentId().equals("root")) {
			catalog = resourceCatalogDao.findById(catalog.getParentId());
			catalogIds.add(catalog.getId());
			catalogNames.add(catalog.getCatelogName());
		}
	}
	
	public List<ResourceCatalog> findByCatelogNameAndCollectionName(String catelogName,String libNum,String collectionName){
		return resourceCatalogDao.findByCatelogNameAndCollectionName(catelogName,libNum, collectionName);
	}
	
}
