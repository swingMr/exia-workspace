package com.excellence.iaserver.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.excellence.iamp.robot.vo.ChatMsg;

/**
 * ְ��ʶ��֪ʶ����ķ���ӿ�
 * @author LJM
 *
 */
public interface RecogniseOntologyService {
	
	/**
	 * ����:��һ���ı���ʶ�����ص�����
	 * @param text			�ı���(����Ϊ��)
	 * @return				Map�����б�id-����ID��content-�������ƣ�relevancy-��ض�ֵ��parentNames-�������ͣ�type-ʵ�������࣬ALIAS--������������ضȴӴ�С����
	 * @throws Exception
	 */
	public List<Map> recognizeDomainsOfTextSec(String text) throws Exception;
	
	/**
	 * ����:��һ���ı���ʶ����漰�ı������
	 * @param text			�ı���(����Ϊ��)
	 * @param clsNames		JSONArray�ַ������޶���Ҫ�����ͣ�Ϊ��ʱ�����޶��������磺������Ϊ����Ȼ�ˡ����ˡ�ʱ�䡢�ռ䡢���⡢ָ��ȡ�
	 * @param clsNames2 
	 * @param clsNames2 
	 * @param text2 
	 * @return				Map�����б�id-����ID��content-�������ƣ�relevancy-��ض�ֵ��parentNames-�������ͣ�type-ʵ�������࣬ALIAS--����������ضȴӴ�С����
	 * @throws Exception
	 */
	public JSONObject recognizeConceptsOfTextSec(String appCode,String context,String title,String keyWord, String text, String clsNames) throws Exception;
	
	/**
	 * ���ܣ�����ѵ�������������������ļ�
	 * @param modelName �����ļ���	
	 * @param inputDir	���������ļ���·��	
	 * @param outputDir	�����ļ�����ļ���·��
	 * @throws Exception
	 */
	public void generateModelOfClassify(String modelName,String inputDir,String outputDir)throws Exception;
	
	/**
	 * ���ܣ����ݱ���
	 * @param text ��������
	 * @param htmlClazz ����
	 * @param words ��
	 * @return
	 * @throws Exception
	 */
	public String getDocIndexes(String text,String wordClazz,String words) throws Exception;
	
	/**
	 * �������
	 * @param text
	 * @param sectionClazz
	 * @param sections
	 * @return
	 */
	public String getSectionIndexes(String text,String sectionClazz,String sections);
	
	/**
	 * ����ʶ����Ϣ����
	 * @param chatMsg
	 * @return
	 */
	public ChatMsg recognizeChatMsg(ChatMsg chatMsg);
	
}
