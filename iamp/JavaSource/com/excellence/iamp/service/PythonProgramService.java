package com.excellence.iamp.service;

import java.util.Map;

import com.excellence.iamp.vo.PythonProgram;
import com.github.pagehelper.PageInfo;

public interface PythonProgramService {
	PageInfo getList(int num,int targetPageSize,Map<String, String> paramMap);
	int insert(PythonProgram pythonProgram);
	int update(PythonProgram pythonProgram);
	PythonProgram findById(String programId);
	int delById(String programId);
}
