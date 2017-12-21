package com.excellence.iaserver.ql.processor.infosearcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.excellence.exke.common.ql.RunContext;
import com.excellence.exke.common.ql.processor.IInfoSearcher;
import com.excellence.exke.common.ql.vo.InformationData;
import com.excellence.exke.common.vo.ConceptVo;
import com.excellence.iaserver.common.util.ElasticSearchUtil;

public class ElasticSearchSearcher implements IInfoSearcher {

	@Override
	public String getName() {
		return this.getClass().getName();
	}

	@Override
	public void setName(String name) {}

	@Override
	public InformationData search(List<List<ConceptVo>> concepts,
			List<List<String>> words, HashMap<String, Object> params,
			RunContext context) throws Exception {
		
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("isReturnContent", 0);
		conditions.put("page", MapUtils.getInteger(params, "page", 1));
		conditions.put("pageSize", MapUtils.getInteger(params, "pageSize", 10));
		conditions.put("categorys", MapUtils.getString(params,"categorys", null));
		conditions.put("infoSources", MapUtils.getString(params,"infoSources", null));
		
		String text = "";
		if(words!=null && words.size()>0)
			text = StringUtils.join(words.get(0).iterator(), " ");
		conditions.put("text", text);
		
		String esResult = ElasticSearchUtil.elSearch(conditions);
		if(StringUtils.isEmpty(esResult)) return null;
		
		JSONObject jsonResult = new JSONObject(esResult);
		if(!jsonResult.has("data") && !(jsonResult.get("data") instanceof JSONArray))return null;
		if(jsonResult.has("num")){
			int num = jsonResult.getInt("num");
			params.put("num", num);
		}
		
		List<JSONObject> infos = new ArrayList<JSONObject>();
		JSONArray jsonDatas= jsonResult.getJSONArray("data");
		for(int i = 0 ; i < jsonDatas.length() ; i++){
			infos.add(jsonDatas.getJSONObject(i));
		}
		InformationData result = new InformationData();
		result.setInfos(infos);
		return result;
	}

}
