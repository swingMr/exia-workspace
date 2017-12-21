package com.excellence.iamp.mongodb.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.excellence.iamp.mongodb.vo.Resource;
import com.excellence.iamp.mongodb.vo.ResourceCatalog;
import com.excellence.iamp.vo.enums.EStatus;
@Repository
public class ResourceCatalogDao extends BaseMongoDaoImpl<ResourceCatalog>{
		
	public List<ResourceCatalog> getCatalogsByParentNumAndLibNum(String parentNum, String libNum) {
		Query query = new Query(Criteria.where("parentNum").is(parentNum).and("libNum").is(libNum).and("status").is(EStatus.normal.getIndex()));
		query.with(new Sort(new Order(Direction.ASC, "displayOrder")));
		return this.find(query);
	}
	
	public List<ResourceCatalog> findByCatelogNameAndCollectionName(String catelogName,String libNum,String collectionName){
		Query query = new Query(Criteria.where("status").is(EStatus.normal.getIndex()).and("catelogName").is(catelogName).and("libNum").is(libNum));
		return this.find(query, collectionName);
	}
}
