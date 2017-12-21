package com.excellence.iamp.service;

import com.excellence.iamp.vo.FileExtConfig;

public interface FileExtConfigService {
	
	/**
	 * 通过id查找
	 * @param configId
	 * @return
	 */
	public FileExtConfig findById(String configId);
	/**
	 * 插入
	 * @param fileExtConfig
	 */
	public void create(FileExtConfig fileExtConfig);
	/**
	 * 更新
	 * @param fileExtConfig
	 */
	public void update(FileExtConfig fileExtConfig);
	/**
	 * 删除
	 * @param configId
	 */
	public void delete(String configId);
	
	/**
	 * 建表
	 */
	public void createNewTable();
	
	/**
	 * 
	 * @return
	 */
	public FileExtConfig getFileExtConfig();
	
	
}
