package com.excellence.iamp.task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.excellence.iacommon.common.util.JavaHandlePython;
import com.excellence.iamp.util.TaskLogUtil;
import com.excellence.iamp.vo.Task;
/**
 * python定时任务启动类
 * @author wangjg
 * **/
public class PythonServiceTask extends ExBaseTask {


	public void executeExJob(JobExecutionContext context, Task task, Map<String,Object> paramMap)
			throws JobExecutionException {
		try {
			// 获取传递参数
			JobDataMap dataMap = context.getJobDetail().getJobDataMap();
			TaskLogUtil taskLogUtil = (TaskLogUtil) dataMap.get("taskLogUtil");
			String url = (String)dataMap.get("url");
			String[] urlArr = StringUtils.split(url, ";");
			JSONArray arr =new JSONArray();
			for(int i=0;i<urlArr.length;i++){
				String result = JavaHandlePython.HandlePython(urlArr[i], null);
				arr.put(result);
			}
			System.out.print(arr.toString());
	        }catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	    }

}
