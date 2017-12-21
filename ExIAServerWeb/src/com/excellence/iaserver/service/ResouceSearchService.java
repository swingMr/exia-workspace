package com.excellence.iaserver.service;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 职责:信息资源检索服务的接口定义
 * @author LJM
 *
 */
public interface ResouceSearchService {
	
	/**
	 * 功能:根据采用基于“两系统五要素”描述的知识需求进行信息资源检索
	 * @param context	  	 上下文参数
	 * @param ontoOfReq   	知识需求的JSON对象,具体格式参考如下:
{
    "domains": 
  [{"id": "领域ID","content":"领域名称","relevantLevel":相关度,"relevantConcepts":相关概念及相关度},{"id": "领域ID","content": "领域名称","relevantLevel":相关度,"relevantConcepts":相关概念及相关度}],
    "acts":
  [{"id": "行为ID","content":"行为名称","relevantLevel":相关度,"relevantConcepts":相关概念及相关度},{"id": "行为ID","content": "行为名称","relevantLevel":相关度,"relevantConcepts":相关概念及相关度}],
    "organdpersons":
  [{"id": "主体ID","content":"主体名称","relevantLevel":相关度,"relevantConcepts":相关概念及相关度},{"id": "主体ID","content": "主体名称","relevantLevel":相关度,"relevantConcepts":相关概念及相关度}],
    "objects": 
 [{"id": "客体ID","content":"客体名称","relevantLevel":相关度,"relevantConcepts":相关概念及相关度},{"id": "客体ID","content": "客体名称","relevantLevel":相关度,"relevantConcepts":相关概念及相关度}],
    "spaces": 
 [{"id": "空间ID","content":"空间名称","relevantLevel":相关度,"relevantConcepts":相关概念及相关度},{"id": "空间ID","content": "空间名称","relevantLevel":相关度}],
    "times":
 [{"id": "时间ID","content":"时间名称","relevantLevel":相关度,"relevantConcepts":相关概念及相关度},{"id": "时间ID","content": "时间名称"},"relevantLevel":相关度,"relevantConcepts":相关概念及相关度],
    "keywords": [{"content":"关键词","relevantLevel":相关度,"},{"content":"关键词","relevantLevel":相关度,"}]
}
	 * @param infoSourceCodes   信息资源库的代码     wcmmetatableflfg-法律法规库、wcmmetatablepartyliterature-党的文献库、wcmmetatabletechnicalstandard-技术标准库、wcmmetatablespecialpolicy--专项政策库、wcmmetatablescientificresearch--科学研究库、wcmmetatablepublicopinion-媒体资讯库、wcmmetatableleadershipinstruction-领导指示库、wcmmetatabletypicalevents-典型事件库、wcmmetatableforeignresources-国外资源库
	 * @param infoYears			各信息资源库的年份限制,格式参考如下:
		[{"infoSource":"信息资源库的代码","years":[2010,2011,2012,2013,2014,2015]},
		{"infoSource":"信息资源库的代码","years":[2010,2011,2012,2013,2014,2015]}]
	 * @param page				当前页码，默认为1。
	 * @param pageSize			分页大小，默认为50。
	 * @return					信息资源检索结果的分页结果JSON格式
		{
			"num": 627,
			"page": 1,
			"pageSize": 10,
			"informations": [
			{
			           "id": 34174,
			            "title": "中华人民共和国宪法修正案",
			            "url": "http: //law.npc.gov.cn/FLFG/flfgByID.action?flfgID=156&keyword=&zlsxid=08",
			"attributes":{
			"content": "",
			"abstract": "",
			 "genre": "",
			"symbol": "",
			"form": "",
			                "keywords": "法律规定;集体经济组织;中华人民共和国;宪法;民主管理",
			                "belong": "wcmmetatableflfg",
			                "bbrq": "1993-3-29"
			},
			"viewPageUrl":"http: //law.npc.gov.cn/FLFG/flfgByID.action?fssc"
			        },….
			    ]
		}
	 * @throws Exception
	 */
	public JSONObject searchByOntologies(Map context,JSONObject ontoOfReq,String[] infoSourceCodes,JSONArray infoYears,Integer page,Integer pageSize) throws Exception;
	
	/**
	 * 功能：根据SQL语句进行全文检索
	 * @param sql					全文检索的SQL语句
	 * @param infoSourceCodes		信息资源库的代码    wcmmetatableflfg-法律法规库、wcmmetatablepartyliterature-党的文献库、wcmmetatabletechnicalstandard-技术标准库、wcmmetatablespecialpolicy--专项政策库、wcmmetatablescientificresearch--科学研究库、wcmmetatablepublicopinion-媒体资讯库、wcmmetatableleadershipinstruction-领导指示库、wcmmetatabletypicalevents-典型事件库、wcmmetatableforeignresources-国外资源库
	 * @param page				当前页码，默认为1。
	 * @param pageSize			分页大小，默认为50。
	 * @return					结果格式参考searchByOntologies的结果格式说明
	 * @throws Exception
	 */
	public JSONObject searchBySql(String sql,String[] infoSourceCodes,Integer page,Integer pageSize) throws Exception;

	/**
	 * 功能：根据条件查询信息资源
	 * @param resOfReqJson			信息资源查询条件的JSON字符串
	 * @param infoSources			信息资源库的代码
	 * @param orderName				排序字段名称：title-标题排序、publishDate-发布日期排序
	 * @param orderType				排序方式：asc-升序、desc-降序。
	 * @param page					当前页码，默认为1。
	 * @param pageSize				分页大小，默认为50。
	 * @return
	 */
	public JSONObject searchByCondition(String resOfReqJson,
			String[] infoSources, String orderName, String orderType, int page,
			int pageSize)throws Exception;
}
