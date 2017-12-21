package com.excellence.iamp.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.excellence.iacommon.common.util.IaServiceClient;
import com.excellence.iamp.mongodb.service.MonitoringInfoService;
import com.excellence.iamp.mongodb.service.MonitoringSettingService;
import com.excellence.iamp.mongodb.service.ResourceLibService;
import com.excellence.iamp.mongodb.service.ResourceService;
import com.excellence.iamp.mongodb.vo.MonitoringInfo;
import com.excellence.iamp.mongodb.vo.MonitoringSetting;
import com.excellence.iamp.mongodb.vo.Resource;
import com.excellence.iamp.mongodb.vo.ResourceLib;
import com.excellence.iamp.mongodb.vo.SettingAttribute;
import com.excellence.iamp.mongodb.vo.Resource.TagInfo;
import com.excellence.iamp.util.Page;
import com.excellence.iamp.util.SpringFactory;
import com.excellence.iamp.vo.Task;
import com.excellence.iamp.vo.enums.EStatus;

public class MonitoringInfoTask extends ExBaseTask{

	private MonitoringInfoService monitoringInfoService;
	
	private MonitoringSettingService monitoringSettingService;
	
	private ResourceService resourceService;
	
	private ResourceLibService resourceLibService;
	/** 编写资源入库定时任务
	 * @date 2017年8月31日
	 * @author liuzh
	 */
    public void executeExJob(JobExecutionContext context, Task task, Map<String,Object> paramMap)
			throws JobExecutionException {
    	monitoringInfoService = (MonitoringInfoService)SpringFactory.getObject("monitoringInfoService");
    	monitoringSettingService = (MonitoringSettingService)SpringFactory.getObject("monitoringSettingService");
    	resourceService = (ResourceService)SpringFactory.getObject("resourceService");
    	resourceLibService = (ResourceLibService)SpringFactory.getObject("resourceLibService");
		Date t1 = new Date();
		System.out.println("开始执行");
    	//主体
		List<String> subParents = new ArrayList<String>() ;
		List<String> subConcepts = new ArrayList<String>();
		//客体
		List<String> objParents = new ArrayList<String>();
		List<String> objConcepts = new ArrayList<String>();
		//行为
		List<String> actParents = new ArrayList<String>();
		List<String> actConcepts = new ArrayList<String>();
		//时间
		List<String> timeParents = new ArrayList<String>();
		List<String> timeConcepts = new ArrayList<String>();
		//空间
		List<String> spaceParents = new ArrayList<String>();
		List<String> spaceConcepts = new ArrayList<String>();
		//问题
		List<String> proParents = new ArrayList<String>();
		List<String> proConcepts = new ArrayList<String>();
		//关键词
		List<String> keywordParents = new ArrayList<String>();
		List<String> keywordConcepts = new ArrayList<String>();
    	//0.查出所有的规则
		MonitoringSetting monitoringSetting = new MonitoringSetting();
		List<MonitoringSetting> settingList = monitoringSettingService.getList();
		if(settingList != null && settingList.size()>0){
			 monitoringSetting = monitoringSettingService.getList().get(0);
		}
		//1.先查出所有的资源库
    	List<ResourceLib> libList = resourceLibService.getAll();
    	//没有资源库不用进行；
    	if(libList !=null && libList.size() > 0){
        	for(int i=0; libList.size()>i; i++){
        		String collectionName = libList.get(i).getCollectionName();
        		//2.通过资源库 查出符合的信息资源；
        		Page<Resource> resourcePage = resourceService.getList(i, 100, collectionName, "", "");
        		List<Resource> resourceList = resourcePage.getRows();
        		//3.查看是否有资源信息 无则不用进行
        		if(resourceList != null && resourceList.size() > 0){
            		for(int j=0; resourceList.size()>j; j++){
						//要保存的值
						List<String> addConceptList = new ArrayList();    
						List<String> addParentList = new ArrayList();
						//默认不限定
            			if(resourceList.get(j).getTag() != null){
            				if(monitoringSetting.getId() != null){
            					Map<String,List<TagInfo>> map= resourceList.get(j).getTag();
            					conditionalFiltering("organdpersons",map, monitoringSetting.getSubjectSetting(), subParents, subConcepts,addConceptList, addParentList);	 
            					conditionalFiltering("objects",map, monitoringSetting.getObjectSetting(), objParents, objConcepts,addConceptList, addParentList);	 
            					conditionalFiltering("acts",map, monitoringSetting.getActionSetting(), actParents, actConcepts,addConceptList, addParentList);	 
            					conditionalFiltering("times",map, monitoringSetting.getTimeSetting(), timeParents, timeConcepts,addConceptList, addParentList);	 
            					conditionalFiltering("spaces",map, monitoringSetting.getSpaceSetting(), spaceParents, spaceConcepts,addConceptList, addParentList);	 
            					conditionalFiltering("keywords",map, monitoringSetting.getKeywordSetting(), keywordParents, keywordConcepts,addConceptList, addParentList);	            				
            				}
							MonitoringInfo info = new MonitoringInfo();
		            		info.setCollectionName(collectionName);
		            		info.setIntoLibDate(new Date());
		            		info.setConcepts(addConceptList);
		            		info.setDomains(resourceList.get(j).getDomains());
            				info.setLibName(libList.get(i).getLibName());
            				info.setLibNum(resourceList.get(j).getLibNum());
            				info.setTitle(resourceList.get(j).getTitle());
            				monitoringInfoService.save(info);
            				Resource resource = resourceList.get(j);
            				resource.setMonitoredStatus(EStatus.normal.getIndex());
            				resourceService.update(resource, collectionName);
            			}else{
            				//3.2标签为空 要打标签。
            				boolean result = saveTag(resourceList.get(j),collectionName);            				
            				if(result){
                        		//4.根据监控设置条件进行筛选 全都是or
                				if(monitoringSetting.getId() != null){
                					Map<String,List<TagInfo>> map= resourceList.get(j).getTag();
                					conditionalFiltering("organdpersons",map, monitoringSetting.getSubjectSetting(), subParents, subConcepts,addConceptList, addParentList);	 
                					conditionalFiltering("objects",map, monitoringSetting.getObjectSetting(), objParents, objConcepts,addConceptList, addParentList);	 
                					conditionalFiltering("acts", map,monitoringSetting.getActionSetting(), actParents, actConcepts,addConceptList, addParentList);	 
                					conditionalFiltering("times",map, monitoringSetting.getTimeSetting(), timeParents, timeConcepts,addConceptList, addParentList);	 
                					conditionalFiltering("spaces",map, monitoringSetting.getSpaceSetting(), spaceParents, spaceConcepts,addConceptList, addParentList);	 
                					conditionalFiltering("keywords",map, monitoringSetting.getProblemSetting(), proParents, proConcepts,addConceptList, addParentList);	 
                				}
                				if(addConceptList.size()>0){
        							MonitoringInfo info = new MonitoringInfo();
        		            		info.setCollectionName(collectionName);
        		            		info.setIntoLibDate(new Date());
        		            		info.setConcepts(addConceptList);
        		            		info.setDomains(resourceList.get(j).getDomains());
                    				info.setLibName(libList.get(i).getLibName());
                    				info.setLibNum(resourceList.get(j).getLibNum());
                    				info.setTitle(resourceList.get(j).getTitle());
                    				info.setSourceId(resourceList.get(j).getId());
                    				monitoringInfoService.save(info);	
                				}
                				Resource resource = resourceList.get(j);
                				resource.setMonitoredStatus(EStatus.normal.getIndex());
                				resourceService.update(resource, collectionName);
            				}
                			Date dt2 = new Date();
                			long t = dt2.getTime()-t1.getTime();
            			};
            		}	
        		}
        		//5.概念符合的 保存。
        	}	
    	}
		Date dt3 = new Date();
		long t = dt3.getTime()-t1.getTime();
		System.out.println("全部执行完："+t);
	}
    
