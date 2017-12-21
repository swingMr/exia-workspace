package com.excellence.iamp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.iamp.dao.PythonTypeDao;
import com.excellence.iamp.service.PythonTypeService;
import com.excellence.iamp.vo.PythonType;
@Service
public class PythonTypeImpl implements PythonTypeService{
	@Autowired
	private PythonTypeDao pythonTypeDao;
	
	@SuppressWarnings("unchecked")
	public List<PythonType> getList(){
		return pythonTypeDao.getList();
	};
}
