package com.excellence.iamp.mongodb.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import com.excellence.iamp.mongodb.vo.FileInfo;
import com.excellence.iamp.mongodb.vo.Resource;
import com.excellence.iamp.util.Page;

public interface ResourceService {
	Page<Resource> getList(int currentPage,int pageSize,String collectionName,String catalogNum,String searchTitle);
	
	Page<Resource> getListByCatalogId(int currentPage,int pageSize,String collectionName,String catalogId,JSONArray param,Map<String ,String> condition);
	
	Page<Resource> getListByCatalogNames(int currentPage,int pageSize,String collectionName,List<String> catalogNames,Map<String ,String> condition);
	/**
	 * 功能: 根据传入的参数列表和表名进行查询
	 * 参数列表格式:{"title":"","id":"",.....}
	 * @param currentPage		当前页面
	 * @param pageSize			查询条数
	 * @param collectionName	表名
	 * @param params			参数列表
	 * @author wangjg
	 * **/
	Page<Resource> findByParamsAndCollectionName(int currentPage,int pageSize,String collectionName,String params);

	Resource findByIdAndCollectionName(String id,String collectionName);
	
	Resource findByUrlAndCollectionName(String url ,String collectionName);
	
	void update(Resource resource,String collectionName);
	
	Resource save(Resource resource,String collectionName);
	
	long getCount(String collectionName);
	
	public FileInfo SaveFile(File file, int type, String title, String fileExt);
	
	public String saveFileToMongo(MultipartFile file, String title);
	
	public InputStream getInputStreamById(String id);
	
	public void deleteFile(String id);
	
	public  void del(String id,String collectionName);
	
	public List<Resource> resourcesByConditions(String catalogId,String text,String type,Map<String ,String> params);
}
