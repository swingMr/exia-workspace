package com.excellence.iamp.mongodb.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.mongodb.vo.ResourceLib;
import com.excellence.iamp.util.Page;

public interface ResourceLibService {

	public Page<ResourceLib> getList(int currentPage, int pageSize);
	
	public Page<ResourceLib> getList(Map paramMap,int currentPage, int pageSize);
	
	public ResourceLib findOne(String id);
	
	public ResourceLib getByLibNum(String libNum);
	
	public ResourceLib getByLibName(String libName);
	
	public void del(String id);
	
	public void update(ResourceLib resourceLib);
	
	public ResourceLib save(ResourceLib resourceLib);
	
	public List<ResourceLib> getAll();
}
