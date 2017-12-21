package com.excellence.iamp.mongodb.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;


/**
 * ��Ϣ��Դ��
 * @author huangjinyuan
 *
 */
public class Resource implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5323831345661722923L;

	/**
	 * ����
	 */
	@Id
	private String id;
	
	/**
	 * ��Դ����
	 */
	private String libNum;
	
	/**
	 * URL
	 */
	private String url;

	/**
	 * Ŀ¼id
	 */
	private String catalogId;
	
	/**
	 * ��ԴĿ¼id���Ӷ���Ŀ¼���ϼ�Ŀ¼
	 */
	private List<String> catalogIds;
	
	/**
	 * ��ԴĿ¼���ƣ��Ӷ���Ŀ¼���ϼ�Ŀ¼
	 */
	private List<String> catalogNames;
	
	/**
	 * Ŀ¼���
	 */
	private String catalogNum;
	
	/**
	 * �汾��
	 */
	private String version;
	
	/**
	 * ���ĺ�
	 */
	private String issuedNum;
	
	/**
	 * ����
	 */
	private String title;
	
	/**
	 * ��������
	 */
	private Date publishDate;
	
	/**
	 * ������
	 */
	private String publisher;
	
	/**
	 * ���
	 */
	private String genre;
	
	/**
	 * ����
	 */
	private String granularity;
	
	/**
	 * ��Ч����
	 */
	private Date validTime;
	
	/**
	 * ״̬  1 ����  0 ���� -1 �ϳ�
	 */
	private int status;
	
	/**
	 * ժҪ
	 */
	private String abstractText;
	
	/**
	 * ���Ĵ��ı�����
	 */
	private String content;
	
	/**
	 * html����
	 */
	private String htmlContent;
	
	/**
	 * ԭ�ĵ�ַ
	 */
	private String originalAddress;
	
	/**
	 * ������id
	 */
	private String creatorId;
	
	/**
	 * ����������
	 */
	private String creatorName;
	
	/**
	 * ��������
	 */
	private Date createDate;
	
	/**
	 * ��������
	 */
	private Date updateDate;
	
	/**
	 * ��������
	 */
	private List<String> belongDomain;
	
	/**
	 * ����ȷ��״̬ 0δȷ�ϣ�1ȷ��
	 */
	private int domainConfirmStatus;
	
	/**
	 * �ؼ���
	 */
	private List<String> keyWords;
	
	
	/**
	 * ��Դ
	 */
	private String source;
	
	/**
	 * �ļ���׺�������link���͵Ļ��������޸������Լ�������Ϣ
	 */
	private String fileExt;
	
	/**
	 * ���״̬ 0δ��� 1�����
	 */
	private int monitoredStatus;
	
	//Ȩ�޿��ƣ�1-���� 2-���е�¼�û� 3-��Ȩ��
	private int authority;
	
	/**
	 * ��չ����
	 */
	private List<Attribute> extendAttrs;
	
	/**
	 * ����&������Ϣ
	 */
	private List<FileInfo> fileInfos;
	
	/**
	 * ��ǩ
	 * {
		 "domains": [{"content":"�����","type":"clazz","similarity":���ƶ�},"parentNames"��[{"content":"�����","type":"clazz"}]}],
		 "acts":[{"content":"��Ϊ��","similarity":���ƶ�,"parentNames"��[{"content":"�����","type":"clazz"}]}],
		 "organdpersons":[{"content":"�����","similarity":���ƶ� ,"parentNames"��[{"content":"�����","type":"clazz"}]}],
		 "objects": [{"content":"�����","similarity":���ƶ�,"parentNames"��[{"content":"�����","type":"clazz"}]}],
		 "spaces":[{"content":"�ռ��","similarity":���ƶ�},"parentNames"��[{"content":"�����","type":"clazz"}]],
		 "times":[{"content":"ʱ���","similarity":���ƶ�},"parentNames"��[{"content":"�����","type":"clazz"}]],
		 "keywords":[{"content":"�ؼ���","similarity":���ƶ�},"parentNames"��[{"content":"�����","type":"clazz"}]],
		 "hierarchy":[{"content":"�����㼶��","similarity":���ƶ�},"parentNames"��[{"content":"�����","type":"clazz"}]]
		}
	 */
	private Map<String, List<TagInfo>> tags;
	
	/**
	 * �������
	 */
	private String classify;
	
	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<String> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(List<String> keyWords) {
		this.keyWords = keyWords;
	}
	
	public String getDomainstr(){
		List<String> domains = getBelongDomain();
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
        	return result.replaceAll("����ʵ��", "ȫ����");
        }
        return result;
	}
	
	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}
	
	public List<String> getCatalogIds() {
		return catalogIds;
	}

	public void setCatalogIds(List<String> catalogIds) {
		this.catalogIds = catalogIds;
	}

	public List<String> getCatalogNames() {
		return catalogNames;
	}

	public void setCatalogNames(List<String> catalogNames) {
		this.catalogNames = catalogNames;
	}

	/**
	 * ͨ��json�ַ�����ֵ����Vo
	 * @param jsonObj
	 */
	@SuppressWarnings("rawtypes")
	public void setTagByJsonObj(JSONObject jsonObj) {
		if(tags == null) {
			tags = new HashMap<String, List<TagInfo>>();
		}
		Iterator it = jsonObj.keys();
		while (it.hasNext()) {  
            String key = (String) it.next();
            JSONArray array = jsonObj.getJSONArray(key);
            List<TagInfo> list = new ArrayList<TagInfo>();
            for(int i=0;i<array.length();i++){
                JSONObject jsonobject = array.getJSONObject(i);
                TagInfo tagInfo = new TagInfo();
                tagInfo.setContent(jsonobject.getString("content"));
                if(jsonobject.has("similarity")){
                    tagInfo.setSimilarity(jsonobject.getDouble("similarity"));	
                }else{
                	tagInfo.setSimilarity(0.0);	
                }
                if(jsonobject.has("parentNames") && jsonobject.getJSONArray("parentNames")!=null){
                	JSONArray parentNames = jsonobject.getJSONArray("parentNames");
                	List<String> parentNamesList = new ArrayList<String>();
                	for(int t=0; parentNames.length()>t; t++){
                		parentNamesList.add(parentNames.get(t).toString());
                	}
                	tagInfo.setParentNames(parentNamesList);
                }
                if(jsonobject.has("type") && jsonobject.get("type") !=null){
                	tagInfo.setType((String)jsonobject.get("type"));
                }else{
                	tagInfo.setType("no");
                }
                list.add(tagInfo);
            }
            tags.put(key, list);
        } 
	}
	
	public JSONObject getTagByJsonObj() {
		JSONObject json = new JSONObject();
		if(tags == null) {
			Set<String> it = tags.keySet();
			for(String key: it) {
				JSONArray arr = new JSONArray(tags.get(key));
				json.put(key, arr);
			}
			return json;
		}
		return null;
	}
	
	
	
	public Map<String, List<TagInfo>> getTag() {
		return tags;
	}

	public void setTag(Map<String, List<TagInfo>> tag) {
		this.tags = tag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLibNum() {
		return libNum;
	}

	public void setLibNum(String libNum) {
		this.libNum = libNum;
	}

	public String getCatalogNum() {
		return catalogNum;
	}

	public void setCatalogNum(String catalogNum) {
		this.catalogNum = catalogNum;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIssuedNum() {
		return issuedNum;
	}

	public void setIssuedNum(String issuedNum) {
		this.issuedNum = issuedNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getGranularity() {
		return granularity;
	}

	public void setGranularity(String granularity) {
		this.granularity = granularity;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOriginalAddress() {
		return originalAddress;
	}

	public void setOriginalAddress(String originalAddress) {
		this.originalAddress = originalAddress;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<String> getBelongDomain() {
		return belongDomain;
	}

	public void setBelongDomain(List<String> belongDomain) {
		this.belongDomain = belongDomain;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public List<String> getDomains() {
		if(tags != null) {
			if(tags.containsKey("domains")) {
				List<TagInfo> list = tags.get("domains");
				if(list != null && list.size() > 0) {
					List<String> words = new ArrayList<String>();
					for(TagInfo tagInfo:list) {
						words.add(tagInfo.getContent());
					}
					return words;
				}
			}
		}
		return null;
	}
	
	public List<String> getActs() {
		if(tags != null) {
			if(tags.containsKey("acts")) {
				List<TagInfo> list = tags.get("acts");
				if(list != null && list.size() > 0) {
					List<String> words = new ArrayList<String>();
					for(TagInfo tagInfo:list) {
						words.add(tagInfo.getContent());
					}
					return words;
				}
			}
		}
		return null;
	}
	
	public List<String> getOrgandpersons() {//����
		if(tags != null) {
			if(tags.containsKey("organdpersons")) {
				List<TagInfo> list = tags.get("organdpersons");
				if(list != null && list.size() > 0) {
					List<String> words = new ArrayList<String>();
					for(TagInfo tagInfo:list) {
						words.add(tagInfo.getContent());
					}
					return words;
				}
			}
		}
		return null;
	}
	
	public List<String> getobjects() {//����
		if(tags != null) {
			if(tags.containsKey("objects")) {
				List<TagInfo> list = tags.get("objects");
				if(list != null && list.size() > 0) {
					List<String> words = new ArrayList<String>();
					for(TagInfo tagInfo:list) {
						words.add(tagInfo.getContent());
					}
					return words;
				}
			}
		}
		return null;
	}
	
	public List<String> getSpaces() {//�ռ�
		if(tags != null) {
			if(tags.containsKey("spaces")) {
				List<TagInfo> list = tags.get("spaces");
				if(list != null && list.size() > 0) {
					List<String> words = new ArrayList<String>();
					for(TagInfo tagInfo:list) {
						words.add(tagInfo.getContent());
					}
					return words;
				}
			}
		}
		return null;
	}
	
	public List<String> getTimes() {//ʱ��
		if(tags != null) {
			if(tags.containsKey("times")) {
				List<TagInfo> list = tags.get("times");
				if(list != null && list.size() > 0) {
					List<String> words = new ArrayList<String>();
					for(TagInfo tagInfo:list) {
						words.add(tagInfo.getContent());
					}
					return words;
				}
			}
		}
		return null;
	}
	
	public List<String> getHierarchy() {//�����㼶��
		if(tags != null) {
			if(tags.containsKey("hierarchy")) {
				List<TagInfo> list = tags.get("hierarchy");
				if(list != null && list.size() > 0) {
					List<String> words = new ArrayList<String>();
					for(TagInfo tagInfo:list) {
						words.add(tagInfo.getContent());
					}
					return words;
				}
			}
		}
		return null;
	}


	public List<Attribute> getExtendAttrs() {
		return extendAttrs;
	}

	public void setExtendAttrs(List<Attribute> extendAttrs) {
		this.extendAttrs = extendAttrs;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}


	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public void addAttribute(Attribute attribute) {
			if(extendAttrs!=null && extendAttrs.size() > 0) {
				extendAttrs.add(attribute);
			} else {
				extendAttrs = new ArrayList<Attribute>();
				extendAttrs.add(attribute);
			}
		}
	
	public void delAttribute(String attrName) {
		if(extendAttrs!=null && extendAttrs.size() > 0) {
			for (Iterator<Attribute> it = extendAttrs.iterator(); it.hasNext();) {
				Attribute attr = it.next();
				if(attr.getAttrName().equals(attrName)) {
					it.remove(); 
				}
			}
		} 
	}
	
	public void delAttributeByAttrCName(String attrCName) {
		if(extendAttrs!=null && extendAttrs.size() > 0) {
			for (Iterator<Attribute> it = extendAttrs.iterator(); it.hasNext();) {
				Attribute attr = it.next();
				if(attr.getAttrCName().equals(attrCName)) {
					it.remove(); 
				}
			}
		} 
	}
	
	public void addFileInfo(FileInfo fileInfo) {
		if(fileInfos!=null && fileInfos.size() > 0) {
			fileInfos.add(fileInfo);
		} else {
			fileInfos = new ArrayList<FileInfo>();
			fileInfos.add(fileInfo);
		}
	}

	public void delFileInfo(String title) {
		if(fileInfos!=null && fileInfos.size() > 0) {
			for (Iterator<FileInfo> it = fileInfos.iterator(); it.hasNext();) {
				FileInfo fileInfo = it.next();
				if(fileInfo.getTitle().equals(title)) {
					it.remove(); 
				}
			}
		} 
	}
	
	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public int getMonitoredStatus() {
		return monitoredStatus;
	}

	public void setMonitoredStatus(int monitoredStatus) {
		this.monitoredStatus = monitoredStatus;
	}


	public int getDomainConfirmStatus() {
		return domainConfirmStatus;
	}

	public void setDomainConfirmStatus(int domainConfirmStatus) {
		this.domainConfirmStatus = domainConfirmStatus;
	}



	public class TagInfo {
		/**
		 * ����
		 */
		private String content;
		
		/**
		 * ��������
		 */
		private String type;
		
		/**
		 * ���ƶ�
		 */
		private double similarity;
		
		private List<String> parentNames;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public double getSimilarity() {
			return similarity;
		}

		public void setSimilarity(double similarity) {
			this.similarity = similarity;
		}

		public List<String> getParentNames() {
			return parentNames;
		}
		
		public String getParentNamesStr(){
			if(parentNames != null && parentNames.size()>0){
				String result = "";
				for(int i=0; parentNames.size()>i; i++){
                    if(i==0 && StringUtils.isNotBlank(parentNames.get(i))) {
                    	result = result + parentNames.get(i);
                    } else if(StringUtils.isNotBlank(parentNames.get(i))){	
                    	result = result + ","+parentNames.get(i);
                    }
				}
				return result;
			}
			return null;
		}

		public void setParentNames(List<String> parentNames) {
			this.parentNames = parentNames;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
	}
	
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("id", this.id);
		json.put("libNum", this.libNum);
		json.put("catalogId", this.catalogId);
		json.put("catalogIds", new JSONArray(this.catalogIds));
		json.put("catalogNames", new JSONArray(this.catalogNames));
		json.put("catalogNum", this.catalogNum);
		json.put("version", this.version);
		json.put("issuedNum", this.issuedNum);
		json.put("title", this.title);
		json.put("publishDate", this.publishDate);
		json.put("publisher", this.publisher);
		json.put("genre", this.genre);
		json.put("granularity", this.granularity);
		json.put("validTime", this.validTime);
		json.put("abstractText", this.abstractText);
		json.put("keywords", new JSONArray(this.keyWords));
		json.put("htmlContent", this.htmlContent);
		json.put("originalAddress", this.originalAddress);
		json.put("creatorId", this.catalogId);
		json.put("creatorName", this.creatorName);
		json.put("createDate", this.createDate);
		json.put("updateDate", this.updateDate);
		json.put("belongDomain", this.belongDomain);
		json.put("source", this.source);
		json.put("fileExt", this.fileExt);
		json.put("extendAttrs", this.extendAttrs);
		json.put("fileInfos", this.fileInfos);
		json.put("tag", this.tags);
		json.put("classify", this.classify);
		return json;
	}
}
