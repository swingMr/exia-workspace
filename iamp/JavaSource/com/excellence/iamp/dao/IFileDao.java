package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.IFile;

/**
 * 文件dao
 * @author huangjinyuan
 *
 */
@Repository
public interface IFileDao {
	
	/**
	 * 通过主键查找
	 * @return
	 */
	public IFile findById(String fileId);
	
	/**
	 * 插入
	 * @param ifile
	 */
	public void insert(IFile ifile);
	
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
