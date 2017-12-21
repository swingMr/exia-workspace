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
	/** ��д��Դ��ⶨʱ����
	 * @date 2017��8��31��
	 * @author liuzh
	 */
    public void executeExJob(JobExecutionContext context, Task task, Map<String,Object> paramMap)
			throws JobExecutionException {
    	monitoringInfoService = (MonitoringInfoService)SpringFactory.getObject("monitoringInfoService");
    	monitoringSettingService = (MonitoringSettingService)SpringFactory.getObject("monitoringSettingService");
    	resourceService = (ResourceService)SpringFactory.getObject("resourceService");
    	resourceLibService = (ResourceLibService)SpringFactory.getObject("resourceLibService");
		Date t1 = new Date();
		System.out.println("��ʼִ��");
    	//����
		List<String> subParents = new ArrayList<String>() ;
		List<String> subConcepts = new ArrayList<String>();
		//����
		List<String> objParents = new ArrayList<String>();
		List<String> objConcepts = new ArrayList<String>();
		//��Ϊ
		List<String> actParents = new ArrayList<String>();
		List<String> actConcepts = new ArrayList<String>();
		//ʱ��
		List<String> timeParents = new ArrayList<String>();
		List<String> timeConcepts = new ArrayList<String>();
		//�ռ�
		List<String> spaceParents = new ArrayList<String>();
		List<String> spaceConcepts = new ArrayList<String>();
		//����
		List<String> proParents = new ArrayList<String>();
		List<String> proConcepts = new ArrayList<String>();
		//�ؼ���
		List<String> keywordParents = new ArrayList<String>();
		List<String> keywordConcepts = new ArrayList<String>();
    	//0.������еĹ���
		MonitoringSetting monitoringSetting = new MonitoringSetting();
		List<MonitoringSetting> settingList = monitoringSettingService.getList();
		if(settingList != null && settingList.size()>0){
			 monitoringSetting = monitoringSettingService.getList().get(0);
		}
		//1.�Ȳ�����е���Դ��
    	List<ResourceLib> libList = resourceLibService.getAll();
    	//û����Դ�ⲻ�ý��У�
    	if(libList !=null && libList.size() > 0){
        	for(int i=0; libList.size()>i; i++){
        		String collectionName = libList.get(i).getCollectionName();
        		//2.ͨ����Դ�� ������ϵ���Ϣ��Դ��
        		Page<Resource> resourcePage = resourceService.getList(i, 100, collectionName, "", "");
        		List<Resource> resourceList = resourcePage.getRows();
        		//3.�鿴�Ƿ�����Դ��Ϣ �����ý���
        		if(resourceList != null && resourceList.size() > 0){
            		for(int j=0; resourceList.size()>j; j++){
						//Ҫ�����ֵ
						List<String> addConceptList = new ArrayList();    
						List<String> addParentList = new ArrayList();
						//Ĭ�ϲ��޶�
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
            				//3.2��ǩΪ�� Ҫ���ǩ��
            				boolean result = saveTag(resourceList.get(j),collectionName);            				
            				if(result){
                        		//4.���ݼ��������������ɸѡ ȫ����or
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
        		//5.������ϵ� ���档
        	}	
    	}
		Date dt3 = new Date();
		long t = dt3.getTime()-t1.getTime();
		System.out.println("ȫ��ִ���꣺"+t);
	}
    
    /** ����list���
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
	            	 //�����޶��ж� 2017��9��7��
	            	   if(StringUtils.isBlank(tagType)){
	            		   tagType="no";
	            	   }
            		   if(settingType.equals("clazz") && settingType.equals(tagType)){
            			   //�޶���
		            	   if(subConcepts.contains(concept)){
		            		   addList(addConceptList, concept);
		            	   }
            		   }else if(settingType.equals("concrete") && settingType.equals(tagType)){
            			   	//�޶�ʵ��
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
    		            		   //�޶���
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
   * ���������� ��Ҫ�����,Ҫȥ�ظ�
   * @param addConceptList
   * @param concept
   */
    private void addList(List<String> addConceptList,String concept){
	   if(!addConceptList.contains(concept)){
		   addConceptList.add(concept);   
	   }
    }
    
    /**����ɸѡ
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
    
    /** �����ǩ
     * @author Liuzh
     * @time 2017��8��31��
     */
    private Boolean saveTag(Resource resource,String collectionName){
    	boolean result=false;
        String regExHtml = "<[^>]+>"; // ����HTML��ǩ��������ʽ 
        String regExBr = "<?+br\\s*/?>"; // ����HTML��ǩ��������ʽ
        String pureContent = ""; 
        String title ="";
        // �޸Ĺ�ĳЩ�ֶΣ������п��ܴ���htmlcontent ����û��content�����������content���ϣ�
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
