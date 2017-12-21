package com.excellence.iamp.mongodb.dao;


import java.util.Iterator;

import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.excellence.iamp.mongodb.vo.UserOpLog;
import com.excellence.iamp.util.Page;
@Repository
public class UserOpLogDao extends BaseMongoDaoImpl<UserOpLog>{
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	public Page<UserOpLog> findByParamsAndCollectionName(int currentPage, int pageSize,String collectionName,String params){
		JSONObject obj = new JSONObject(params);
		Iterator it = obj.keys();
		Criteria criatira = new Criteria();
		criatira.where("1=1");
		while (it.hasNext()) {
			String key = (String) it.next();
			String value = obj.getString(key);
			criatira.and(key).is(value);
		}
		Query query = new Query(criatira);
		query.with(new Sort(new Order(Direction.DESC, "opDate")));
		return this.findPage(query,collectionName ,currentPage, pageSize);
	}
	
}
