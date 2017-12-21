package com.excellence.iamp.mongodb.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.excellence.iamp.mongodb.dao.ResourceLibDao;
import com.excellence.iamp.mongodb.service.ResourceLibService;
import com.excellence.iamp.mongodb.vo.ResourceLib;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.enums.EStatus;

/**
 * ��Դ��service
 * @author huangjinyuan
 *
 */
@Service("resourceLibService")
public class ResourceLibServiceImpl implements ResourceLibService{
	@Autowired
	private ResourceLibDao resourceLibDao;
	
	public Page<ResourceLib> getList(int currentPage, int pageSize){
		return resourceLibDao.getList(currentPage, pageSize);
	}
	
	//ͨ��ID���Ҷ���
	public ResourceLib findOne(String id){
		return resourceLibDao.findById(id);
	}

	public ResourceLib getByLibNum(String libNum) {
		return resourceLibDao.getByLibNum(libNum);
	}
	
	//ɾ������
	public void del(String id){
		Query query = new Query(Criteria.where("_id").is(id));
		resourceLibDao.remove(query);
	}
	
	//�������
	public ResourceLib save(ResourceLib resourceLib){
		return  resourceLibDao.saveResourceLib(resourceLib);
	}
	
	//���¶���
	public void update(ResourceLib resourceLib){
		resourceLibDao.update(resourceLib);
	}
	
	//����Ҫ��ҳȫ����ѯ
	public List<ResourceLib> getAll(){
		return resourceLibDao.getAll();
	}

	public Page<ResourceLib> getList(Map paramMap, int currentPage, int pageSize) {
		if(paramMap.containsKey("type")) {
			Query query = new Query(Criteria.where("type").is(paramMap.get("type")).and("status").is(EStatus.normal.getIndex()));
			return resourceLibDao.findPage(query, currentPage, pageSize);
		} else {
			return resourceLibDao.getList(currentPage, pageSize);
		}
	}
	
	public ResourceLib getByLibName(String libName) {
		return resourceLibDao.getByLibName(libName);
	}
}
