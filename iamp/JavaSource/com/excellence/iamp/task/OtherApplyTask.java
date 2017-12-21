package com.excellence.iamp.task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.excellence.iamp.util.TaskLogUtil;
import com.excellence.iamp.vo.Task;
/**
 * 远程定时任务启动类
 * @author wangjg
 * **/
public class OtherApplyTask extends ExBaseTask {

	@Override
	public void executeExJob(JobExecutionContext context, Task task, Map<String,Object> paramMap)
			throws JobExecutionException {
		HttpPost httppost = null;
		try {
			// 获取传递参数
			JobDataMap dataMap = context.getJobDetail().getJobDataMap();
			String url = (String)dataMap.get("url");
			 // 创建默认的httpClient实例.    
	        CloseableHttpClient httpclient = HttpClients.createDefault(); 
	        // 创建httppost    
	        httppost = new HttpPost(url);
	        // 创建参数队列    
	        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	        if(paramMap != null){
	        	Iterator<Entry<String,Object>> iter = paramMap.entrySet().iterator();
	    		while(iter.hasNext()){
	    			Entry<String,Object> entry = iter.next();
	    			String key = entry.getKey();
	    			String value = entry.getValue().toString();
	    			formparams.add(new BasicNameValuePair(key, value));
	    		}
	        }
	        UrlEncodedFormEntity uefEntity;  
            uefEntity = new UrlEncodedFormEntity(formparams, "GBK");  
            httppost.setEntity(uefEntity);  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            HttpEntity entity = response.getEntity();
            String  result = EntityUtils.toString(entity);
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
	    }

}
