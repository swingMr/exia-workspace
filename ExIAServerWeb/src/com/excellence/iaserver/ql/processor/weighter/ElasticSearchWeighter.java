package com.excellence.iaserver.ql.processor.weighter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.excellence.exke.common.ql.RunContext;
import com.excellence.exke.common.ql.processor.IWeighter;
import com.excellence.exke.common.vo.enums.ESearchResultDataType;

public class ElasticSearchWeighter implements IWeighter{

	@Override
	public String getName() {
		return this.getClass().getName();
	}

	@Override
	public void setName(String arg0) {}

	@Override
	public ESearchResultDataType getAdaptedDatType() {
		return ESearchResultDataType.Information;
	}

	/**
	 * double dRet = 0;初始相关度为0
	 * 第一步：
	 * 	获取查询出的信息资源的标签，遍历标签名称，跟已构造的词-权重map对此，如果包含该标签，词-权重map对应的权重值改为"原权重值"*"标签的相关性度"
	 * 第二步：
	 *  获取信息资源的关键词，遍历，若词-权重map中包含该词，该信息资源的相关度  dRet = dRet+“该关键词在map中的值”
	 * 第三步：
	 *  获取信息资源的领域，遍历，若词-权重map中包含该领域，该信息资源的相关度  dRet = dRet+“该领域在map中的值”
	 * 第四步：
	 * 返回资源的相关度  dRet
	 */
	@Override
	public float weight(Object arg0, HashMap<String, Object> params, RunContext arg1) {
		if(!(arg0 instanceof JSONObject))return 0;
		JSONObject jsonInfo = (JSONObject)arg0;
		Map<String, Double> newWordRelevancies = this.overlayWordRelevancies(
			jsonInfo, (Map<String, Double>) params.get("wordRelevancies"));
		Double weight = 0.0;
		
		if(jsonInfo.has("keyWords") && !jsonInfo.isNull("keyWords")){
			weight += calculateWeight(newWordRelevancies, jsonInfo.get("keyWords"));
		}
		
		if(jsonInfo.has("category") && !jsonInfo.isNull("category")){
			weight += calculateWeight(newWordRelevancies, jsonInfo.get("category"));
		}
		return weight.floatValue();
	}

	
	private Map<String, Double> overlayWordRelevancies(JSONObject jsonInfo, 
			Map<String, Double> orgWordRelevancies){
		if(!jsonInfo.has("tags") || jsonInfo.isNull("tags") 
			|| !(jsonInfo.get("tags") instanceof JSONObject)){
			return orgWordRelevancies;
		}
		
		Map<String, Double> newWordRelevancies = new HashMap<String,Double>();
		newWordRelevancies.putAll(orgWordRelevancies);
		
		JSONObject jsonTags = jsonInfo.getJSONObject("tags");
		Iterator it = jsonTags.keys();
		while (it.hasNext()) {
			String key = (String) it.next();
			JSONArray jsonTmpArray = jsonTags.getJSONArray(key);
			if(jsonTmpArray==null || jsonTmpArray.length()==0)continue;
			for(int i = 0 ; i < jsonTmpArray.length() ; i++){
				JSONObject jsonTmpElement = jsonTmpArray.getJSONObject(i);
				if(!jsonTmpElement.isNull("content")){
					String content = jsonTmpElement.getString("content");
					if(newWordRelevancies.containsKey(content)){
						double weight = newWordRelevancies.get(content);
						if(jsonTmpElement.has("similarity")){
							weight *= jsonTmpElement.getDouble("similarity");
						}else if(jsonTmpElement.has("relevantLevel")){
							weight *= jsonTmpElement.getDouble("relevantLevel");
						}
						newWordRelevancies.put(content, weight);
					}
				}
			}
		}
		
		return newWordRelevancies;
	}
	
	private double calculateWeight(Map<String, Double> wordRelevancies, Object values){
		double weight = 0.0;
		if(values instanceof String) {
			String[] valuesArray = StringUtils.split(values.toString(),";");
			for(int i = 0 ; i < valuesArray.length ; i++){
				String value = valuesArray[i];
				if(StringUtils.isBlank(value))continue;
				if(wordRelevancies.containsKey(value)){
					weight += wordRelevancies.get(value);
					wordRelevancies.remove(value);
				}
			}
		}else if(values instanceof JSONArray){
			JSONArray jsonValues = (JSONArray)values;
			for(int i=0;i<jsonValues.length();i++){
				String value = jsonValues.getString(i);
				if(StringUtils.isBlank(value))continue;
				if(wordRelevancies.containsKey(value)){
					weight += wordRelevancies.get(value);
					wordRelevancies.remove(value);
				}
			}
		}
		return weight;
	}
}
