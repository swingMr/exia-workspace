package com.excellence.iamp.task;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.excellence.iamp.service.IFileService;
import com.excellence.iamp.util.SpringFactory;
import com.excellence.iamp.vo.IFile;
import com.excellence.iamp.vo.Task;

public class TaskManagement  extends ExBaseTask{
	
	private IFileService iFileService;
	 public void executeExJob(JobExecutionContext context, Task task, Map<String,Object> paramMaps)
				throws JobExecutionException {
		 iFileService = (IFileService) SpringFactory.getObject("iFileService");
        List<IFile> list = new ArrayList();
        Map<String,String> paramMap = new HashMap<String,String>();
        //查找所有临时文件
        paramMap.put("isTemp", "1");
        list = iFileService.getByCondition(paramMap);
        Date date = new Date();
        for(int i=0; list.size()>i; i++){
        	int judge =date.compareTo(list.get(i).getExpireDate());
        	if(judge>0){
        		//已过期
        		File f = new File(list.get(i).getAccessPath()); 
        		if(f.exists()){
        		    f.delete();
        		}
        		iFileService.delete(list.get(i).getFileId());	
        	}
        }
    }
}
