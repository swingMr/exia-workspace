package com.excellence.iamp.mongodb.service;

import java.util.List;


import com.excellence.iamp.mongodb.vo.UserOpLog;
import com.excellence.iamp.util.Page;

public interface UserOpLogService {
	/**
	 * 功能 ： 保存用户行为日志
	 * @param userOpLog   需要保存的日志对象
	 * @return 			      创建后的日志对象
	 * 
	 * **/
	UserOpLog save(UserOpLog userOpLog);
	
	/**
	 * 功能 ： 查找所有的用户行为日志
	 * @param pageNo	当前页码
	 * @param pageSize	当前条数
	 * @return 			所有的日志对象
	 * @author	  		wangjg
	 * **/
	Page<UserOpLog> findAll(int pageNo,int pageSize);
	
	/**
	 * 功能: 根据传入的参数列表和表名进行查询
	 * 参数列表格式:{"title":"","id":"",.....}
	 * @param currentPage		当前页面
	 * @param pageSize			查询条数
	 * @param collectionName	表名
	 * @param params			参数列表
	 * @author wangjg
	 * **/
	Page<UserOpLog> findByParamsAndCollectionName(int currentPage,int pageSize,String params);
}
