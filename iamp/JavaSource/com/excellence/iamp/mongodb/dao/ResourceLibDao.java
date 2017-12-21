package com.excellence.iamp.mongodb.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.excellence.iamp.mongodb.vo.ResourceLib;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.enums.EStatus;

@Repository
public class ResourceLibDao  extends BaseMongoDaoImpl<ResourceLib>{
	
	/**
	 * 分页
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<ResourceLib> getList(int currentPage, int pageSize){
		Query query = new Query(Criteria.where("status").is(EStatus.normal.getIndex()));
		return  this.findPage(query, currentPage, pageSize);
	}


	/**
	 * 查出所有数据（无分页）
	 * @return
	 */
	public List<ResourceLib> getAll(){
		//去除外部接入库
		Query query = new Query(Criteria.where("status").is(EStatus.normal.getIndex()).and("type").nin(3));
		return this.find(query);
	}
	
	/**
	 * 通过资源库编号查找资源库
	 * @param libNum
	 * @return
	 */
	public ResourceLib getByLibNum(String libNum) {
		Query query = new Query(Criteria.where("libNum").is(libNum).and("status").is(EStatus.normal.getIndex()));
		List<ResourceLib> list = find(query);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 通过libName查找资源库
	 * @param libName
	 * @return
	 */
	public ResourceLib getByLibName(String libName) {
		Query query = new Query(Criteria.where("libName").is(libName).and("status").is(EStatus.normal.getIndex()));
		List<ResourceLib> list = find(query);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	
	/**
	 * 新增对象
	 * @author Liuzehang
	 * @param resourceLib
	 * @return
	 */
	public ResourceLib saveResourceLib(ResourceLib resourceLib){
		return this.save(resourceLib);
	}
	
	/**更新对象
	 * @author liuzehang
	 * @param resourceLib
	 */
	public void updateResourceLib(ResourceLib resourceLib){
		this.update(resourceLib);
	}
}
