package com.excellence.iamp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.dao.IFileDao;
import com.excellence.iamp.service.IFileService;
import com.excellence.iamp.vo.IFile;

/**
 * 文件Service实现
 * @author huangjinyuan
 *
 */
@Service
public class IFileServiceImpl implements IFileService {
	
	@Autowired
	private IFileDao iFileDao;

	public IFile findById(String fileId) {
		return iFileDao.findById(fileId);
	}

	public void create(IFile ifile) {
		if(StringUtils.isEmpty(ifile.getFileId())) {
			ifile.setFileId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		iFileDao.insert(ifile);
	}

	public void delete(String fileId) {
		// TODO Auto-generated method stub
		iFileDao.delete(fileId);
	}

	public void update(IFile ifile) {
		// TODO Auto-generated method stub
		iFileDao.update(ifile);
	}

	public void createNewTable() {
		// TODO Auto-generated method stub
		iFileDao.createNewTable();
	}

	public List<IFile> getByCondition(Map paramMap) {
		// TODO Auto-generated method stub
		List<IFile> list = iFileDao.getByCondition(paramMap);
		return list;
	} 
	
}
