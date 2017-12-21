package com.excellence.iamp.mongodb.service.Impl;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.excellence.iamp.mongodb.dao.FileInfoDao;
import com.excellence.iamp.mongodb.dao.ResourceDao;
import com.excellence.iamp.mongodb.service.ResourceService;
import com.excellence.iamp.mongodb.vo.FileInfo;
import com.excellence.iamp.mongodb.vo.Resource;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.enums.EStatus;
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService{
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private FileInfoDao fileInfoDao;
	
	public List<Resource> getAll(String collectionName){
		Query query = new Query();
		return resourceDao.find(query, collectionName);
	}
	
	public long getCount(String collectionName){
		Query query = new Query();
		return resourceDao.count(query, collectionName);
	}
	
	public Resource findByUrlAndCollectionName(String url ,String collectionName){
		return resourceDao.findByUrlAndCollectionName(url, collectionName);
	}
	
	public Page<Resource> getList(int currentPage, int pageSize,String collectionName,String catalogNum,String searchTitle){
		return resourceDao.getList(currentPage, pageSize,collectionName,catalogNum,searchTitle);
	}
	
	public Page<Resource> findByParamsAndCollectionName(int currentPage, int pageSize,String collectionName,String params){
		return resourceDao.findByParamsAndCollectionName(currentPage, pageSize,collectionName,params);
	}
	
	public Resource findByIdAndCollectionName(String id,String collectionName){
		return resourceDao.findByIdAndCollectionName(id, collectionName);
	}
	
	public void update(Resource resource,String collectionName){
		resourceDao.update(resource,collectionName);
	}
	
	public Resource save(Resource resource,String collectionName){
		return resourceDao.save(resource, collectionName);
	}

	public Page<Resource> getListByCatalogId(int currentPage, int pageSize, String collectionName, String catalogId,JSONArray param,Map<String,String> condition) {
		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(EStatus.normal.getIndex()));
		if(StringUtils.isNotBlank(catalogId)){
			query.addCriteria(Criteria.where("catalogIds").all(catalogId));
		}
		String orderByDate = "0";
		if(condition.containsKey("order")){
			orderByDate = condition.get("order");	
		}
		String orderByTitle = condition.get("orderByTitle");
		if(orderByDate.equals("0")){
			query.with(new Sort(Direction.DESC,"publishDate"));	
		}
		if(condition.containsKey("title")) {
			query.addCriteria(Criteria.where("title").is(condition.get("title")));
		}
		if(condition.containsKey("searchAttr")) {
			String searchAttr = condition.get("searchAttr");
			JSONArray arr = new JSONArray(searchAttr);
			for(int i=0; arr.length()>i; i++){
				JSONObject obj = (JSONObject) arr.get(i);
				if(obj.has("attrName")){
					String attrName = (String) obj.get("attrName");
					String value = (String) obj.get("value");
					Criteria c = Criteria.where("extendAttrs").elemMatch(Criteria.where("attrName").is(attrName).and("value").is(value));
					query.addCriteria(c);	
				}else{
					//基本类型
					String basic = (String) obj.get("basic");
					String value = (String) obj.get("value");
					if(basic.equals("publishDate")){
						//日期格式
						String formatTime ="";
						SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
						try {
							Date publishDate = sdf.parse(value);
							Calendar calendar =new GregorianCalendar();
							calendar.setTime((Date)publishDate); //你自己的数据进行类型转换
							calendar.add(calendar.DATE,1);
							 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							 formatTime  = dateFormat.format(calendar.getTime());
						query.addCriteria(Criteria.where(basic).gte(publishDate).lt(calendar.getTime()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(basic.equals("belongDomain")){
						query.addCriteria(Criteria.where(basic).regex(value));
					}else{
						query.addCriteria(Criteria.where(basic).is(value));	
					}
				}
			};
		}
		return resourceDao.findPage(query,collectionName ,currentPage, pageSize);
	}
	
	

	public FileInfo SaveFile(File file, int type, String title, String fileExt) {
		return fileInfoDao.SaveFile(file, type, title, fileExt);
	}
	
	public String saveFileToMongo(MultipartFile file, String title) {
		return fileInfoDao.saveFileToMongo(file, title);
	}

	public InputStream getInputStreamById(String id) {
		return fileInfoDao.getInputStreamById(id);
	}

	public void deleteFile(String id) {
		fileInfoDao.deleteFile(id);
	}
	
	public  void del(String id,String collectionName){
		Query query = new Query(Criteria.where("_id").is(id));
		resourceDao.remove(query, collectionName);
	}

	@SuppressWarnings("static-access")
	public Page<Resource> getListByCatalogNames(int currentPage, int pageSize, String collectionName,
			List<String> catalogNames,Map<String ,String> condition) {
		Criteria criteria = new Criteria();
		Query query = new Query(criteria.where("status").is(EStatus.normal.getIndex()).and("catalogNames").all(catalogNames));
		if(condition != null) {
			for(String key:condition.keySet()) {
				if(key.contains("extendAttrs")){
					//特殊字段  json数组
					if(StringUtils.isNotBlank(condition.get(key))){
						String theKey = key.replaceAll("extendAttrs.", "");
						Criteria c = Criteria.where("extendAttrs").elemMatch(Criteria.where("attrName").is(theKey).and("value").is(condition.get(key)));
						query.addCriteria(c);	
					}
				}else{
					//基础字段
					criteria.and(key).is(condition.get(key));
				}
			}
		}
		return resourceDao.findPage(query,collectionName ,currentPage, pageSize);
	}
	
	public List<Resource> resourcesByConditions(String catalogId,String text,String type,Map<String ,String> params){
		return null;
	}
}
