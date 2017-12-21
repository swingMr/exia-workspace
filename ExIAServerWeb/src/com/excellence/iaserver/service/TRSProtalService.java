package com.excellence.iaserver.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.excellence.iaserver.common.util.Constant;
import com.excellence.iaserver.common.util.TRSServiceUtil;
import com.trs.ckm.soap.ABSHold;
import com.trs.ckm.soap.ABSResult;
import com.trs.ckm.soap.RuleCATField;
import com.trs.ckm.soap.SegDictWord;
import com.trs.ckm.soap.TrsCkmSoapClient;

public class TRSProtalService {
	/*private TRSProtalService(){
		// ���þ������
		this.clusParam = new CluParam();
		this.clusParam.setdocsimv(0.2f);
		this.clusParam.setclusimv(0.3f);
		this.clusParam.setminnum(5);
        this.clusParam.setmaxcls(10);
        this.clusParam.setmincls(2);
	}*/
	
	private TrsCkmSoapClient getCkmSoapClient(){
		TrsCkmSoapClient client = new TrsCkmSoapClient(
			"http://" + Constant.CKM_HOST + ":" + Constant.CKM_PORT, 
			Constant.CKM_ACCOUNT, Constant.CKM_PASSWORD);
		return client;
	}
	
	/**
	 * ��ȡ�ı�ժҪ�������
	 * @param text
	 * @param numOfSub ����ʸ���
	 * @param percent ժҪ����ռ���³��Ȱٷֱ�
	 * @param dictName ժҪ�ʵ����ƣ�Ϊ�ձ�ʾ����ϵͳĬ�ϴʵ䣩Ĭ�ϴʵ�Ϊ./ DICT/absdict;
	 * @return json����{keywords:����ʣ�abstract:ժҪ}
	 */
	public JSONObject absText(String text, Integer numOfSub, Integer percent, String dictName) {
		if(StringUtils.isEmpty(text))return null;
		// Ĭ��10 ���ؼ���, 20%��ժҪ����
		if(numOfSub==null)numOfSub = 10;
		if(percent==null)percent = 20;
		
		JSONObject abs = new JSONObject();
		try {
			ABSResult absResult = this.getCkmSoapClient().ABSText(text, dictName, new ABSHold(numOfSub, percent));
			abs.put("keywords", absResult.getwordlist());
			abs.put("abstract", absResult.getabs());
			return abs;
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * �ִ�������
	 * @param text
	 * @return
	 */
	public JSONArray segText(String text){
		if(StringUtils.isEmpty(text))return null;
		JSONArray jsonWords = new JSONArray();
		try{
			SegDictWord[] segWords = this.getCkmSoapClient().SegText(text, 0);
			if(segWords==null)return null;
			for(SegDictWord segWord : segWords){
				jsonWords.put(segWord.getword());
			}
			return jsonWords;
		}catch(Exception ex){
			return null;
		}
	}
	
	/**
	 * �ִ�������(������)
	 * @param text
	 * @return
	 */
	public JSONArray segTextHasPos(String text){
		if(StringUtils.isEmpty(text))return null;
		JSONArray jsonWords = new JSONArray();
		try{
			SegDictWord[] segWords = this.getCkmSoapClient().SegText(text, 0);
			if(segWords==null)return null;
			for(SegDictWord segWord : segWords){
				JSONObject jsonWord = new JSONObject();
				jsonWord.put("text", segWord.getword());
				jsonWord.put("pos", segWord.getcate());
				jsonWords.put(jsonWord);
			}
			return jsonWords;
		}catch(Exception ex){
			return null;
		}
	}
	
	/**
	 * ȫ�ļ���
	 * @param text ��������
	 * @return
	 */
	public JSONObject fullTextSearch(String text,
			Map<String, Object> conditions, int pageNo, int pageSize) {
		if(conditions==null)conditions = new HashMap<String, Object>();
		conditions.put("text", text);
		conditions.put("page", pageNo);
		conditions.put("pageSize", pageSize);
		String result = TRSServiceUtil.search(conditions);
		if(StringUtils.isEmpty(result))return null;
		return new JSONObject(result);
	}
	
	public JSONArray ruleCATClassifyText(String text){
		try{
			RuleCATField[] ruleCATFields=new RuleCATField[1];
			ruleCATFields[0]=new RuleCATField(text,"����");
			String result = this.getCkmSoapClient().RuleCATClassifyText("ontology",
					ruleCATFields);
			if(StringUtils.isEmpty(result)) return null;
			String[] domains = StringUtils.split(result, ";");
			JSONArray jsonDomains = new JSONArray(Arrays.asList(domains));
			return jsonDomains;
		}catch(Exception ex){
			return null;
		}
	}
	
	public JSONArray ruleCATClassifyText(String author, String title , String content){
		try{
			RuleCATField[] ruleCATFields=new RuleCATField[3];
			ruleCATFields[0]=new RuleCATField(author,"����");
			ruleCATFields[1]=new RuleCATField(title,"����");
			ruleCATFields[2]=new RuleCATField(content,"����");
			String result = this.getCkmSoapClient().RuleCATClassifyText("ontology",
					ruleCATFields);
			if(StringUtils.isEmpty(result)) return null;
			String[] domains = StringUtils.split(result, ";");
			JSONArray jsonDomains = new JSONArray(Arrays.asList(domains));
			return jsonDomains;
		}catch(Exception ex){
			return null;
		}
	}
	
	/*
	private CluParam clusParam;
	private CluDocInfo[] clusDocList;
	private void reloadCorpus(){
		String cluAnalysisResPath = Constant.OA_DOC_PATH
        		+ File.separator + "trs" 
        		+ File.separator + "CLU_ANALYSIS_RESOURCE";
		this.clusDocList = null;
		
        List<File> files = FileUtil.getFiles(cluAnalysisResPath);
        if(files!=null && files.size()>0){
        	List<CluDocInfo> tmpCluDocInfoList = new ArrayList<CluDocInfo>();
        	tmpCluDocInfoList.add(new CluDocInfo("0", null, null));
            
        	for(int i = 0 ; i < files.size() ; i++){
				tmpCluDocInfoList.add(new CluDocInfo(String.valueOf(i + 1),
					FileUtil.readFileToString(files.get(i), "GBK").trim(), null));
        	}
            
        	this.clusDocList = new CluDocInfo[tmpCluDocInfoList.size()];
            tmpCluDocInfoList.toArray(this.clusDocList);
        }
	}
	
	private JSONArray clusterAnalysis(String analysisText){
		if(this.clusDocList==null){
			this.reloadCorpus();
			if(this.clusDocList==null) return null;
		}
		this.clusDocList[0].setcontent(analysisText);
		
		String theme = null;
        // ����
		try {
			CluClsInfo[] clusResult = this.getCkmSoapClient().ClusterDocList(
					this.clusDocList, this.clusParam, 0);
			if (clusResult != null){
	            for (int j = 0; j < clusResult.length; j++){
	                for(int k = 0 ; k < clusResult[j].getdoclist().length; k++){
	                	if(clusResult[j].getdoclist()[k].equals("0")){
	                		theme = clusResult[j].gettheme();
	                		break;
	                	}
	                }
	            }
	        }
		} catch (CkmSoapException ex) {
			LogUtil.error(this.getClass(), "clusterAnalysis", ex);
		}
		
		if(theme==null)return null;
		
		JSONArray jsonArray = new JSONArray();
		String[] words = StringUtils.split(theme, ",");
		for(String word : words){
			if(StringUtils.isEmpty(word.trim()))continue;
			jsonArray.put(word.trim());
		}
		
		return jsonArray;
	}*/
}
