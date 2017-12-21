package com.excellence.iaserver.service;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * ְ��:��Ϣ��Դ��������Ľӿڶ���
 * @author LJM
 *
 */
public interface ResouceSearchService {
	
	/**
	 * ����:���ݲ��û��ڡ���ϵͳ��Ҫ�ء�������֪ʶ���������Ϣ��Դ����
	 * @param context	  	 �����Ĳ���
	 * @param ontoOfReq   	֪ʶ�����JSON����,�����ʽ�ο�����:
{
    "domains": 
  [{"id": "����ID","content":"��������","relevantLevel":��ض�,"relevantConcepts":��ظ����ض�},{"id": "����ID","content": "��������","relevantLevel":��ض�,"relevantConcepts":��ظ����ض�}],
    "acts":
  [{"id": "��ΪID","content":"��Ϊ����","relevantLevel":��ض�,"relevantConcepts":��ظ����ض�},{"id": "��ΪID","content": "��Ϊ����","relevantLevel":��ض�,"relevantConcepts":��ظ����ض�}],
    "organdpersons":
  [{"id": "����ID","content":"��������","relevantLevel":��ض�,"relevantConcepts":��ظ����ض�},{"id": "����ID","content": "��������","relevantLevel":��ض�,"relevantConcepts":��ظ����ض�}],
    "objects": 
 [{"id": "����ID","content":"��������","relevantLevel":��ض�,"relevantConcepts":��ظ����ض�},{"id": "����ID","content": "��������","relevantLevel":��ض�,"relevantConcepts":��ظ����ض�}],
    "spaces": 
 [{"id": "�ռ�ID","content":"�ռ�����","relevantLevel":��ض�,"relevantConcepts":��ظ����ض�},{"id": "�ռ�ID","content": "�ռ�����","relevantLevel":��ض�}],
    "times":
 [{"id": "ʱ��ID","content":"ʱ������","relevantLevel":��ض�,"relevantConcepts":��ظ����ض�},{"id": "ʱ��ID","content": "ʱ������"},"relevantLevel":��ض�,"relevantConcepts":��ظ����ض�],
    "keywords": [{"content":"�ؼ���","relevantLevel":��ض�,"},{"content":"�ؼ���","relevantLevel":��ض�,"}]
}
	 * @param infoSourceCodes   ��Ϣ��Դ��Ĵ���     wcmmetatableflfg-���ɷ���⡢wcmmetatablepartyliterature-�������׿⡢wcmmetatabletechnicalstandard-������׼�⡢wcmmetatablespecialpolicy--ר�����߿⡢wcmmetatablescientificresearch--��ѧ�о��⡢wcmmetatablepublicopinion-ý����Ѷ�⡢wcmmetatableleadershipinstruction-�쵼ָʾ�⡢wcmmetatabletypicalevents-�����¼��⡢wcmmetatableforeignresources-������Դ��
	 * @param infoYears			����Ϣ��Դ����������,��ʽ�ο�����:
		[{"infoSource":"��Ϣ��Դ��Ĵ���","years":[2010,2011,2012,2013,2014,2015]},
		{"infoSource":"��Ϣ��Դ��Ĵ���","years":[2010,2011,2012,2013,2014,2015]}]
	 * @param page				��ǰҳ�룬Ĭ��Ϊ1��
	 * @param pageSize			��ҳ��С��Ĭ��Ϊ50��
	 * @return					��Ϣ��Դ��������ķ�ҳ���JSON��ʽ
		{
			"num": 627,
			"page": 1,
			"pageSize": 10,
			"informations": [
			{
			           "id": 34174,
			            "title": "�л����񹲺͹��ܷ�������",
			            "url": "http: //law.npc.gov.cn/FLFG/flfgByID.action?flfgID=156&keyword=&zlsxid=08",
			"attributes":{
			"content": "",
			"abstract": "",
			 "genre": "",
			"symbol": "",
			"form": "",
			                "keywords": "���ɹ涨;���徭����֯;�л����񹲺͹�;�ܷ�;��������",
			                "belong": "wcmmetatableflfg",
			                "bbrq": "1993-3-29"
			},
			"viewPageUrl":"http: //law.npc.gov.cn/FLFG/flfgByID.action?fssc"
			        },��.
			    ]
		}
	 * @throws Exception
	 */
	public JSONObject searchByOntologies(Map context,JSONObject ontoOfReq,String[] infoSourceCodes,JSONArray infoYears,Integer page,Integer pageSize) throws Exception;
	
	/**
	 * ���ܣ�����SQL������ȫ�ļ���
	 * @param sql					ȫ�ļ�����SQL���
	 * @param infoSourceCodes		��Ϣ��Դ��Ĵ���    wcmmetatableflfg-���ɷ���⡢wcmmetatablepartyliterature-�������׿⡢wcmmetatabletechnicalstandard-������׼�⡢wcmmetatablespecialpolicy--ר�����߿⡢wcmmetatablescientificresearch--��ѧ�о��⡢wcmmetatablepublicopinion-ý����Ѷ�⡢wcmmetatableleadershipinstruction-�쵼ָʾ�⡢wcmmetatabletypicalevents-�����¼��⡢wcmmetatableforeignresources-������Դ��
	 * @param page				��ǰҳ�룬Ĭ��Ϊ1��
	 * @param pageSize			��ҳ��С��Ĭ��Ϊ50��
	 * @return					�����ʽ�ο�searchByOntologies�Ľ����ʽ˵��
	 * @throws Exception
	 */
	public JSONObject searchBySql(String sql,String[] infoSourceCodes,Integer page,Integer pageSize) throws Exception;

	/**
	 * ���ܣ�����������ѯ��Ϣ��Դ
	 * @param resOfReqJson			��Ϣ��Դ��ѯ������JSON�ַ���
	 * @param infoSources			��Ϣ��Դ��Ĵ���
	 * @param orderName				�����ֶ����ƣ�title-��������publishDate-������������
	 * @param orderType				����ʽ��asc-����desc-����
	 * @param page					��ǰҳ�룬Ĭ��Ϊ1��
	 * @param pageSize				��ҳ��С��Ĭ��Ϊ50��
	 * @return
	 */
	public JSONObject searchByCondition(String resOfReqJson,
			String[] infoSources, String orderName, String orderType, int page,
			int pageSize)throws Exception;
}
