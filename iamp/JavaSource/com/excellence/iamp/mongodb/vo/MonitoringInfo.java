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
	//��ԴId
	private String sourceId;
	//��������
	private List<String> concepts;
	//��Դ������
	private String libName;
	//��Դ����
	private String libNum;
	//����
	private String collectionName;
	//����
	private String title;
	//��������
	private List<String> domains;
	//���ʱ��
	private Date intoLibDate;
	//״̬  0δ���� ��1�Ѵ���  -1����
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
