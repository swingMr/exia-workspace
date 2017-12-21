package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.PythonProgram;

@Repository
public interface PythonProgramDao {
	List getList(Map<String, String> paramMap);
	
	/**
	 * 新增
	 * @param pythonProgram
	 */
	int insert(PythonProgram pythonProgram);
	
	/**
	 * 更新
	 * @param pythonProgram
	 */
	int update(PythonProgram pythonProgram);
	
	/**
	 * 通过Id找到对象
	 * @param ProgramId;
	 */
	PythonProgram getPythonProgramById(String programId);
	
	/**
	 * 通过Id删除对象
	 * @param ProgramId;
	 */
	int delById(String programId);
	
	/**
	 * 统计总数
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
}
