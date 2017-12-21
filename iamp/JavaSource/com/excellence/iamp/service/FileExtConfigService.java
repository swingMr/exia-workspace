package com.excellence.iamp.service;

import com.excellence.iamp.vo.FileExtConfig;

public interface FileExtConfigService {
	
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
	public void create(FileExtConfig fileExtConfig);
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
	 * 
	 * @return
	 */
	public FileExtConfig getFileExtConfig();
	
	
}
