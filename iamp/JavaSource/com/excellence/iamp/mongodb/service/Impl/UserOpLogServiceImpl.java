package com.excellence.iamp.mongodb.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.excellence.iamp.mongodb.dao.UserOpLogDao;
import com.excellence.iamp.mongodb.service.UserOpLogService;
import com.excellence.iamp.mongodb.vo.UserOpLog;
import com.excellence.iamp.util.Page;
@Service
public class UserOpLogServiceImpl implements UserOpLogService{
	private static final String collectionName = "ia_user_op_log";
	
	@Autowired
	private UserOpLogDao userOpLogDao;
	
	public UserOpLog save(UserOpLog userOpLog){
		userOpLog = userOpLogDao.save(userOpLog, collectionName);
		return userOpLog;
	};
	
	public Page<UserOpLog> findAll(int pageNo,int pageSize){
		Query query = new Query();
		query.with(new Sort(new Order(Direction.DESC, "")));
		Page<UserOpLog> userOpLogList = userOpLogDao.findPage(null, collectionName, pageNo, pageSize);
		return userOpLogList;
	};
	
	public Page<UserOpLog> findByParamsAndCollectionName(int currentPage, int pageSize,String params){
		return userOpLogDao.findByParamsAndCollectionName(currentPage, pageSize,collectionName,params);
	}
}
