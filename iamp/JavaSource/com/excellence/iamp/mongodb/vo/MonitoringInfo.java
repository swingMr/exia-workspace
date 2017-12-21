package com.excellence.iamp.mongodb.vo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "ia_monitoring_info")
public class MonitoringInfo {
	@Id
	private String id;
	//资源Id
	private String sourceId;
	//关联概念
	private List<String> concepts;
	//资源库名称
	private String libName;
	//资源库编号
	private String libNum;
	//表名
	private String collectionName;
	//标题
	private String title;
	//所属领域
	private List<String> domains;
	//入库时间
	private Date intoLibDate;
	//状态  0未处理 ，1已处理  -1忽略
	private int monitoredStatus;
	
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getLibNum() {
		return libNum;
	}

	public void setLibNum(String libNum) {
		this.libNum = libNum;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List<String> getConcepts() {
		return concepts;
	}
	
	public String getDomainStr(){
		List<String> domains = getDomains();
        String result = "";
        if(domains != null && domains.size() > 0) {
        	if(domains.size()>1){
        		for(int i=0;i<domains.size();i++) {
                    if(i==0 && StringUtils.isNotBlank(domains.get(i))) {
                    	result = result + domains.get(i).replaceAll(";", "");
                    } else if(StringUtils.isNotBlank(domains.get(i))){	
                    	result = result + ";"+domains.get(i);
                    }
                }
            }else if(domains.size() == 1){
            	result = domains.get(0).replaceAll(";", "");
            }
        }
        return result;
	}
	
	public String getConceptStr(){
		List<String> list = getConcepts();
        String result = "";
        if(list != null && list.size() > 0) {
        	if(list.size()>1){
        		for(int i=0;i<list.size();i++) {
                    if(i==0 && StringUtils.isNotBlank(list.get(i))) {
                    	result = result + list.get(i).replaceAll(";", "");
                    } else if(StringUtils.isNotBlank(list.get(i))){	
                    	result = result + ";"+list.get(i);
                    }
                }
            }else if(list.size() == 1){
            	result = list.get(0).replaceAll(";", "");
            }
        }
        return result;
	}
	
	public void setConcepts(List<String> concepts) {
		this.concepts = concepts;
	}
	public String getLibName() {
		return libName;
	}
	public void setLibName(String libName) {
		this.libName = libName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getDomains() {
		return domains;
	}
	public void setDomains(List<String> domains) {
		this.domains = domains;
	}
	public Date getIntoLibDate() {
		return intoLibDate;
	}
	public void setIntoLibDate(Date intoLibDate) {
		this.intoLibDate = intoLibDate;
	}
	public int getMonitoredStatus() {
		return monitoredStatus;
	}
	public void setMonitoredStatus(int monitoredStatus) {
		this.monitoredStatus = monitoredStatus;
	}
}
