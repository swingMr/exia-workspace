package com.excellence.iamp.resource.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.excellence.exke.connector.Configure;
import com.excellence.exke.connector.ConnectorException;
import com.excellence.exke.connector.Host;
import com.excellence.exke.connector.HttpConnector;
import com.excellence.iacommon.common.util.Constant;

@Service
public class ExkeOntologService  {

	private static HttpConnector getConnector(String connectContext) throws ConnectorException{
		Host host = new Host(Constant.EXKE_HOST, Constant.EXKE_PORT);
		Configure configure = new Configure();
		configure.setHost(host);
		configure.setContext(connectContext);
		configure.setAccount(Constant.EXKE_ACCOUNT);
		configure.setPassword(Constant.EXKE_PASSWORD);
		HttpConnector connector = new HttpConnector(configure);
		connector.connect();
		return connector;
	}
	
	public static String getConceptsByKeywords(String[] conceptNames) {
		JSONObject jsonResult;
		try{
			jsonResult  =getConnector("ExKE").getConceptsByNames(null, conceptNames);
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", ex.getMessage());
		}
		return jsonResult.toString();
	}
	
	public static String getChildConceptsById(String conceptId,String conditions,int num) {
		JSONArray jsonResult;
		try{
			jsonResult =  getConnector("ExKE").getOntologies(conceptId,conditions,num);
		}catch(Exception ex){
			jsonResult = new JSONArray();
			JSONObject obj = new JSONObject();
			obj.put("status", 0);
			obj.put("msg", ex.getMessage());
		}
		return jsonResult.toString();
	}

}
