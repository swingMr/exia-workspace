package com.excellence.iamp.service;

import java.util.List;
import java.util.Map;

import com.excellence.iamp.vo.IFile;

public interface IFileService {
	
	/**
	 * ͨ����������
	 * @return
	 */
	public IFile findById(String fileId);
	
	/**
	 * ����
	 * @param ifile
	 */
	public void create(IFile ifile);
	
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
