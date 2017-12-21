package com.excellence.iamp.resource.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.excellence.exke.connector.Configure;
import com.excellence.exke.connector.ConnectorException;
import com.excellence.exke.connector.Host;
import com.excellence.exke.connector.HttpConnector;
import com.excellence.iacommon.common.util.Constant;

@Service
public class SearchResourceService  {

	private HttpConnector getConnector(String connectContext, String serviceContext) throws ConnectorException{
		Host host = new Host(Constant.EXKE_HOST, Constant.EXKE_PORT);
		Configure configure = new Configure();
		configure.setHost(host);
		configure.setContext(connectContext);
		configure.setAccount(Constant.EXKE_ACCOUNT);
		configure.setPassword(Constant.EXKE_PASSWORD);
		configure.setServiceContext(serviceContext);
		HttpConnector connector = new HttpConnector(configure);
		connector.connect();
		return connector;
	}
	
	public String getCatalogTree(String url) {
		JSONObject jsonResult;
		try{
			jsonResult = this.getConnector("ExKE", "ExKEResource").getWCMCatalogTree(url);
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", ex.getMessage());
		}
		return jsonResult.toString();
	}
	
	public String searchChildren(String id, String text, String type, String parentPath, String pageNo, String pageSize) {
		JSONObject jsonResult;
		try{
			jsonResult = this.getConnector("ExKE", "ExKEResource").searchWCMNodeChildren(id, text, type, parentPath, pageNo, pageSize);
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", ex.getMessage());
		}
		return jsonResult.toString();
	}

	public String getResource(String url) {
		JSONObject jsonResult;
		String result;
		try{
			result = this.getConnector("ExKE", "ExKEResource").getResource(url);
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", ex.getMessage());
			return jsonResult.toString();
		}
		return result;
	}

	public String getTableName(String channelId) {
		JSONObject jsonResult;
		String result;
		try{
			result = this.getConnector("ExKE", "ExKEResource").getTableName(channelId);
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", ex.getMessage());
			return jsonResult.toString();
		}
		return result;
	}
	
	public String updateWCMDoc(String channelId, String docId, String condition) {
		JSONObject jsonResult;
		String result;
		try{
			result = this.getConnector("ExKE", "ExKEResource").updateWCMDoc(channelId, docId, "×ÊÔ´¿â", condition).toString();
		}catch(Exception ex){
			jsonResult = new JSONObject();
			jsonResult.put("status", 0);
			jsonResult.put("msg", ex.getMessage());
			return jsonResult.toString();
		}
		return result;
	}
}
