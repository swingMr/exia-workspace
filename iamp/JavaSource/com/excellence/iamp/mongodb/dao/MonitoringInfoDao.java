package com.excellence.iamp.mongodb.dao;

import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.excellence.iamp.mongodb.vo.MonitoringInfo;
import com.excellence.iamp.util.Page;
@Repository
public class MonitoringInfoDao  extends BaseMongoDaoImpl<MonitoringInfo>{

	public Page<MonitoringInfo> getList(Map<String, Object> map,int currentPage, int pageSize){
		Query query = new Query();
		if(map.containsKey("type")) {
			query.addCriteria(Criteria.where("monitoredStatus").is(map.get("type")));
		}
		if(map.containsKey("dataBase")){
			query.addCriteria(Criteria.where("libNum").is(map.get("dataBase")));
		}
		if(map.containsKey("searchKeyword")){
			//Ä£ºýÆ¥Åä
			String searchKeyword = (String) map.get("searchKeyword");
			Pattern pattern = Pattern.compile("^.*"+searchKeyword+".*$", Pattern.CASE_INSENSITIVE);
			query.addCriteria(Criteria.where("concepts").regex(pattern));
		}
		query.with(new Sort(Direction.DESC,"intoLibDate"));
		return findPage(query, currentPage, pageSize);
	}
}
