package com.excellence.iacommon.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * Http请求工具类
 * @author Huangyb
 * @date 2017-3-25
 * @2014-2016 Excellence
 */
public class HttpClientUtil {
	
	/**
	 * 发送POST请求
	 * @param url URL地址
	 * @param charset 字符编码
	 * @param params 发送参数
	 * @return 应答内容
	 * @throws HttpException
	 */
	public static String post(String url, String charset, Map<String, Object> params) throws HttpException{
		HttpClientContext context = HttpClientContext.create();
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		if(params!=null && params.size()>0){
			Iterator<Entry<String, Object>> it = params.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, Object> pairs = it.next();
				formParams.add(new BasicNameValuePair(pairs.getKey(), pairs
						.getValue().toString()));
			}
		}
		
		CloseableHttpResponse response = null;
		try{
			httpPost.setEntity(new UrlEncodedFormEntity(formParams, charset));
			response = httpClient.execute(httpPost, context);
			int statusCode = response.getStatusLine().getStatusCode();
			String result = "";
			if(statusCode==400 ||statusCode==505){
				throw new HttpException();
			}else if(statusCode==302 || statusCode==301){
				Header[] headers = response.getHeaders("Location");
				if(headers!=null && headers.length>0){
					String location = headers[0].getValue();
					result = HttpClientUtil.get(location);
				}
			}else{
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity);
			}
			return result;
		}catch(Exception ex){
			throw new HttpException(ex.getMessage());
		}finally{
			if(response!=null){
				try { response.close(); } catch (IOException e) {}
			}
		}
	}
	
	/**
	 * 发送GET请求
	 * @param url URL地址
	 * @return 应答内容
	 * @throws HttpException
	 */
	public static String get(String url) throws HttpException{
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		
		CloseableHttpResponse response = null;
		try{
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if(response.getStatusLine().getStatusCode()!=200){
				throw new HttpException();
			}
			String result = EntityUtils.toString(entity);
			return result;
		}catch(Exception ex){
			throw new HttpException(ex.getMessage());
		}finally{
			if(response!=null){
				try { response.close(); } catch (IOException e) {}
			}
		}
	}
	
	/**
	 * 功能:通过httpclient调用接口
	 * @param url			接口地址
	 * @param paramMap		传入的参数
	 * @return
	 */
    public static String getHttpClientResult(String url,Map<String,Object> paramMap) {  
        // 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault(); 
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if(paramMap != null){
        	Iterator<Entry<String,Object>> iter = paramMap.entrySet().iterator();
    		while(iter.hasNext()){
    			Entry<String,Object> entry = iter.next();
    			String key = entry.getKey();
    			//String value = entry.getValue().toString();
    			formparams.add(new BasicNameValuePair(key,  entry.getValue()+""));
    		}
        }
        UrlEncodedFormEntity uefEntity;  
        String result = "";
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "GBK");  
            httppost.setEntity(uefEntity);  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {
        	if(httppost!=null){
        		httppost.releaseConnection();
			}
        }
		return result;
    }
}
