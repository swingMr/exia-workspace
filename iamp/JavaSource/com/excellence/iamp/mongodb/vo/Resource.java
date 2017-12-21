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
 * 信息资源类
 * @author huangjinyuan
 *
 */
public class Resource implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5323831345661722923L;

	/**
	 * 主键
	 */
	@Id
	private String id;
	
	/**
	 * 资源库编号
	 */
	private String libNum;
	
	/**
	 * URL
	 */
	private String url;

	/**
	 * 目录id
	 */
	private String catalogId;
	
	/**
	 * 资源目录id，从顶级目录到上级目录
	 */
	private List<String> catalogIds;
	
	/**
	 * 资源目录名称，从顶级目录到上级目录
	 */
	private List<String> catalogNames;
	
	/**
	 * 目录编号
	 */
	private String catalogNum;
	
	/**
	 * 版本号
	 */
	private String version;
	
	/**
	 * 发文号
	 */
	private String issuedNum;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 发布日期
	 */
	private Date publishDate;
	
	/**
	 * 发布者
	 */
	private String publisher;
	
	/**
	 * 体裁
	 */
	private String genre;
	
	/**
	 * 粒度
	 */
	private String granularity;
	
	/**
	 * 有效日期
	 */
	private Date validTime;
	
	/**
	 * 状态  1 正常  0 过期 -1 废除
	 */
	private int status;
	
	/**
	 * 摘要
	 */
	private String abstractText;
	
	/**
	 * 正文纯文本内容
	 */
	private String content;
	
	/**
	 * html内容
	 */
	private String htmlContent;
	
	/**
	 * 原文地址
	 */
	private String originalAddress;
	
	/**
	 * 创建人id
	 */
	private String creatorId;
	
	/**
	 * 创建人名称
	 */
	private String creatorName;
	
	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 更新日期
	 */
	private Date updateDate;
	
	/**
	 * 归属领域
	 */
	private List<String> belongDomain;
	
	/**
	 * 领域确认状态 0未确认，1确认
	 */
	private int domainConfirmStatus;
	
	/**
	 * 关键词
	 */
	private List<String> keyWords;
	
	
	/**
	 * 来源
	 */
	private String source;
	
	/**
	 * 文件后缀，如果是link类型的话，不能修改正文以及附件信息
	 */
	private String fileExt;
	
	/**
	 * 监控状态 0未完成 1已完成
	 */
	private int monitoredStatus;
	
	//权限控制（1-开放 2-所有登录用户 3-授权）
	private int authority;
	
	/**
	 * 扩展属性
	 */
	private List<Attribute> extendAttrs;
	
	/**
	 * 正文&附件信息
	 */
	private List<FileInfo> fileInfos;
	
	/**
	 * 标签
	 * {
		 "domains": [{"content":"领域词","type":"clazz","similarity":相似度},"parentNames"：[{"content":"领域词","type":"clazz"}]}],
		 "acts":[{"content":"行为词","similarity":相似度,"parentNames"：[{"content":"领域词","type":"clazz"}]}],
		 "organdpersons":[{"content":"主体词","similarity":相似度 ,"parentNames"：[{"content":"领域词","type":"clazz"}]}],
		 "objects": [{"content":"客体词","similarity":相似度,"parentNames"：[{"content":"领域词","type":"clazz"}]}],
		 "spaces":[{"content":"空间词","similarity":相似度},"parentNames"：[{"content":"领域词","type":"clazz"}]],
		 "times":[{"content":"时间词","similarity":相似度},"parentNames"：[{"content":"领域词","type":"clazz"}]],
		 "keywords":[{"content":"关键词","similarity":相似度},"parentNames"：[{"content":"领域词","type":"clazz"}]],
		 "hierarchy":[{"content":"行政层级词","similarity":相似度},"parentNames"：[{"content":"领域词","type":"clazz"}]]
		}
	 */
	private Map<String, List<TagInfo>> tags;
	
	/**
	 * 主题分类
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
        	return result.replaceAll("领域实例", "全领域");
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
	 * 通过json字符串把值传进Vo
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
	
	public List<String> getOrgandpersons() {//主体
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
	
	public List<String> getobjects() {//客体
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
	
	public List<String> getSpaces() {//空间
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
	
	public List<String> getTimes() {//时间
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
	
	public List<String> getHierarchy() {//行政层级词
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
		 * 词名
		 */
		private String content;
		
		/**
		 * 概念类型
		 */
		private String type;
		
		/**
		 * 相似度
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
