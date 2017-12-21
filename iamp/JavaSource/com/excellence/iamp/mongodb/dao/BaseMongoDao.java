package com.excellence.iamp.mongodb.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.excellence.iamp.util.Page;
import com.mongodb.WriteResult;

public interface BaseMongoDao<T> {
	/**   
     * ����   
     */    
    public T save(T entity);    
    
    /**   
     * ����ID��ѯ   
     */    
    public T findById(String id);    
    
    /**   
     * ͨ��ID��ȡ��¼,����ָ���˼�����(�����˼)   
     */    
    public T findById(String id, String collectionName);    
    
    /**   
     * ������и����ͼ�¼   
     */    
    public List<T> findAll();    
    
    /**   
     * ������и����ͼ�¼,����ָ���˼�����(�����˼)   
     */    
    public List<T> findAll(String collectionName);    
    
    /**   
     * ����������ѯ   
     */    
    public List<T> find(Query query);
    
    /**   
     * ����������ѯ   
     */
    public List<T> find(Query query,String collectionName); 
    
    /**   
     * ����������ѯһ��   
     */    
    public T findOne(Query query);
    
    /**   
     * ����������ѯһ��   
     */    
    public T findOne(Query query,String collectionName);  
    
    /**   
     * ��ҳ��ѯ   
     */    
    public Page<T> findPage(Query query,int pageNo, int pageSize);
    
    /**   
     * ��ҳ��ѯ   
     */    
    public Page<T> findPage(Query query,String collectionName,int pageNo, int pageSize);  
    
    /**   
     * �������� �������   
     */    
    public long count(Query query,String collectionName);
    
    /**   
     * �������� �������   
     */    
    public long count(Query query);   
    
    /**   
     * �������� ����   
     */    
    public WriteResult update(Query query, Update update);  
    
    /**   
     * �������� ����   
     */    
    public WriteResult update(Query query, Update update,String collectionName);
    
    /**   
     * ���·���������sort֮��ĵ�һ���ĵ� �����ظ��º���ĵ�   
     */    
    public T updateOne(Query query, Update update);
    
    /**   
     * ���·���������sort֮��ĵ�һ���ĵ� �����ظ��º���ĵ�   
     */    
    public T updateOne(Query query, Update update,String collectionName);
    
    /**   
     * ���ݴ���ʵ��ID����   
     */    
    public WriteResult update(T entity);  
    
    /**   
     * ���ݴ���ʵ��ID����   
     */    
    public WriteResult update(T entity,String collectionName);
    
    /**   
     * �������� ɾ��   
     *    
     * @param query   
     */    
    public void remove(Query query);
    
    /**   
     * �������� ɾ��   
     *    
     * @param query   
     */    
    public void remove(Query query,String collectionName);
}