    /** 处理list添加
     * @author Liuzh
     * @param map
     * @param subConcepts
     * @param subParents
     * @param addConceptList
     * @param addParentList
     */
    private void handleAttr(String type,Map<String,List<TagInfo>> map,List<String> subConcepts,List<String> subParents,String settingType,List<String> addConceptList,List<String> addParentList){
    	List<TagInfo> tagInfo = new ArrayList<TagInfo>();
		if(map.containsKey(type)){
		    tagInfo =map.get(type);
		    if(tagInfo !=null && tagInfo.size()>0){
				for(int s=0; tagInfo.size()>s; s++){
	            	   String concept  = tagInfo.get(s).getContent();
	            	   String tagType = tagInfo.get(s).getType();
	            	 //加上限定判断 2017年9月7日
	            	   if(StringUtils.isBlank(tagType)){
	            		   tagType="no";
	            	   }
            		   if(settingType.equals("clazz") && settingType.equals(tagType)){
            			   //限定类
		            	   if(subConcepts.contains(concept)){
		            		   addList(addConceptList, concept);
		            	   }
            		   }else if(settingType.equals("concrete") && settingType.equals(tagType)){
            			   	//限定实例
            			   	addList(addConceptList, concept);
            		   }else{
		            	   if(subConcepts.contains(concept)){  		   
		            		   addList(addConceptList, concept);
		            	   }
            		   }
            		   if(!type.equals("keywords")){
    	            	   List nameList = tagInfo.get(s).getParentNames();
    	            	   if(nameList != null){
    		            	   for(int n=0; nameList.size()>n; n++){
    		            		   //限定类
    		            		   if(settingType.equals("clazz") && settingType.equals(tagType)){
    			            		   if(subParents.contains(nameList.get(n))){
    			            			   addList(addConceptList, concept);
    			            		   };
    		            		   }else if(settingType.equals("concrete") && settingType.equals(tagType)){
    			            		   if(subParents.contains(nameList.get(n))){
    			            			   addList(addConceptList, concept);
    			            		   };
    		            		   }else{
    			            		   if(subParents.contains(nameList.get(n))){
    			            			   addList(addConceptList, concept);
    			            		   };
    		            		   }
    		            	   }
    	            	   }   
            		   }
	            }
		    }
		};
    }
    
