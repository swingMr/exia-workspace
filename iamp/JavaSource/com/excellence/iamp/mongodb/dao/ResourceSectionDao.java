package com.excellence.iamp.mongodb.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.activation.CommandMap;

import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.excellence.iamp.mongodb.vo.ResourceSection;
import com.excellence.iamp.util.Page;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;

@Repository
public  class ResourceSectionDao extends BaseMongoDaoImpl<ResourceSection>{
	public List<String> getResourceIdsByCreatorId(int currentPage, int pageSize,String creatorId){
			String mapFunction = "function(){"
	               + "emit(this.resourceSign,this.resTile);" +"}";
	        String reduceFunction = "function(key, values)" +"{"
	        		+ "return Array.sum(values);" +"}";
	        DBCollection sec = mongoTemplate.getCollection("ia_resource_section");
	        DBObject query = new BasicDBObject().append("creatorId", creatorId);
	        MapReduceCommand  cmd =new MapReduceCommand(sec,mapFunction,reduceFunction,null, MapReduceCommand.OutputType.INLINE, query);
	        MapReduceOutput out = sec.mapReduce(cmd);
	        List<DBObject>  list = (List<DBObject>) out.results();
	        List<String> ids = new ArrayList<String>();
	        for(int i=0; list.size()>i; i++){
	        	 JSONObject objs = new JSONObject(list.get(i).toString());
	        	 String resourceId = (String) objs.get("_id");
	 	         ids.add(resourceId);
	        }
			return ids;
		}
}
