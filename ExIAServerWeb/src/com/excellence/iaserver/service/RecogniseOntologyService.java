package com.excellence.iaserver.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.excellence.iamp.robot.vo.ChatMsg;

/**
 * 职责：识别知识本体的服务接口
 * @author LJM
 *
 */
public interface RecogniseOntologyService {
	
	/**
	 * 功能:从一句文本中识别出相关的领域
	 * @param text			文本句(不能为空)
	 * @return				Map对象列表，id-领域ID，content-领域名称，relevancy-相关度值，parentNames-概念类型，type-实例还是类，ALIAS--别名，，按相关度从大到小排序，
	 * @throws Exception
	 */
	public List<Map> recognizeDomainsOfTextSec(String text) throws Exception;
	
	/**
	 * 功能:从一句文本中识别出涉及的本体概念
	 * @param text			文本句(不能为空)
	 * @param clsNames		JSONArray字符串。限定的要素类型（为空时，不限定），比如：领域、行为、自然人、法人、时间、空间、问题、指标等。
	 * @param clsNames2 
	 * @param clsNames2 
	 * @param text2 
	 * @return				Map对象列表，id-概念ID，content-领域名称，relevancy-相关度值，parentNames-概念类型，type-实例还是类，ALIAS--别名，按相关度从大到小排序，
	 * @throws Exception
	 */
	public JSONObject recognizeConceptsOfTextSec(String appCode,String context,String title,String keyWord, String text, String clsNames) throws Exception;
	
	/**
	 * 功能：根据训练语料生成领域特征文件
	 * @param modelName 特征文件名	
	 * @param inputDir	语料所在文件夹路径	
	 * @param outputDir	特征文件输出文件夹路径
	 * @throws Exception
	 */
	public void generateModelOfClassify(String modelName,String inputDir,String outputDir)throws Exception;
	
	/**
	 * 功能：内容标引
	 * @param text 正文内容
	 * @param htmlClazz 类型
	 * @param words 词
	 * @return
	 * @throws Exception
	 */
	public String getDocIndexes(String text,String wordClazz,String words) throws Exception;
	
	/**
	 * 段落标引
	 * @param text
	 * @param sectionClazz
	 * @param sections
	 * @return
	 */
	public String getSectionIndexes(String text,String sectionClazz,String sections);
	
	/**
	 * 分析识别消息内容
	 * @param chatMsg
	 * @return
	 */
	public ChatMsg recognizeChatMsg(ChatMsg chatMsg);
	
}
