package com.excellence.iamp.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.excellence.iamp.vo.PythonProgram;

@Repository
public interface PythonProgramDao {
	List getList(Map<String, String> paramMap);
	
	/**
	 * ����
	 * @param pythonProgram
	 */
	int insert(PythonProgram pythonProgram);
	
	/**
	 * ����
	 * @param pythonProgram
	 */
	int update(PythonProgram pythonProgram);
	
	/**
	 * ͨ��Id�ҵ�����
	 * @param ProgramId;
	 */
	PythonProgram getPythonProgramById(String programId);
	
	/**
	 * ͨ��Idɾ������
	 * @param ProgramId;
	 */
	int delById(String programId);
	
	/**
	 * ͳ������
	 * @param paramMap
	 * @return
	 */
	public int countByCondition(Map paramMap);
}
