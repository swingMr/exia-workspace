package com.excellence.iamp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.dao.FileExtConfigDao;
import com.excellence.iamp.service.FileExtConfigService;
import com.excellence.iamp.vo.FileExtConfig;

/**
 * 文件转换配置service实现
 * @author huangjinyuan
 *
 */
@Service
public class FileExtConfigServiceImpl implements FileExtConfigService {
	
	@Autowired
	private FileExtConfigDao fileExtConfigDao;
	
	public FileExtConfig findById(String configId) {
		return fileExtConfigDao.findById(configId);
	}

	public void create(FileExtConfig fileExtConfig) {
		if(StringUtils.isEmpty(fileExtConfig.getConfigId())) {
			fileExtConfig.setConfigId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		}
		fileExtConfigDao.insert(fileExtConfig);
	}

	public void update(FileExtConfig fileExtConfig) {
		fileExtConfigDao.update(fileExtConfig);
	}

	public void delete(String configId) {
		fileExtConfigDao.delete(configId);
	}

	public void createNewTable() {
		fileExtConfigDao.createNewTable();
	}

	public FileExtConfig getFileExtConfig() {
		List<FileExtConfig> list = fileExtConfigDao.getList();
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
