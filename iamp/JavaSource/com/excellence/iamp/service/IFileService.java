package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.IFile;

public interface IFileService {
	
	/**
	 * 通过主键查找
	 * @return
	 */
	public IFile findById(String fileId);
	
	/**
	 * 插入
	 * @param ifile
	 */
	public void create(IFile ifile);
	
	/**
	 * 删除
	 * @param fileId
	 */
	public void delete(String fileId);
	
	/**
	 * 更新
	 * @param ifile
	 */
	public void update(IFile ifile);
	
	/**
	 * 创建
	 */
	public void createNewTable();
	
	/**
	 * 通过条件查询
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<IFile> getByCondition(Map paramMap);
	
}
