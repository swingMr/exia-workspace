package com.excellence.iamp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.FileExtConfig;

/**
 * �ļ�ת������dao
 * @author huangjinyuan
 *
 */
@Repository
public interface FileExtConfigDao {
	/**
	 * ͨ��id����
	 * @param configId
	 * @return
	 */
	public FileExtConfig findById(String configId);
	/**
	 * ����
	 * @param fileExtConfig
	 */
	public void insert(FileExtConfig fileExtConfig);
	/**
	 * ����
	 * @param fileExtConfig
	 */
	public void update(FileExtConfig fileExtConfig);
	/**
	 * ɾ��
	 * @param configId
	 */
	public void delete(String configId);
	
	/**
	 * ����
	 */
	public void createNewTable();
	
	/**
	 * ��ѯ�б�
	 * @return
	 */
	public List<FileExtConfig> getList();
}
