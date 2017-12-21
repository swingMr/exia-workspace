package com.excellence.iamp.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excellence.common.util.GUIDGenerator;
import com.excellence.iamp.dao.PythonProgramDao;
import com.excellence.iamp.service.PythonProgramService;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.vo.PythonProgram;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PythonProgramImpl implements PythonProgramService{
	@Autowired
	private PythonProgramDao pythonProgramDao;
	public PageInfo getList(int num,int targetPageSize,Map<String, String> paramMap){
		 int count = pythonProgramDao.countByCondition(paramMap);
		 PageHelper.startPage(num, targetPageSize,false);
		 PageInfo pageInfo = new PageInfo<PythonProgram>(pythonProgramDao.getList(paramMap));
		 int pageSizes = Page.getPageCount(count, targetPageSize);
		 pageInfo.setPages(pageSizes);
		 return pageInfo;
	}
	
	public int insert(PythonProgram pythonProgram){
		pythonProgram.setProgramId(GUIDGenerator.getInstance().getGUID().replaceAll("-", ""));
		return pythonProgramDao.insert(pythonProgram);
	};
	public int update(PythonProgram pythonProgram){
		return pythonProgramDao.update(pythonProgram);
	};
	
	public PythonProgram findById(String programId){
		return pythonProgramDao.getPythonProgramById(programId);
	}
	
	public int delById(String programId){
		return pythonProgramDao.delById(programId);
	}
}
