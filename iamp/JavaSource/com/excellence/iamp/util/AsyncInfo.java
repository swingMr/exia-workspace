package com.excellence.iamp.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.excellence.exke.common.vo.InformationVo;
import com.excellence.exke.connector.Configure;
import com.excellence.exke.connector.ConnectorException;
import com.excellence.exke.connector.Host;
import com.excellence.exke.connector.HttpConnector;
import com.excellence.iacommon.common.util.Constant;
import com.excellence.iacommon.common.util.HttpClientUtil;
import com.excellence.iamp.mongodb.vo.Resource;
import com.excellence.iamp.task.ResourceTaskManagement;
@Service
public class AsyncInfo {
	private HttpConnector getConnector() throws ConnectorException {
		return this.getConnector("ExKE", "ExKE");
	}
	
	private static HttpConnector getConnector(String connectContext, String serviceContext) throws ConnectorException{
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
	//信息资源同步接口：新增信息资源
	@Async
	public boolean informationAdded(InformationVo info,String content){
		JSONObject json = new JSONObject();
		try {
			json =  this.getConnector().informationAdded(info, content);
		} catch (ConnectorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean results =json.getBoolean("status");
		System.out.println("最终返回的结果！！"+results);
		return results;
	};
	//信息资源同步接口：更新信息资源
	@Async
	public  boolean informationUpdated(InformationVo info,String newContent,String oldContent,String newTitle,String newUrl) throws InterruptedException{
		JSONObject json = new JSONObject();	
		try {
			json = this.getConnector().informationUpdated(info,newContent,oldContent,newTitle,newUrl);
		} catch (ConnectorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean results =json.getBoolean("status");
		System.out.println("最终返回的结果！！"+results);
		return results;
	};
	
	//信息资源同步接口：删除信息资源
	@Async
	public boolean informationRemoved(String url,String title,String content){
		JSONObject json = new JSONObject();	
		try {
			json = this.getConnector().informationRemoved(url, title, content);
		} catch (ConnectorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean results =json.getBoolean("status");
		return results;
	};
	
	//更新索引到Elas;
/*	@Async
	public void resourceTask(Resource resource){
		Settings settings = Settings.settingsBuilder().put("cluster.name", "my-application").build();
		JSONArray updateIndex = new JSONArray();
		String url ="";
		Map<String, Object> params = new HashMap<String, Object>();

		try {
			HttpClientUtil.post(url, "GBK", params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
