package com.excellence.iamp.mongodb.service;

import java.util.List;


import com.excellence.iamp.mongodb.vo.UserOpLog;
import com.excellence.iamp.util.Page;

public interface UserOpLogService {
	/**
	 * ���� �� �����û���Ϊ��־
	 * @param userOpLog   ��Ҫ�������־����
	 * @return 			      ���������־����
	 * 
	 * **/
	UserOpLog save(UserOpLog userOpLog);
	
	/**
	 * ���� �� �������е��û���Ϊ��־
	 * @param pageNo	��ǰҳ��
	 * @param pageSize	��ǰ����
	 * @return 			���е���־����
	 * @author	  		wangjg
	 * **/
	Page<UserOpLog> findAll(int pageNo,int pageSize);
	
	/**
	 * ����: ���ݴ���Ĳ����б�ͱ������в�ѯ
	 * �����б��ʽ:{"title":"","id":"",.....}
	 * @param currentPage		��ǰҳ��
	 * @param pageSize			��ѯ����
	 * @param collectionName	����
	 * @param params			�����б�
	 * @author wangjg
	 * **/
	Page<UserOpLog> findByParamsAndCollectionName(int currentPage,int pageSize,String params);
}
