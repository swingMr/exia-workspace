package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.IFile;

/**
 * �ļ�dao
 * @author huangjinyuan
 *
 */
@Repository
public interface IFileDao {
	
	/**
	 * ͨ����������
	 * @return
	 */
	public IFile findById(String fileId);
	
	/**
	 * ����
	 * @param ifile
	 */
	public void insert(IFile ifile);
	
	/**
	 * ɾ��
	 * @param fileId
	 */
	public void delete(String fileId);
	
	/**
	 * ����
	 * @param ifile
	 */
	public void update(IFile ifile);
	
	/**
	 * ����
	 */
	public void createNewTable();
	
	/**
	 * ͨ��������ѯ
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<IFile> getByCondition(Map paramMap);
	
}
