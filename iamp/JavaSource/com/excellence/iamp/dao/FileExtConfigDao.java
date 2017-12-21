package com.excellence.iamp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.FileExtConfig;

/**
 * 文件转换配置dao
 * @author huangjinyuan
 *
 */
@Repository
public interface FileExtConfigDao {
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
	public void insert(FileExtConfig fileExtConfig);
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
	 * 查询列表
	 * @return
	 */
	public List<FileExtConfig> getList();
}
