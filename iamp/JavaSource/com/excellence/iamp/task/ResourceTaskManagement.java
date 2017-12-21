package com.excellence.iamp.task;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.excellence.iacommon.common.util.CategoryAndKeyWordsUtil;
import com.excellence.iacommon.common.util.Constant;
import com.excellence.iamp.mongodb.service.ResourceLibService;
import com.excellence.iamp.service.ResourceTaskService;
import com.excellence.iamp.util.SpringFactory;
import com.excellence.iamp.vo.ResourceTask;
import com.excellence.iamp.vo.Task;
import com.github.pagehelper.PageInfo;


public class ResourceTaskManagement  extends ExBaseTask{
	private ResourceTaskService resourceTaskService;
	private ResourceLibService resourceLibService;
	
	 public void executeExJob(JobExecutionContext context, Task task, Map<String,Object> paramMap)
				throws JobExecutionException {
		 resourceLibService = (ResourceLibService) SpringFactory.getObject("resourceLibService");
		 resourceTaskService = (ResourceTaskService) SpringFactory.getObject("resourceTaskService");
		Date dt1 = new Date();
        //默认页大小
        int pageSize = 100;
        //记录总数
        int count = resourceTaskService.getCount(null);
        //总页数
        int pageCount = count/pageSize+1;
        
        JSONObject json = new JSONObject();
		json.put("host", Constant.DB_HOST);
		json.put("user", Constant.DB_ACCOUNT);
		json.put("passwd", Constant.DB_PASSWORD);
		json.put("dbName", "213trswcmv7");
		
		String oapath = System.getProperty("oapath");
        for(int i=0;i<pageCount;i++){
        	
        	PageInfo<ResourceTask> page = resourceTaskService.page(null, i+1, pageSize);
        	List<ResourceTask> taskList = page.getList();
        	
        	JSONArray delIndex = new JSONArray();
        	JSONArray updateIndex = new JSONArray();
        	JSONArray updateTag = new JSONArray();
        	JSONArray updateTagIndex = new JSONArray();
        	if(taskList!=null && taskList.size()>0){
        		for(ResourceTask tasks:taskList){
        			String libNum = tasks.getLibNum();//资源库编号
//        			ResourceLib resLib = resourceLibService.getByLibNum(libNum);
//        			String libName = resLib.getLibName();//资源库名称
        			
        			JSONObject obj = new JSONObject();
        			
        			obj.put("tableName", libNum);
        			obj.put("id", tasks.getResId());
        			
        			
        			//任务代码:包括：删除全文索引  del_index,更新全文索引 update_index,打标签 update_tag,打标签并更新索引 update_tag_index
        			String actCode = tasks.getActCode();
        			if(actCode.equals("del_index")){
        				delIndex.put(obj);
        			}else if(actCode.equals("update_index")){
        				updateIndex.put(obj);
        			}else if(actCode.equals("update_tag")){
        				updateTag.put(obj);
        			}else if(actCode.equals("update_tag_index")){
        				updateTagIndex.put(obj);
        			}
        			
        			/*if(actCode.equals("del_index") || actCode.equals("update_index")){
        				String pythonFile = oapath+File.separator+"exiaserver"+File.separator+"data_dig_tools"+File.separator+"expy"+File.separator+"classify"+File.separator+"elasticsearchHandle.py";
        				json.put("actCode", actCode);
        				json.put("elasHost", "10.2.0.211");
        				msg = CategoryAndKeyWordsUtil.importResourceTag(json.toString(),pythonFile);
        			}else if(actCode.equals("update_tag")){
        				String pythonFile = oapath+File.separator+"exiaserver"+File.separator+"data_dig_tools"+File.separator+"expy"+File.separator+"classify"+File.separator+"wcmKeyWordsImport.py";
        				msg = CategoryAndKeyWordsUtil.importResourceTag(json.toString(),pythonFile);
        			}else if(actCode.equals("update_tag_index")){
        				json.put("actCode", "update_index");
        				json.put("elasHost", "10.2.0.211");
        				String pythonFile = oapath+File.separator+"exiaserver"+File.separator+"data_dig_tools"+File.separator+"expy"+File.separator+"classify"+File.separator+"wcmKeyWordsImport.py";
        				msg = CategoryAndKeyWordsUtil.importResourceTag(json.toString(),pythonFile);
        			}
        			System.out.println(msg);
        			resourceTaskService.delete(task.getTaskId());
        			Date dt2 = new Date();
        			long t = dt2.getTime()-dt1.getTime();
        			System.out.println("警告:任务耗时"+t+" 毫秒");*/
        			/*if(msg.equals("success")){
        				resourceTaskService.delete(task.getTaskId());
        			}*/
        		}
        		String msg="";
        		if(delIndex!=null&&delIndex.length()>0){
        			json.put("paremeter", delIndex);
        			json.put("actCode", "del_index");
    				json.put("elasHost", "10.2.0.211");
    				String pythonFile = oapath+File.separator+"exiaserver"+File.separator+"data_dig_tools"+File.separator+"expy"+File.separator+"classify"+File.separator+"elasticsearchHandle.py";
    				msg = CategoryAndKeyWordsUtil.importResourceTag(json.toString(),pythonFile);
    				for(int z=0;z<delIndex.length();z++){
    					resourceTaskService.delete(delIndex.getJSONObject(z).getString("id"));
    				}
        		}
        		if(updateIndex!=null&&updateIndex.length()>0){
        			json.put("paremeter", updateIndex);
        			json.put("actCode", "update_index");
    				json.put("elasHost", "10.2.0.211");
    				String pythonFile = oapath+File.separator+"exiaserver"+File.separator+"data_dig_tools"+File.separator+"expy"+File.separator+"classify"+File.separator+"elasticsearchHandle.py";
    				msg = CategoryAndKeyWordsUtil.importResourceTag(json.toString(),pythonFile);
    				for(int z=0;z<updateIndex.length();z++){
    					resourceTaskService.delete(updateIndex.getJSONObject(z).getString("id"));
    				}
        		}
        		if(updateTag!=null&&updateTag.length()>0){
        			json.put("paremeter", updateTagIndex);
        			json.put("actCode", "update_tag");
    				json.put("elasHost", "10.2.0.211");
    				String pythonFile = oapath+File.separator+"exiaserver"+File.separator+"data_dig_tools"+File.separator+"expy"+File.separator+"classify"+File.separator+"wcmKeyWordsImport.py";
    				msg = CategoryAndKeyWordsUtil.importResourceTag(json.toString(),pythonFile);
    				for(int z=0;z<updateTag.length();z++){
    					resourceTaskService.delete(updateTag.getJSONObject(z).getString("id"));
    				}
        		}
        		if(updateTagIndex!=null&&updateTagIndex.length()>0){
        			json.put("paremeter", updateTagIndex);
        			json.put("actCode", "update_tag_index");
    				json.put("elasHost", "10.2.0.211");
    				String pythonFile = oapath+File.separator+"exiaserver"+File.separator+"data_dig_tools"+File.separator+"expy"+File.separator+"classify"+File.separator+"wcmKeyWordsImport.py";
    				msg = CategoryAndKeyWordsUtil.importResourceTag(json.toString(),pythonFile);
//    				if(msg.equals("success"))
    				for(int z=0;z<updateTagIndex.length();z++){
    					resourceTaskService.delete(updateTagIndex.getJSONObject(z).getString("id"));
    				}
        		}
        		//System.out.println(msg);
        		Date dt2 = new Date();
    			long t = dt2.getTime()-dt1.getTime();
    			//System.out.println("警告:任务耗时"+t+" 毫秒");
        	}
        }
    }
}
