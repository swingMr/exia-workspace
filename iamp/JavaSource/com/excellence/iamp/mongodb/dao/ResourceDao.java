package com.excellence.iamp.mongodb.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.excellence.iamp.mongodb.vo.Resource;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.enums.EStatus;
import com.mongodb.BasicDBObject;

@Repository
public class ResourceDao extends BaseMongoDaoImpl<Resource>{
	
	/**分页查询
	 * @author Liuzh
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<Resource> getList(int currentPage, int pageSize,String collectionName,String catalogNum,String searchTitle){
		Query query = new Query();
		if(StringUtils.isNotBlank(catalogNum)){			
			if(StringUtils.isNotBlank(searchTitle)){
				 query = new Query(Criteria.where("status").is(EStatus.normal.getIndex()).and("catalogNum").is(catalogNum).and("title").is(searchTitle));	
			}else{
				 query = new Query(Criteria.where("status").is(EStatus.normal.getIndex()).and("catalogNum").is(catalogNum));
			}
		}else{
			//定时任务  monitorStatus=0;
			query = new Query(Criteria.where("status").is(EStatus.normal.getIndex()).and("monitoredStatus").is(0));
		}
		return this.findPage(query,collectionName ,currentPage, pageSize);
	}
	
	public Resource findByIdAndCollectionName(String id,String collectionName){
		Query query = new Query(Criteria.where("status").is(EStatus.normal.getIndex()).and("_id").is(id));
		return this.findOne(query, collectionName);
	}
	
	public Resource findByUrlAndCollectionName(String url,String collectionName){
		Query query = new Query(Criteria.where("status").is(EStatus.normal.getIndex()).and("url").is(url));
		List<Resource> list =  this.find(query, collectionName);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	public Page<Resource> findByParamsAndCollectionName(int currentPage, int pageSize,String collectionName,String params){
		JSONObject obj = new JSONObject(params);
		Iterator it = obj.keys();
		Criteria criatira = new Criteria();
		criatira.where("1=1");
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = obj.getString(key);
			criatira.and(key).is(value);
		}
		criatira.and("status").is(EStatus.normal.getIndex());
		Query query = new Query(criatira);
		return this.findPage(query,collectionName ,currentPage, pageSize);
	}
}