  /**@author Liuzh
   * 满足条件的 需要保存的,要去重复
   * @param addConceptList
   * @param concept
   */
    private void addList(List<String> addConceptList,String concept){
	   if(!addConceptList.contains(concept)){
		   addConceptList.add(concept);   
	   }
    }
    
    /**条件筛选
     * @author Liuzh
     * @param map
     * @param settingAttribute
     * @param filterParents
     * @param filterConcepts
     * @param addConceptList
     * @param addParentList
     */
    private void conditionalFiltering(String type,Map<String,List<TagInfo>> map,SettingAttribute settingAttribute,
    	List<String>  filterParents,List<String> filterConcepts,List<String> addConceptList,List<String> addParentList){
    	filterParents = settingAttribute.getParents();
    	filterConcepts = settingAttribute.getConcepts();
		handleAttr(type,map, filterConcepts, filterParents,settingAttribute.getType(), addConceptList, addParentList);
    }
    
    /** 保存标签
     * @author Liuzh
     * @time 2017年8月31日
     */
    private Boolean saveTag(Resource resource,String collectionName){
    	boolean result=false;
        String regExHtml = "<[^>]+>"; // 定义HTML标签的正则表达式 
        String regExBr = "<?+br\\s*/?>"; // 定义HTML标签的正则表达式
        String pureContent = ""; 
        String title ="";
        // 修改过某些字段，所以有可能存在htmlcontent 但还没有content；所以这里把content补上；
        if(StringUtils.isNotBlank(resource.getHtmlContent())){
        	pureContent = resource.getHtmlContent().replaceAll(regExBr, "\n").replaceAll("</p>","\n").replaceAll(regExHtml, "");
        	resource.setContent(pureContent);
        }
        if(StringUtils.isNotBlank(resource.getTitle())){
        	title = resource.getTitle();
        }
		String tag = IaServiceClient.gentag(title, pureContent, true, true, "",""); 
		int judge = 1;
		if(StringUtils.isNotBlank(tag)){
			//System.out.println(tag);
			JSONObject obj = new JSONObject(tag);
			 judge = (Integer)obj.get("status");
			if(judge != 0){
				resource.setTagByJsonObj((JSONObject) obj.get("data"));
				resourceService.save(resource, collectionName);
				result=true;
			}	
		}
		return result;
    }
}
